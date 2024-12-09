<template>
  <div id="capstone-app">

    <div id="nav">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </div>

    <div class="tab">
      <button class="tablinks" :class="{active: activeTab === 'Home'}" v-on:click="setActiveTab('Home')">Home</button>
      <button class="tablinks" :class="{active: activeTab === 'Logout'}" v-if="$store.state.token != ''" v-on:click="setActiveTab('Logout')">Logout</button>
      <button class="tablinks" :class="{active: activeTab === 'Login'}" v-else v-on:click="setActiveTab('Login')">Login</button>
      <button class="tablinks" :class="{active: activeTab === 'Map'}" v-on:click="setActiveTab('Map')">Map</button>
      <button class="tablinks" :class="{active: activeTab === 'Rewards'}" v-on:click="setActiveTab('Rewards')">Rewards</button>
      <button class="tablinks" :class="{active: activeTab === 'History'}" v-on:click="setActiveTab('History')">My History</button>
    </div>

    <div class="tabcontent">
      <component :is="currentTabComponent"></component>
    </div>
    
  </div>
</template>

<script>
import HomeView from './views/HomeView.vue';
import LoginView from './views/LoginView.vue';
import MapHomeView from './views/MapHomeView.vue';
import BadgeView from './views/BadgeView.vue';
import MyHistoryView from './views/MyHistoryView.vue';


export default {

  data(){
    return {
      activeTab: 'Home',
    };
  },

  computed: {
    currentTabComponent(){

      const tabComponents = {
        Home: HomeView,
        Login: LoginView,
        Map: MapHomeView,
        Rewards: BadgeView,
        History: MyHistoryView
      };
      return tabComponents[this.activeTab];
    }
  },

  methods: {
    setActiveTab(tabName) {
      this.activeTab = tabName;
    },
  },


 


};


</script>

<style scoped>

.tab{
  overflow: hidden;
  border: 1px solid #cccccc;
  background-color: #f1f1f1;
  display: flex;
  justify-content: center;
}

.tab .tablinks{
  background-color: #f1f1f1;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  font-size: 16px;
  color: #333333;
}

.tab .tablinks:hover{
  background-color: #dddddd;
}

.tab .tablinks.active {
  background-color: #cccccc;
  color: #000000;
  font-weight: bold;
}

.tabcontent{
  padding: 16px;
  border: 1px solid #cccccc;
  border-top: none;
  background-color: #ffffff;
  color: #333333;
}

/*@media (max-width: 768px){
  .tab {
    flex-direction: column;
    align-items: stretch;
  }

  .tab button{
    float: none;
    width: 100%;
    text-align: center;
  }
}*/

</style>
