<template>
  <div id="capstone-app">

    <div class="tab">
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'home'}" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'logout'}" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'login'}" v-bind:to="{ name: 'login' }" v-else >Login</router-link>
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'maphome'}" v-bind:to="{ name: 'maphome' }">Map</router-link>
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'badge'}" v-bind:to="{ name: 'badge' }">Rewards</router-link>
      <router-link class="tablinks" v-bind:class="{active: $route.name === 'myhistory'}" v-bind:to="{ name: 'myhistory' }">My History</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>

export default {


 


}


</script>

<style scoped>

.tab{
  overflow: hidden;
  border: 1px solid #cccccc;
  background-color: #f1f1f1;
  display: flex;
  justify-content: center;
}

.tab button{
  background-color: #f1f1f1;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 16px;
  color: #333333;
}

.tab button:hover{
  background-color: #dddddd;
}

.tab .tablinks{
  padding: 10px 20px;
  font-size: 16px;
  background-color: transparent;
  cursor: pointer;
}

.tab .tablinks:hover{
  background-color: azure;
  color:#f1f1f1
}

</style>
