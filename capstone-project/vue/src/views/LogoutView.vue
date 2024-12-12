<template>
  <section class= "hero">
    <img src= "../assets/logos/logout_page.png" alt= "liberty banner" class="banner-image"/>
    <div class="logout">
     
    <router-link to = "/"><button class ="logout-button" >Home</button></router-link>
  </div>
  </section>

</template>


<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    //this.$router.push("/login");
  }
};
</script>


<style scoped>

.logout-button {
  background-color: #F9F7DF;
  color: #6E3F13;
  padding: 10px 40px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Georgia', serif;
  font-size: 1rem;
 
}

.logout-button:hover {
  background-color: #EB473D;
  color: #F9F7DF;
}

.hero{
  position: relative;
  text-align: center;
}

.hero img {
  width: 100%;
  height: 100vh;
}

.logout{
  position: absolute;
  top: 55%;
  right: 80%;
}

</style>



