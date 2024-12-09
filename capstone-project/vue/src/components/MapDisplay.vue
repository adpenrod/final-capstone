<template>
  <div class="dropdown">
    <select v-on:change="onCategoryChange($event)">
      <option value="all">All</option>
      <option v-for="type in types" :key="type.type_id" :value="type.type_id">
        {{ type.name }}

      </option>
    </select>
  </div>

  <div class="map-container">
    <div id="map" ref="map"></div>
  </div>

  <div class="accordion-container">
    <div v-for="(marker, index) in filteredMarkers" :key="index" class="accordion" :id="'accordion-' + index">
      <summary v-on:click="navigateToDirections(marker)">{{ marker.name }}</summary>
      <div class="accordion-content">
        <p>{{ marker.description }}</p>
        <p>Hours: {{ marker.hoursOfOperation }}</p>
        <p>Address: {{ marker.address }}</p>
        <p>Social Media: {{ marker.socialMedia }}</p>
        <img :src="marker.image" alt="Image of attraction">
      </div>
    </div>
  </div>
  
</template>


<script>
import axios from 'axios';
import { Loader } from "@googlemaps/js-api-loader";

let gmarkers1 = [];

export default {

  data() {

    return {
      map: null,
      markers: [],
      markersData: [],
      types: [
        { type_id: 1, name: 'Bars' },
        { type_id: 2, name: 'Museums' },
        { type_id: 3, name: 'Stadiums' },
        { type_id: 4, name: 'Parks' },
        { type_id: 5, name: 'Restaurants' }
      ],
      selectedGroup: "all"
    };
  },

  computed: {

    filteredMarkers() {

      if (this.selectedGroup === "all") {
        return this.markersData;
      }

      return this.markersData.filter(marker => marker.typeId === parseInt(this.selectedGroup));

    },

  },

  mounted() {

    const loader = new Loader({

      apiKey: "AIzaSyBqXUJKJ-biBNEFS4eDEVxPM-elng6ewqw", // API key
      libraries: ["places"],

    });

    loader.load().then(() => {
      this.map = new google.maps.Map(this.$refs.map, {
        center: { lat: 39.9526, lng: -75.1652 }, //Philly
        zoom: 12,
      });

      this.fetchAttractions();
      this.addUserLocation();

    });
  },

  methods: {

    fetchAttractions() {

      axios.get('/attractions/').then(response => {

        this.markersData = response.data.map(attraction => ({

          id: attraction.id,
          name: attraction.name,
          description: attraction.description,
          hoursOfOperation: attraction.hoursOfOperation,
          address: attraction.address,
          socialMedia: attraction.socialMedia,
          image: new URL(`/src/assets/images/${attraction.image}`, import.meta.url).href,
          typeId: attraction.typeId,
          latitude: attraction.latitude,
          longitude: attraction.longitude,

        }));

        this.displayMarkers();

      }).catch(error => {
        console.error("Error fetching attractions:", error);
      });

    },

    createMarker(markerData) {

      const marker = new google.maps.Marker({

        position: { lat: markerData.latitude, lng: markerData.longitude },
        map: this.map,
        title: markerData.name

      });

      marker.category = markerData.typeId;
      gmarkers1.push(marker);

      return marker;

    },

    displayMarkers() {

      gmarkers1.forEach(marker => marker.setMap(null));
      gmarkers1 = [];

      this.filteredMarkers.forEach(markerData => {
        this.createMarker(markerData);

      });

      this.filterMarkers(this.selectedGroup);
    },

    filterMarkers(category) {

      for (let i = 0; i < gmarkers1.length; i++) {
        const marker = gmarkers1[i];

        if (category === "all" || marker.category === parseInt(category)) {
          marker.setVisible(true);
        } else {
          marker.setVisible(false);
        }
      }

    },

    onCategoryChange(event) {
      const category = event.target.value;
      this.selectedGroup = category;
      this.filterMarkers(category);
    },

    /*toggleAccordion(index) {

      const accordion = document.getElementById(`accordion-${index}`);
      if (accordion.open) {
        this.shrinkAccordion(accordion);
      } else {
        this.expandAccordion(accordion);
      }
    },

    expandAccordion(accordion) {

      const content = accordion.querySelector('.accordion-content');
      const summary = accordion.querySelector('summary');
      const startHeight = 0;
      const endHeight = content.scrollHeight + summary.offsetHeight;

      accordion.style.maxHeight = `${startHeight}px`;
      accordion.open = true;

      const animation = accordion.animate({
        maxHeight: [startHeight, endHeight],
      }, {
        duration: 400,
        easing: 'ease-out',
      });

      animation.onfinish = () => {

        accordion.style.maxHeight = '';
      };

    },

    shrinkAccordion(accordion) {

      const content = accordion.querySelector('.accordion-content');
      const summary = accordion.querySelector('summary');
      const startHeight = content.scrollHeight + summary.offsetHeight;
      const endHeight = 0;

      accordion.style.overflow = 'hidden';

      const animation = accordion.animate({
        maxHeight: [startHeight, endHeight],
      }, {
        duration: 400,
        easing: 'ease-out',
      });

      animation.onfinish = () => {
        accordion.style.maxHeight = '';
        accordion.open = false;
      };

    },*/

    addUserLocation() {

      navigator.geolocation.getCurrentPosition((position) => {

        this.userLocation = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        }

        new google.maps.Marker({
          position: this.userLocation,
          map: this.map,
          title: "You are here",
          icon: {
            url: "http://maps.google.com/mapfiles/ms/icons/blue-dot.png",
          },

        });

        this.map.setCenter(this.userLocation);

      },
      );
    },

    navigateToDirections(marker) {

      this.$router.push({
        name: 'mapdirections',
        query: { attractionId: marker.id}
        
      });
    }
  },
};

</script>

<style scoped>
.map-container {
  display: grid;
  grid-template-columns: 5fr;
  grid-template-areas:
    "notmap map map map notmap"
  ;
  width: 100%;
  height: 50vh;
  gap: 10px;
  overflow: hidden;
}

.accordion-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-rows: auto;
  grid-template-areas:
    "map map map map map"
    "accordion accordion accordion accordion accordion"
  ;
  width: 100%;
  height: 100vh;
  gap: 10px;
  overflow: hidden;
}

.dropdown {
  grid-area: sidebar;
  padding: 10px;
}

.map {
  grid-area: map;
  height: 50vh;
  width: 100%;
  position: relative;
}

.accordion {
  background-color: hotpink;
  margin: 10px;
  border-radius: 5px;
  border: 1px solid #8dd792;
  overflow: hidden;
  position: relative;
}

summary {
  background: #8dd792;
  padding: 10px;
  font-weight: bold;
  border-bottom: 2px solid transparent;
}

details[open] summary {
  border-bottom-color: #000;
}

.content {
  padding: 10px;
  border-top: 2px solid #8dd792;
}

p {
  margin: 0;
  padding-bottom: 10px;
}

img {
  max-width: 100%;
  margin-top: 10px;
}
</style>