<template>

    <div class="checkin-history-container">
      <div class="card">
        <div class="card-content">
          <p v-if="!checkins.length">No check-in history yet.</p>
          <div v-else>
            <div class="checkin-item" v-for="checkin in checkins" :key="checkin.checkinId">
              <p><strong>Attraction:</strong> {{ getAttractionName(checkin.attractionId) }}</p>
              <p><strong>Check-in Time:</strong> {{ new Date(checkin.checkinTime).toLocaleString() }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="link-container">
        <router-link class="progress-link" v-bind:to="{ name: 'badge'}">Check to see your Badge progress!</router-link>
    </div>

  </template>

  <script>
    import AttractionsService from '../services/AttractionsService';
    import CheckinService from '../services/CheckinService';

  export default {

      data() {

          return {
            checkins: [],
            attractions: {}
          };
      },

      computed: {

      },

      mounted() {
          this.fetchCheckins();
      },

      methods: {

          fetchCheckins() {

            const userId = this.$store.state.user.id;
            
              CheckinService.getCheckinByUserId(userId).then(response => {
                  this.checkins = response.data;
              }).catch(error => {
                  console.error('Error fetching check-ins:', error);
              });
          },

          getAttractionName(attractionId) {

            if (this.attractions[attractionId]) {
                return this.attractions[attractionId];
            }

            this.fetchAttractionDetails(attractionId);
          },

          fetchAttractionDetails(attractionId){

            AttractionsService.getAttractionId(attractionId).then(response => {
                this.attractions[attractionId] = response.data.name;
            }).catch(error => {
                console.error('Error fetching check-ins:', error);
            })
          }
      }
  };

  </script>

  <style scoped>
  /*.checkin-history-container {
  display: flex;
  flex-direction: row;
  width: 100%;
}*/
.checkin-history-container {
  display: flex;
  justify-content: center;
  padding: 16px;
}
.card {
  background-color: var(--neutral-color);
  border: 1px solid #F7CD75;
  border-radius: 8px;
  width: 100%;
  max-width: 800px;
  padding: 16px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}
.card-content {
  padding: 16px;
}
.checkin-item {
  margin-bottom: 16px;
  padding-bottom: 16px;
  border-bottom: 1px solid #ddd;
}
.checkin-item:last-child {
  border-bottom: none;
}
.card p {
  color: var(--text-color);
  margin: 12px 0;
}
.link-container {
  margin-top: 16px;
  text-align: center;
}
/*
.progress-link {
  color: var(--link-color, #007BFF);
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease, text-decoration 0.3s ease;
}
.progress-link:hover {
  color: #F7CD75;
  text-decoration: underline;
  text-decoration-style: dotted;
}  */
.progress-link {
  color: #8B3A3A;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease, text-decoration 0.3s ease;
}
.progress-link:hover {
  color: #F7CD75;
  text-decoration: underline;
}
</style>