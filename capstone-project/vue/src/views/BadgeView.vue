<template>
  <div class="badge">
    <h1>Badges</h1>
    <p>You must be authenticated to see this</p>
    <BadgeRewards />
  </div>
  
</template>
  
<script>
import BadgeRewards from "../components/BadgeRewards.vue";

export default {

  components: {
    BadgeRewards
  }

};
</script>
