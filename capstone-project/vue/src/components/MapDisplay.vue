<template>
  <div class="map-container">
    <div class="sidebar">
      <select v-on:change="filterMarkers($event.target.value)">
        <option v-bind:value="'all'">All</option>
        <option v-bind:value="1">Bars</option>
        <option v-bind:value="2">Museums</option>
        <option v-bind:value="3">Stadiums</option>
        <option v-bind:value="4">Parks</option>
        <option v-bind:value="5">Restaurants</option>
      </select>
    </div>
    <div id="map" ref="map"></div>
    <div v-for="(marker, index) in filteredMarkers" :key="index" class="accordion" :id="'accordion-' + index">
      <summary v-on:click="toggleAccordion(index)">{{ marker.title }}</summary>
      <div class="content">
        <p>{{ marker.description }}</p>
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
      markersData: [
        //{ latitude: 39.9656, longitude: -75.1800, title: "Phaladelphia Museum of Art", groupId: 1 },
        //{ latitude: 39.9583, longitude: -75.1726, title: "The Franklin Institute", groupId: 1 },
        //{ latitude: 39.9533, longitude: -75.1766, title: "Mutter Museum", groupId: 1 },
        //{ latitude: 39.9528, longitude: -75.1481, title: "Nattional Constitution Center", groupId: 1 },
        //{ latitude: 39.9489, longitude: -75.1467, title: "Museum of the American Revolution", groupId: 1 },
//
        //{ latitude: 39.9062, longitude: -75.1675, title: "Citizens Bank Park", groupId: 2 },
        //{ latitude: 39.9008, longitude: -75.1675, title: "Lincoln Financial Field", groupId: 2 },
        //{ latitude: 39.9012, longitude: -75.1720, title: "Wells Fargo Center", groupId: 2 },
        //{ latitude: 39.9523, longitude: -75.1905, title: "Franklin Field", groupId: 2 },
        //{ latitude: 40.0171, longitude: -75.1527, title: "Marcus Foster Memorial Stadium", groupId: 2 },
//
        //{ latitude: 39.9486, longitude: -75.1715, title: "Rittenhouse Square", groupId: 3 },
        //{ latitude: 39.9042, longitude: -75.1809, title: "FDR Park", groupId: 3 },
        //{ latitude: 39.9449, longitude: -75.1413, title: "Spruce Street Harbor Park", groupId: 3 },
        //{ latitude: 39.9510, longitude: -75.1684, title: "John F. Collins Park", groupId: 3 },
        //{ latitude: 39.9479, longitude: -75.1513, title: "Washington Square Park", groupId: 3 },
//
        //{ latitude: 39.9721, longitude: -75.1286, title: "Bastia", groupId: 4 },
        //{ latitude: 39.9456, longitude: -75.1677, title: "Rex at the Royal", groupId: 4 },
        //{ latitude: 39.9425, longitude: -75.1450, title: "Provenance", groupId: 4 },
        //{ latitude: 39.9478, longitude: -75.1624, title: "Little Nonna's", groupId: 4 },
//
        //{ latitude: 39.9506, longitude: -75.1620, title: "McGillin's Olde Ale House", groupId: 5 },
        //{ latitude: 39.9475, longitude: -75.1444, title: "Khyber Pass Pub", groupId: 5 },
        //{ latitude: 39.9583, longitude: -75.1703, title: "Assembly Rooftop Lounge", groupId: 5 },
        //{ latitude: 39.9473, longitude: -75.1439, title: "48 Record Bar", groupId: 5 },
        //{ latitude: 39.9496, longitude: -75.1503, title: "Independence Beer Garden", groupId: 5 },
      ],
      selectedGroup: "all",
    };
  },
  computed: {

    filteredMarkers() {
      let result;
      if (this.selectedGroup === "all") {
        result = this.markersData;
      } else {
        result = this.markersData.filter(marker => marker.typeId === parseInt(this.selectedGroup));
      }
      return result;
    },

  },

  mounted() {
    const loader = new Loader({
      apiKey: "AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg", // Replace with your API key
      libraries: ["places"], // Include the Places library
    });

    loader.load().then(() => {
      this.map = new google.maps.Map(this.$refs.map, {
        center: { lat: 39.9526, lng: -75.1652 }, // Philly coordinates
        zoom: 12,
      });

      this.fetchAttractions();

      // Add event listener for pothole marker creation
      //.map.addListener("click", (event) => {
      //this.createAttractionMarker(event.latLng);
      //});
    });
  },
  methods: {
    //createAttractionMarker(location) {
    // google.maps.Marker({
    //position: location,
    //map: this.map,
    //title: "Attraction",
    //});

    // Send POST request to your backend to update the database
    //this.$axios.post("/attractions", {
    //latitude: location.lat(),
    //longitude: location.lng(),
    //});
    //},

    async fetchAttractions() {
      try {
        const response = await axios.get('/attractions/', {
          params: { userInput: this.selectedGroup == 'all' ? '' : this.selectedGroup }
        });
        this.markersData = response.data;
        this.displayMarkers();
      } catch (error) {
        console.error('Error fetching attractions:', error);
      }
    },

    createMarker(markerData) {
      return new google.maps.Marker({
        position: { lat: markerData.latitude, lng: markerData.longitude },
        map: this.map,
        title: markerData.title
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
      this.fetchAttractions();
    },

    toggleAccordion(index) {
      const accordion = document.getElementById('accordion-' + index);
      const content = accordion.querySelector('.content');
      const summary = accordion.querySelector('summary');

      if (accordion.open) {
        this.shrinkAccordion(accordion, content, summary);
      } else {
        this.expandAccordion(accordion, content, summary);
      }
    },

    expandAccordion(accordion, content, summary) {
      const startHeight = `${accordion.offsetHeight}px`;
      const endHeight = `${summary.offsetHeight + content.offsetHeight}px`;

      accordion.style.height = startHeight;
      accordion.open = true;

      const animation = accordion.animate({
        height: [startHeight, endHeight],
      }, {
        duration: 400,
        easing: 'ease-out',
      });

      animation.onfinish = () => {
        accordion.style.height = '';
        accordion.style.overflow = '';
      };
    },

    shrinkAccordion(accordion, content, summary) {
      const startHeight = `${accordion.offsetHeight}px`;
      const endHeight = `${summary.offsetHeight}px`;

      accordion.style.overflow = 'hidden';

      const animation = accordion.animate({
        height: [startHeight, endHeight],
      }, {
        duration: 400,
        easing: 'ease-out',
      });

      animation.onfinish = () => {
        accordion.style.height = '';
        accordion.style.overflow = '';
      };
    },
  },
};
</script>

<style scoped>
.map-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  grid-template-areas: 
    ".sidebar #map .accordion .accordion .accordion"
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

details[open] summary{
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