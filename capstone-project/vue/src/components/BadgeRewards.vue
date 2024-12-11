<template>
   

  <div class="badge-container" v-for="badge in badges" :key="badge.id">

    <img
    v-if="!badge.unlocked"
    :src="badge.lockedImage"
    :alt="`Locked badge: ${badge.name}`"
    class="badge-image"
    >

    <img
    v-else
    :src="badge.unlockedImage"
    :alt="`Unlocked badge: ${badge.name}`"
    class="badge-image"
    >

    <p>{{ badge.name }}</p>
</div>

    <div id="All" class="tabcontent">
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
    </div>
</template>

<script>
import axios from 'axios';
import BadgeService from '../services/BadgeService.js';

const unlocked = true;

export default {

    props: [],

    data() {
        return {
            badges: [
                {
                Id:1,
                name:"Defender of The Land",
                description: "For those who have visited every location!",
                unlocked: false,
                lockedImage: "../assets/logos/Badges/liberty_walk_-_unearned_-_dol_480.png",
                unlockedImage: "../assets/logos/Badges/liberty_walk_-_earned_-_dol_480.png",
                },
                {
                    id:2,
                    name:"Bar Hopper",
                    description: "For those who have visited every bar!",
                    unlocked: false,
                    lockedImage:"../assets/logos/Badges/liberty_walk_-_unearned_-_bh_480.png",
                    unlockedImage: "../assets/logos/Badges/liberty_walk_-_earned_-_bh_480.png"
                },
                {
                    id:3,
                    name:"Sports Enthusiast",
                    description: "For those who have visited every stadium!",
                    unlocked: false,
                    lockedImage: "../assets/logos/Badges/liberty_walk_-_unearned_-_se_480.png",
                    unlockedImage: "../assets/logos/Badges/liberty_walk_-_earned_-_se_480.png"
                },
                {
                    id:4,
                    name:"Tree Hugger",
                    description: "For those who have visited every park!",
                    unlocked: false,
                    lockedImage: "../assets/logos/Badges/liberty_walk_-_unearned_-_th_480.png",
                    unlockedImage: "../assets/logos/Badges/liberty_walk_-_earned_-_th_480.png"
                },
                {
                    id:5,
                    name: "Art Enthusiast",
                    description: "For those who have visited every museum!",
                    unlocked: false,
                    lockedImage: "../assets/logos/Badges/liberty_walk_-_unearned_-_ae_480.png",
                    unlockedImage: "../assets/logos/Badges/liberty_walk_-_earned_-_ae_480.png"
                },
                {
                    id:6,
                    name:"Culinary Conqueror",
                    description: "For those who have visited every restaurant",
                    unlocked: false,
                    lockedImage: "../assets/logos/Badges/liberty_walk_unearned_480.png",
                    unlockedImage: "../assets/logos/Badges/liberty_walk_earned_480.png"
                }
            ],
            museums: [],
            restaurants: [],
            bars: [],
            parks: [],
            stadiums: [],
            completionist: [],
            categoryMapping: {
                1: 'Completionist',
                2: 'Bars',
                3: 'Stadiums',
                4: 'Parks',
                5: 'Museums',
                6: 'Restaurants'
            }
        };
    },

    computed: {

        museumBadges() {
            return this.badges.find(badge => badge.id === 5);
        },

    },

    mounted() {
        this.checkUnlockConditions();

        const museumsTab = document.getElementById('Museums');
        const firstTabLink = document.querySelector('.tablinks');

        if (museumsTab) {
            museumsTab.style.display = 'block';
        }
        if (firstTabLink) {
            firstTabLink.className += ' active';
        }

        this.fetchBadges();

    },

    methods: {


        fetchBadges() {

            BadgeService.getBadges().then(response => {

                   const badges = response.data;

                   this.badges = badges;

                 this.museums = badges.filter(badge => this.categoryMapping[badge.id] === 'Museums');
                  this.restaurants = badges.filter(badge => this.categoryMapping[badge.id] === 'Restaurants');
                  this.bars = badges.filter(badge => this.categoryMapping[badge.id] === 'Bars');
                  this.parks = badges.filter(badge => this.categoryMapping[badge.id] === 'Parks');
                  this.stadiums = badges.filter(badge => this.categoryMapping[badge.id] === 'Stadiums');
                  this.completionist = badges.filter(badge => this.categoryMapping[badge.id] === 'Completionist');


            }).catch(error => {
                console.error('Error fetching badges:', error);
            });

        },
        checkUnlockConditions(){
            this.badges.forEach((badge) => {
                if(unlocked /*change this to the condition to unlock */) {
                    badge.unlocked = true;
                }
            });
        }

    }

};

</script>

<style scoped>
.tab {
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

.tabcontent {
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