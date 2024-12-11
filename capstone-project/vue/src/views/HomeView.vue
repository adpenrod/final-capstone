<template>
  <section class= "hero">
    <img src= "../assets/logos/homepage5.png" alt= "liberty banner" class="banner-image"/>
    <div class="home">
    <router-link to = "/login" class ="login-button"><button>Get Started</button></router-link>
  </div>
  </section>
   <!--make higher resolution image. Possibly remove text from banner. Add images or something pretty to login page.
  -->
    <!--<input type="number" name="iLat" id="">-->
</template>
<script>
import MapDisplay from '../components/MapDisplay.vue';
export default {
  components: {
    MapDisplay
  }
}
</script>
<style>
.hero{
  position: relative;
  text-align: center;
}
.hero img {
  width: 100%;
  height: 100vh;
}
.home{
  position: absolute;
  top: 55%;
  left: 77%;
}
</style>