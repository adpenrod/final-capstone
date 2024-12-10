<template>
    <div class="myhistory">
      <h1>My History</h1>
      <p>You must be authenticated to see this</p>
    </div>
    <MyHistory/>
  </template>
  
  <script>
  import MyHistory from "../components/MyHistory.vue";

  export default {

    components: {
      MyHistory
    }

  };
  </script>