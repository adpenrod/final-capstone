<template>
    <h1>Directions To Location</h1>
    <div class="return-nav">
        <router-link class="general-link" v-bind:to="{ name: 'maphome' }">Return to Attraction Map</router-link>


    </div>
    <attraction-nav></attraction-nav>
</template>

<script>
import AttractionNav from '../components/AttractionNav.vue';

export default {
  components: { AttractionNav },

  
}
</script>

<style scoped>

.return-nav{
    text-align: center;
}

</style>