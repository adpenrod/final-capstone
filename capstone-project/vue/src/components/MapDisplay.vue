<template>
  <div class="map-container">
    <div class="sidebar">
      <select v-on:change="filterMarkers($event.target.value)">
        <option v-bind:value="'all'">All</option>
        <option v-bind:value="1">Museums</option>
        <option v-bind:value="2">Stadiums</option>
        <option v-bind:value="3">Parks</option>
        <option v-bind:value="4">Restaurants</option>
        <option v-bind:value="5">Bars</option>
      </select>
      <ul>
        <li v-for="marker in filteredMarkers" v-bind:key="marker.title">
          <!--Direct to directions page-----<router-link v-bind:to="">{{ marker.title }}</router-link>-->
        </li>
      </ul>
    </div>
    <div id="map" ref="map"></div>
  </div>
</template>

<script>
import { Loader } from "@googlemaps/js-api-loader";

export default {
  data() {
    return {
      map: null,
      markers: [],
      markersData: [
        { lat: 39.9656, lng: -75.1800, title: "Phaladelphia Museum of Art", groupId: 1 },
        { lat: 39.9583, lng: -75.1726, title: "The Franklin Institute", groupId: 1 },
        { lat: 39.9533, lng: -75.1766, title: "Mutter Museum", groupId: 1 },
        { lat: 39.9528, lng: -75.1481, title: "Nattional Constitution Center", groupId: 1 },
        { lat: 39.9489, lng: -75.1467, title: "Museum of the American Revolution", groupId: 1 },

        { lat: 39.9062, lng: -75.1675, title: "Citizens Bank Park", groupId: 2 },
        { lat: 39.9008, lng: -75.1675, title: "Lincoln Financial Field", groupId: 2 },
        { lat: 39.9012, lng: -75.1720, title: "Wells Fargo Center", groupId: 2 },
        { lat: 39.9523, lng: -75.1905, title: "Franklin Field", groupId: 2 },
        { lat: 40.0171, lng: -75.1527, title: "Marcus Foster Memorial Stadium", groupId: 2 },

        { lat: 39.9486, lng: -75.1715, title: "Rittenhouse Square", groupId: 3 },
        { lat: 39.9042, lng: -75.1809, title: "FDR Park", groupId: 3 },
        { lat: 39.9449, lng: -75.1413, title: "Spruce Street Harbor Park", groupId: 3 },
        { lat: 39.9510, lng: -75.1684, title: "John F. Collins Park", groupId: 3 },
        { lat: 39.9479, lng: -75.1513, title: "Washington Square Park", groupId: 3 },

        { lat: 39.9721, lng: -75.1286, title: "Bastia", groupId: 4 },
        { lat: 39.9456, lng: -75.1677, title: "Rex at the Royal", groupId: 4 },
        { lat: 39.9425, lng: -75.1450, title: "Provenance", groupId: 4 },
        { lat: 39.9478, lng: -75.1624, title: "Little Nonna's", groupId: 4 },

        { lat: 39.9506, lng: -75.1620, title: "McGillin's Olde Ale House", groupId: 5 },
        { lat: 39.9475, lng: -75.1444, title: "Khyber Pass Pub", groupId: 5 },
        { lat: 39.9583, lng: -75.1703, title: "Assembly Rooftop Lounge", groupId: 5 },
        { lat: 39.9473, lng: -75.1439, title: "48 Record Bar", groupId: 5 },
        { lat: 39.9496, lng: -75.1503, title: "Independence Beer Garden", groupId: 5 },
      ],
      selectedGroup: "all",
    };
  },
  computed: {

    filteredMarkers(){
      let result;
      if(this.selectedGroup === "all"){
        result = this.markersData;
      } else {
        result = this.markersData.filter(marker => marker.groupId === this.selectedGroup);
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

      this.displayMarkers("all");

      // Add event listener for pothole marker creation
      //.map.addListener("click", (event) => {
      //this.createAttractionMarker(event.latLng);
      //});
    });
  },
  methods: {
    createAttractionMarker(location) {
      new google.maps.Marker({
        position: location,
        map: this.map,
        title: "Attraction",
      });

      // Send POST request to your backend to update the database
      this.$axios.post("/attractions", {
        latitude: location.lat(),
        longitude: location.lng(),
      });
    },

    createMarker(markerData) {
      return new google.maps.Marker({
        position: { lat: markerData.lat, lng: markerData.lng },
        map: this.map,
        title: markerData.title
      });
    },

    displayMarkers(groupId) {

      this.markers.forEach(marker => marker.setMap(null));
      this.markers = [];

      let filteredData;
      if (groupId === "all") {
        filteredData = this.markersData;
      } else {
        filteredData = this.markersData.filter(marker => marker.groupId === groupId);
      }


      filteredData.forEach(markerData => {
        const marker = this.createMarker(markerData);
        this.markers.push(marker);
      });

    },

    filterMarkers(groupId) {
      this.selectedGroup = groupId;
      this.displayMarkers(groupId);
    }
  },
};
</script>

<style scoped>

.map-container{
  display: flex;
  width: 100%;
  height: 100vh;
}

.sidebar {
  width: 25%;
  padding: 10px;
  background-color: #f8f9fa;
  overflow-y: auto;
}

#map {
  height: 50vh;
  width: 50vw;
}
</style>