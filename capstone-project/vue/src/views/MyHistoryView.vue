<template>
    <div class="myhistory">
      <h1>Check-In History</h1>
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