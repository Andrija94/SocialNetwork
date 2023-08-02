<template>
  <div>
    <router-view/>
  </div>
</template>


<script setup lang="js">
import {defineComponent} from "vue";

export default defineComponent({
  name: "App",
  data: function () {
    return {
      showNavBar: false,
    }
  }
})

</script>
<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
