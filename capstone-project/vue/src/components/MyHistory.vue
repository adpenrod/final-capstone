<template>

    <div>
        <h3>My Check-ins</h3>
    </div>
  
    <div id="Museums" class="tabcontent">
      <h3>Museums</h3>
      <p>List of museum badges to earn.</p>
      <ul>
          <li v-for="badge in museums" :key="badge.id">
              <h4>{{ badge.name }}</h4> 
              <p>{{ badge.description }}</p>
          </li>
      </ul>
    </div>
  
    <div id="Restaurants" class="tabcontent">
      <h3>Restaurants</h3>
      <p>List of restaurant badges to earn.</p>
      <ul>
          <li v-for="badge in restaurants" :key="badge.id">
              <h4>{{ badge.name }}</h4> 
              <p>{{ badge.description }}</p>
          </li>
      </ul>
    </div>
  
    <div id="Bars" class="tabcontent">
      <h3>Bars</h3>
      <p>List of bar badges to earn.</p>
      <ul>
          <li v-for="badge in bars" :key="badge.id">
              <h4>{{ badge.name }}</h4> 
              <p>{{ badge.description }}</p>
          </li>
      </ul>
    </div>
  
    <div id="Parks" class="tabcontent">
      <h3>Parks</h3>
      <p>List of park badges to earn.</p>
      <ul>
          <li v-for="badge in parks" :key="badge.id">
              <h4>{{ badge.name }}</h4> 
              <p>{{ badge.description }}</p>
          </li>
      </ul>
    </div>
  
    <div id="Stadiums" class="tabcontent">
      <h3>Stadiums</h3>
      <p>List of stadium badges to earn.</p>
      <ul>
          <li v-for="badge in stadiums" :key="badge.id">
              <h4>{{ badge.name }}</h4> 
              <p>{{ badge.description }}</p>
          </li>
      </ul>
    </div>
  
  </template>
  
  <script>
  import BadgeService from '../services/BadgeService.js';
  import CheckinService from '../services/CheckinService';
  
  
  export default {
  
      data() {
          return {
            checkins: [],
          };
      },
  
      computed: {
  
      },
  
      mounted() {
  
          this.fetchCheckins();
  
      },
  
      methods: {
  
          fetchCheckins() {
             
              CheckinService.getCheckinByUserId(this.userId).then(response => {
  
                  this.checkins = response.data;

              }).catch(error => {
                  console.error('Error fetching check-ins:', error);
              });
  
          }
  
      }
  
  };
  
  </script>
  <!-- 
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
  -->