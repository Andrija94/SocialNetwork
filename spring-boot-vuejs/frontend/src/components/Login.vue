<template>
  <div class="form-wrapper">

    <div>
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">Username</label>
        <input v-model="username" type="text" class="form-control" id="inputUser"
               aria-describedby="emailHelp">
        <div v-if="showErrorUsername" id="usernameMesage" class="form-text" style="color: red">Username must be 4 or
          more characters!
        </div>
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">Password</label>
        <input v-model="password" type="password" class="form-control" id="inputPassword">
        <div v-if="showErrorPassword" id="passwordMesage" class="form-text" style="color: red">Password must be 8 or
          more characters!
        </div>
      </div>
      <div class="mb-3 form-check">
      </div>
      <button v-on:click="goToRegister" type="submit" class="register btn btn-primary">Register</button>
      <button v-on:click="login" type="submit" class="btn btn-primary">Login</button>
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
      user: null,
      showErrorUsername: false,
      showErrorPassword: false
    }
  },
  methods: {
    checkInput() {
      const userInput = document.querySelector("#inputUser");
      const passInput = document.querySelector("#inputPassword");
      if (this.username.length > 3 && this.password.length > 7) {
        this.showErrorUsername = false;
        this.showErrorPassword = false;
        return true;
      } else if (this.username.length <= 3 && this.password.length > 7) {
        userInput.style.borderColor = "red";
        this.showErrorUsername = true;
        this.showErrorPassword = false;
        return false;
      }
      else if (this.username.length > 3 && this.password.length <= 7) {
        passInput.style.borderColor = "red";
        this.showErrorUsername = false;
        this.showErrorPassword = true;
        return false;
      }
      else {
        userInput.style.borderColor = "red";
        passInput.style.borderColor = "red";
        this.showErrorUsername = true;
        this.showErrorPassword = true;
      }
    },
    login() {
      if (this.checkInput())
        return api.login(this.username, this.password)
            .then(response => {
              this.user = response.data;
              if (response.status === 200) {
                router.push("/home/" + this.user.username);
                localStorage.setItem("loggedIn","true")
              }
            })
            .catch(error => {
              if (error.response.status === 401) {
                alert("Incorrect username or password!")
              }
            })
    },
    goToRegister () {
      router.push("/register")
    }
  }
})
</script>


<style>
.form-wrapper {
  width: 400px;
  margin: 200px auto auto;
}

.btn {
  width: 100px;
  margin: 0 5px;
}
</style>