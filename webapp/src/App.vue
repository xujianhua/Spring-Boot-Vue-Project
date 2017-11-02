<template>
  <div id="app">
    <HeaderComponent></HeaderComponent>
    <router-view/>
    <FooterComponent></FooterComponent>
  </div>
</template>

<script>
import HeaderComponent  from './components/common/Header.vue'
import FooterComponent from './components/common/Footer.vue'

export default {
  name: 'app',
  components: {
  	HeaderComponent, 
  	FooterComponent
  }   
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
