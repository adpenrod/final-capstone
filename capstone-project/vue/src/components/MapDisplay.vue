<template>
  <div class="map-container">
    <div class="dropdown">
      <select v-on:change="filterMarkers($event.target.value)">
        <option value="all">All</option>
        <option v-for="type in types" :key="type.type_id" :value="type.type_id">
          {{ type.name }}
        </option>
      </select>
    </div>
    <div id="map" ref="map"></div>
    <div v-for="(marker, index) in filteredMarkers" :key="index" class="accordion" :id="'accordion-' + index">
      <summary v-on:click="toggleAccordion(index)">{{ marker.name }}</summary>
      <div class="accordion-content">
        <p>{{ marker.description }}</p>
        <p>Hours: {{ marker.hoursOfOperation }}</p>
        <p>Address: {{ marker.address }}</p>
        <p>Social Media: {{ marker.socialMedia }}</p>
        <img :src="marker.imageUrl" alt="Image of attraction">
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';
import { Loader } from "@googlemaps/js-api-loader";

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
      selectedGroup: "all",
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

      apiKey: "AIzaSyBqXUJKJ-biBNEFS4eDEVxPM-elng6ewqw", // Replace with your API key
      libraries: ["places"], // Include the Places library
    });

    loader.load().then(() => {
      this.map = new google.maps.Map(this.$refs.map, {
        center: { lat: 39.9526, lng: -75.1652 }, // Philly coordinates
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
          imageUrl: attraction.imageUrl,
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

      return new google.maps.Marker({

        position: { lat: markerData.latitude, lng: markerData.longitude },
        map: this.map,
        title: markerData.name

      });

    },

    displayMarkers() {

      this.markers.forEach(marker => marker.setMap(null));
      this.markers = [];
      this.filteredMarkers.forEach(markerData => {
        const marker = this.createMarker(markerData);
        this.markers.push(marker);

      });
    },

    filterMarkers(typeId) {

      this.selectedGroup = typeId;
      this.displayMarkers();

    },

    toggleAccordion(index) {

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

    },

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
  },
};

</script>

<style scoped>
.map-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-rows: auto;
  grid-template-areas:
    "#map #map .accordion .accordion .accordion"
    ". . .accordion .accordion .accordion"
  ;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

#map {
  flex: 2;
  height: 100vh;
  width: auto;
}

.sidebar {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
  background-color: #d8dde2e0;
  height: 100vh;
  width: 20%;
  box-sizing: border-box;
}

.accordion {
  flex-direction: column;
  background-color: hotpink;
  margin-bottom: 10px;
  border-radius: 5px;
  overflow: hidden;
  border: 1px solid #8dd792;
}

summary {
  background: #8dd792;
  padding: 10px;
  font-weight: bold;
  border-bottom: 2px solid transparent;
  outline: none;
  margin: 0;
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