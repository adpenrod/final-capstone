<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'maphome' }">Map</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'badge' }">Rewards</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'myhistory' }">My History</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'about' }">About</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>

export default {

}

</script>

<style>

</style>
