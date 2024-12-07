<template>
  <div class="home">
    <h1>Home</h1>
    <p>You can either login/register or feel free to use our interactive map!</p>

    <!--<input type="number" name="iLat" id="">-->

    <AttractionMap :latitude="(39.9526)" :longitude="(-75.1652)"></AttractionMap>



  </div>
</template>

<script>

import MapDisplay from '../components/MapDisplay.vue';

export default {

  components: {
    MapDisplay
  }
  
}

</script>
