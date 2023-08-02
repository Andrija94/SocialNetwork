<template>
  <div class="container" style="width: 500px">
    <div>
      <div class="mb-3">
        <input v-model="user.username" type="text" class="form-control" id="inputUser"
               aria-describedby="emailHelp" placeholder="Username" v-on:input="validateUsername">
      </div>
      <div v-if="showErrorUsername" id="usernameMesage" class="form-text row" style="color: red; margin-top: 20px">
        Username must be 4 or
        more characters!
      </div>
      <div class="row g-3">
        <div class="col">
          <input v-model="user.firstName" type="text" class="form-control" placeholder="First name"
                 aria-label="First name" v-on:input="btnEnable">
        </div>
        <div class="col">
          <input v-model="user.lastName" type="text" class="form-control" placeholder="Last name"
                 aria-label="Last name" v-on:input="btnEnable">
        </div>
      </div>
    </div>
    <div class="input-group mb-3">
      <input v-model="user.email" type="text" class="form-control" placeholder="Email" aria-label="Email"
             aria-describedby="basic-addon2" id="emailInput" v-on:input="validateEmail">
      <span class="input-group-text" id="basic-addon2">@example.com</span>
    </div>
    <div v-if="showErrorEmail" id="passwordMesage" class="form-text" style="color: red; margin-left: -180px">Email
      must be of format example@email.com
    </div>
    <div class="pass-container">
      <div class="row g-3 align-items-center">
        <div class="col-auto">
          <label for="inputPassword6" class="col1 col-form-label">Password</label>
        </div>
        <div class="col-auto">
          <input v-model="user.password" type="password" id="inputPassword" class="form-control"
                 aria-describedby="passwordHelpInline" v-on:input="validatePassword">
        </div>
        <div v-if="showErrorPassword" id="passwordMesage" class="col-auto form-text" style="color: red">Password must be
          8 or
          more characters!
        </div>
        <div class="col-auto">
        </div>
        <div class="row g-3 align-items-center">
          <div class="col-auto">
            <label for="inputPassword6" class="col-form-label">Confirm Password</label>
          </div>
          <div class="col-auto">
            <input v-model="passwordConfirm" type="password" id="inputPasswordConfirm" class="form-control"
                   aria-describedby="passwordHelpInline">
            <span v-if="showErrorPassConfirm" id="passwordMesage" class="form-text" style="color: red">Enter same password!
          </span>
          </div>
          <div class="col-auto">
          </div>
        </div>
      </div>
    </div>
    <div>
      <select v-model="user.gender" class="form-select" aria-label="Default select example" v-on:input="btnEnable">
        <option value="male">Male</option>
        <option value="female">Female</option>
      </select>
    </div>
    <div class="date-container">
      <div class="row g-3 align-items-center date">
        <div class="col-auto">
          <label for="inputPassword6" class="col1 col-form-label">Birthday</label>
        </div>

        <div class="col-auto bdinput">
          <input v-model="user.birthday" type="date" min="1900-01-01" onkeydown="return false" id="birthday" v-on:input="btnEnable">
        </div>
      </div>
    </div>
    <div class="btn-container">
      <button v-on:click="registerUser()" type="submit" class="btn btn-primary" id="reg-btn" disabled>
        Register
      </button>
    </div>

  </div>
</template>


<script lang="js">
import {axiosApi} from "../api/backend-api";
import {defineComponent} from 'vue';
import router from "@/router";


export default defineComponent({
  name: "Register",

  data: function () {
    return {
      currentDate: new Date().toJSON().slice(0, 10).toString(),
      passwordConfirm: "",
      showErrorEmail: false,
      showErrorUsername: false,
      showErrorPassword: false,
      showErrorPassConfirm: false,
      user: {
        username: "",
        password: "",
        email: "",
        firstName: "",
        lastName: "",
        gender: "",
        birthday: "",
      }
    }
  },
  mounted () {
    document.getElementById("birthday").setAttribute("max", this.currentDate)
  },
  methods: {
    btnEnable() {
      const btn = document.querySelector("#reg-btn")
      if (this.user.username.length > 3 && this.user.firstName !== "" && this.user.lastName !== "" && this.user.email !== "" && !this.showErrorEmail
      && this.user.password.length > 7 && this.user.gender !== "" && this.user.birthday !== "") {
        btn.removeAttribute("disabled")

      } else {
        btn.setAttribute("disabled", "")
      }
    },
    validateUsername() {
      this.btnEnable()
      const userInput = document.querySelector("#inputUser");
      if (this.user.username.length < 4) {
        userInput.style.borderColor = "red";
        this.showErrorUsername = true;
        return;
      }
      userInput.style.borderColor = "green";
      this.showErrorUsername = false;
    },

    validatePassword() {
      this.btnEnable()
      const passInput = document.querySelector("#inputPassword");
      if (this.user.password.length < 7) {
        passInput.style.borderColor = "red";
        this.showErrorPassword = true;
        return;
      }
      passInput.style.borderColor = "green";
      this.showErrorPassword = false;
    },

    validateEmail() {
      const emailInput = document.querySelector("#emailInput");
      if (!this.user.email.match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
        emailInput.style.borderColor = "red";
        this.showErrorEmail = true;
        this.btnEnable()
        return;
      }
      emailInput.style.borderColor = "green";
      this.showErrorEmail = false;
      this.btnEnable()
    },
    registerUser() {
      if (this.passwordConfirm === this.user.password) {
        this.showErrorPassConfirm = false;
        return axiosApi.post("authentication/register", this.user)
            .then(response => {
              if (response.status === 201) {
                alert("User registered!");
                router.push("/");
              }
            }).catch(error => {
              if (error.response.status === 400) {
                alert("User already exists!");
              }
            })
      }
      this.showErrorPassConfirm = true;
    }
  }

})


</script>


<style>
.container {
  margin-top: 100px;
}

.pass {
}

.col {
  margin: 30px 0;
}

.col-form-label {
  margin-right: 80px;
}

.col1 {
  margin-right: 140px;
}

.pass-container {
  margin: 30px 0;
}

.btn-container {
  margin: 40px 0;
}

.date-container {
  margin-top: 30px;
}

.bdinput {
  margin-left: 100px;
}

#birthday {
  border-color: lightgray;
  border-style: solid;
  border-radius: 10px;
  padding: 5px;
}
</style>

<!--"!user.username && !user.firstName && !user.lastName-->
<!--&& !user.email && !user.password && !passwordConfirm-->
<!--&& !user.gender && !user.birthday"-->