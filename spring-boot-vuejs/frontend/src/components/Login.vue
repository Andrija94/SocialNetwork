<template>
  <div class="form-wrapper">
    <div>
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Username</label>
        <input v-model="username" type="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input v-model="password" type="password" class="form-control" id="exampleInputPassword1">
      </div>
      <div class="mb-3 form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
      </div>
      <button v-on:click="login" type="submit" class="btn btn-primary">Submit</button>
    </div>
  </div>
</template>


<script lang="js">
import {defineComponent} from "vue";
import api from "../api/backend-api";
import router from "@/router";

export default defineComponent({
  name: "Login",
  data: function () {
    return {
      username: "",
      password: "",
      user: null
    }
  },
  methods: {
    login() {
      console.log(this.username, "username")
      console.log(this.password, "password")
      return api.login(this.username, this.password)
          .then(response => {
            this.user = response.data;
            console.log(response, "Response")
            if(response.status === 200){
              router.push("/home");
            }
          })
          .catch(error => {
            if (error.response.status === 401) {
              alert("Incorrect username or password!")
            }
          })
    }
  }
})
</script>


<style>
  .form-wrapper {
    width: 400px;
    margin: auto;
    margin-top: 200px;
  }
</style>