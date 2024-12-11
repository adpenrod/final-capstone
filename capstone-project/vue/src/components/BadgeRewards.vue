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

    <!--<div id="All" class="tabcontent">
        <h3>Completionist</h3>
        <ul>
            <li v-for="badge in completionist" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_-_earned_-_dol_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_-_unearned_-_dol_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>
            </li>
        </ul>
    </div>

    <div id="Museums" class="tabcontent">
        <h3>Museums</h3>
        <ul>
            <li v-for="badge in museums" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_-_earned_-_ae_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_-_unearned_-_ae_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>

            </li>
        </ul>
    </div>

    <div id="Restaurants" class="tabcontent">
        <h3>Restaurants</h3>
        <ul>
            <li v-for="badge in restaurants" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_earned_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_unearned_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>
            </li>
        </ul>
    </div>

    <div id="Bars" class="tabcontent">
        <h3>Bars</h3>
        <ul>
            <li v-for="badge in bars" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_-_earned_-_bh_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_-_unearned_-_bh_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>
            </li>
        </ul>
    </div>

    <div id="Parks" class="tabcontent">
        <h3>Parks</h3>
        <ul>
            <li v-for="badge in parks" :key="badge.id">
                <img v-if="unlocked" src="../assets/logos/Badges/liberty_walk_-_earned_-_th_480.png">
                <img v-else src="../assets/logos/Badges/liberty_walk_-_unearned_-_th_480.png">
                <h4>{{ badge.name }}</h4>
                <p>{{ badge.description }}</p>
            </li>
        </ul>
    </div>

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

export default {

    props: [],

    data() {
        return {
            badges: [],
            /*categorizedBadges: {},
            categoryMapping: {
                1: 'Completionist',
                2: 'Bars',
                3: 'Stadiums',
                4: 'Parks',
                5: 'Museums',
                6: 'Restaurants'
            }*/
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

                   /*const badges = response.map((badge) => ({
                    id: badge.badge_id,
                    name: badge.name,
                    description: badge.description,
                    unlocked: badge.unlocked,
                    lockedImage: `../assets/logos/Badges/${badge.locked_image}`,
                    unlockedImage: `../assets/logos/Badges/${badge.unlocked_image}`
                   }));*/

                   this.badges = response.data;

            }).catch(error => {
                console.error('Error fetching badges:', error);
            });

        },

        /*checkUnlockConditions(){
            this.badges.forEach((badge) => {
                if(/*change this to the condition to unlock/ badge.name === /*condition) {
                    badge.unlocked = true;
                }
            });
        }*/

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