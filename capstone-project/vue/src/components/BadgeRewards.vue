<template>
  <div class="tab">
    <button class="tablinks" @click="openVenueType($event, 'Museums')">Museums</button>
    <button class="tablinks" @click="openVenueType($event, 'Restaurants')">Restaurants</button>
    <button class="tablinks" @click="openVenueType($event, 'Bars')">Bars</button>
    <button class="tablinks" @click="openVenueType($event, 'Parks')">Parks</button>
    <button class="tablinks" @click="openVenueType($event, 'Stadiums')">Stadiums</button>
  </div>
  <div id="Museums" class="tabcontent">
    <h3>Museums</h3>
    <p>List of museum badges to earn.</p>
    <ul>
        <li v-for="badge in museums" :key="badge.id">{{ badge.name }}</li>
    </ul>
  </div>
  <div id="Restaurants" class="tabcontent">
    <h3>Restaurants</h3>
    <p>List of restaurant badges to earn.</p>
  </div>
  <div id="Bars" class="tabcontent">
    <h3>Bars</h3>
    <p>List of bar badges to earn.</p>
  </div>
  <div id="Parks" class="tabcontent">
    <h3>Parks</h3>
    <p>List of park badges to earn.</p>
  </div>
  <div id="Stadiums" class="tabcontent">
    <h3>Stadiums</h3>
    <p>List of stadium badges to earn.</p>
  </div>
</template>

<script>
import axios from 'axios';
import BadgeService from '../services/BadgeService.js';


export default {

    props: [],

    data() {
        return {
           museums: [],
           restaurants: [],
           bars: [],
           parks: [],
           stadiums: []
        };
    },

    computed: {

    },

    mounted() {

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


        openVenueType(evt, typeName) {

            const tabcontent = document.getElementsByClassName("tabcontent");
            for (let i = 0; i < tabcontent.length; i++){
                tabcontent[i].style.display = "none";
            }

            const tablinks = document.getElementsByClassName("tablinks");
            for (let i = 0; i < tablinks.length; i++){
                tablinks[i].className = tablinks[i].className.replace("active","");
            }

            document.getElementById(typeName).style.display = "block";
            evt.currentTarget.className += "active";
        },

        fetchBadges() {
           
            BadgeService.getBadges().then(response => {
                const badges = response.data;
                this.museums = badges.filter(badge => badge.category === 'Museum');
            }).catch(error => {
                console.error('Error fetching badges:', error);
            })

        }

    }

}

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

.tabcontent{
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

</style>