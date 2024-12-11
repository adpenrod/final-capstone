<template>
   
<div>
  <div class="badge-container" v-for="badge in badges" :key="badge.id">

    <img
    v-if="!badge.unlocked"
    :src="getImagePath(badge.lockedImage)"
    :alt="`Locked badge: ${badge.name}`"
    class="badge-image"
    >

    <img
    v-else
    :src="getImagePath(badge.unlockedImage)"
    :alt="`Unlocked badge: ${badge.name}`"
    class="badge-image"
    >

    <p>{{ badge.name }}</p>
    <p>{{ badge.description }}</p>
</div>

    <!--

    <div id="Stadiums" class="tabcontent">
        <h3>Stadiums</h3>
        <ul>
            <li v-for="badge in stadiums" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_-_earned_-_se_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_-_unearned_-_se_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>
            </li>
        </ul>
    </div>-->
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
            return new URL(`/src/assets/logos/Badges/${image}`, import.meta.url).href;
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
            UserBadgeService.hasVisitedFiveTimes(this.userId, badge.attractionType)
            .then((response) => {
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
.badge-container {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

.tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
}

.tab button:hover {
    background-color: #ddd;
}

.tab button.active {
    background-color: #ccc;
}

.badge-container {
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

img {
    height: 20%;
    width: 20%;
}
</style>
-->