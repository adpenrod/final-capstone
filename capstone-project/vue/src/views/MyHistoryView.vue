<template>
    <div class="myhistory">
      <h1>My History</h1>
      <p>You must be authenticated to see this</p>
    </div>
    <BadgeHistory/>
  </template>
  
  <script>
  import BadgeHistory from "../components/BadgeHistory.vue";

  export default {

    components: {
      BadgeHistory
    }

  };
  </script>