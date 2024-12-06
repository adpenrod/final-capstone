<template>
    <div class="mapdirections">
      <h1>Map Directions</h1>
      <p>Hope you found your way</p>
    </div>

    <AttractionMap/>

  </template>
  
  <script>
  import AttractionMap from '../components/AttractionMap.vue';


  export default {

    components:{
      AttractionMap
    }
  };
  </script>