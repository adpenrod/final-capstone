<template>
    <div class="maphome">
      <h1>Map Home</h1>
      <router-link v-bind:to="{ name: 'mapdata' }">Map Data</router-link><br>
      <router-link v-bind:to="{ name: 'mapdirections' }">Map Directions</router-link>

      

    </div>

    <iframe
        width="800"
        height="650"
        style="border:0"
        loading="lazy"
        allowfullscreen
        referrerpolicy="no-referrer-when-downgrade"
        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBqXUJKJ-biBNEFS4eDEVxPM-elng6ewqw
          &q=Cleveland+OH">
      </iframe>
      
  </template>
  
  <script>
  export default {

    
  };
  </script>