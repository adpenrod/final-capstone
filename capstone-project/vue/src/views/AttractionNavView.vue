<template>
    <h1>Map Directions</h1>
    <div id="return-nav">
        <router-link v-bind:to="{ name: 'maphome' }">Return to Map</router-link>

    </div>

    <attraction-nav></attraction-nav>
</template>

<script>
import AttractionNav from '../components/AttractionNav.vue';

export default {
  components: { AttractionNav },

    compoents: {
        AttractionNav
    }
}
</script>

<style scoped>

</style>