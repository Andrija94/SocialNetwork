<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">{{ username }}</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a v-on:click="goToFeed" class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">My Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Photos</a>
          </li>
          <li class="nav-item">
            <a v-on:click="goToPosts" class="nav-link" href="#">Posts</a>
          </li>
        </ul>
        <form class="d-flex">
          <input class="form-control me-2" type="search" placeholder="Find friends" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      </div>
    </div>
  </nav>
</template>


<script lang="js">
import {defineComponent} from 'vue';
import router from "@/router";

export default defineComponent({

  name: "Navbar",

  props : {
    username: String
  },


  methods: {
    goToPosts () {
      router.push("/post/" + router.currentRoute.value.params.username)
    },

    goToFeed() {
      router.push("/home/" + router.currentRoute.value.params.username)
    }
  }

})
</script>


<style>

</style>