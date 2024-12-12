<template>

    <div class="badge-card-container">
      <div class="card" v-for="badge in badges" :key="badge.id">
        <div class="card-content">
            
          <span v-if="badge.unlocked">Earned!</span>
          <span v-if="!badge.unlocked">Not Earned!</span>
          <img :src="badge.unlocked ? getImagePath(badge.unlockedImage) : getImagePath(badge.lockedImage)" :alt="badge.name" />
          <h3>{{ badge.name }}</h3>
          <p>{{ badge.description }}</p>

        </div>
      </div>
    </div>

  </template>

  <script>

  import BadgeService from '../services/BadgeService.js';
  import UserBadgeService from '../services/UserBadgeService';
  import CheckinService from '../services/CheckinService';
  import AttractionsService from '../services/AttractionsService';

  export default {

      props: [],

      data() {
          return {
              badges: [],
              userId: this.$store.state.user.id
          };
      },

      computed: {

      },

      mounted() {
          this.fetchBadges();
      },

      methods: {

          getImagePath(image){
              return new URL(`../assets/logos/Badges/${image}`, import.meta.url).href;
          },

          fetchBadges() {

              BadgeService.getBadges().then((response) => {
                     this.badges = response.data;
                     this.checkUnlockConditions();
              }).catch(error => {
                  console.error('Error fetching badges:', error);
              });
          },

          checkUnlockConditions(){

              this.badges.forEach((badge) => {
                  if (badge.name === 'Completionist'){
                      this.checkAllLocationsVisited();
                  }else {
                      this.checkVisitCountForUnlock(badge);
                  }
              });
          },

          checkVisitCountForUnlock(badge) {

              UserBadgeService.hasVisitedFiveTimes(this.userId, badge.attractionType).then((response) => {
                  if (response.data.visits >= 5){
                      badge.unlocked = true;
                  }
              })
              .catch((error) => {
                  console.error(`Error checking visists for badge ${badge.id}:`, error);
              });
          },

          checkAllLocationsVisited(){
              UserBadgeService.hasVisitedAllLocations(this.userId).then((response) => {
                  if (response.data.visits >= 25) {
                      const completionistBadge = this.badges.find(badge => badge.name === 'Completionist');
                      if (completionistBadge) {
                          completionistBadge.unlocked = true;
                      }
                  }
              }).catch((error) => {
                  console.error('Error checking visits', error);
              })
          }
      }
  };

  </script>

  <style scoped>

  .badge-card-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 16px;
    padding: 16px;
    width: 100%;
    box-sizing: border-box;
  }

  .card {
    background-color: var(--neutral-color);
    border: 1px solid #F7CD75;
    border-radius: 8px;
    overflow: hidden;
    transition: box-shadow 0.3s ease;
  }

  .card-content {
    padding: 16px;
    text-align: center;
  }
  
  .card img {
    width: 100%;
    height: 250px;
    object-fit: cover;
    border-bottom: 1px solid #F7CD75;
  }

  .card h3 {
    margin: 12px 0;
    color: var(--text-color);
  }
  .card p {
    color: var(--text-color);
  }
  .card:hover {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  }
  </style>