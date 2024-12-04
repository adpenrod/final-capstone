<template>
    <div id="map" ref="map"></div>
  </template>

<script>
import { Loader } from "@googlemaps/js-api-loader";

export default {
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    const loader = new Loader({
      apiKey: "AIzaSyB41DRUbKWJHPxaFjMAwdrzWzbVKartNGg", // Replace with your API key
      libraries: ["places"], // Include the Places library
    });

    loader.load().then(() => {
      this.map = new google.maps.Map(this.$refs.map, {
        center: { lat: 41.4993, lng: -81.6944 }, // Cleveland coordinates
        zoom: 12,
      });

      // Add event listener for pothole marker creation
      this.map.addListener("click", (event) => {
        this.createAttractionMarker(event.latLng);
      });
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
  },
};
</script>

<style scoped>

#map {
    height: 30vh;
    
}



</style>