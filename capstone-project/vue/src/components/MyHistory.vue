<template>

    <div>
        <h3>My Check-ins</h3>
        <ul class="checkins-list">
            <li v-for="checkin in checkins" :key="checkin.checkinId" class="checkin-item">
                <p>Attraction: {{ getAttractionName(checkin.attractionId) }} </p>
                <p>Check-in Time: {{ new Date(checkin.checkinTime).toLocaleString() }}</p>
            </li>
        </ul>
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