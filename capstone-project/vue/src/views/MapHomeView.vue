<template>
    <div class="maphome">
      <h1>Map Home</h1>
      <router-link v-bind:to="{ name: 'mapdata' }">Map Data</router-link><br>
      <router-link v-bind:to="{ name: 'mapdirections' }">Map Directions</router-link>
    </div>
  </template>
  
  <script>
  export default {
  };
  </script>