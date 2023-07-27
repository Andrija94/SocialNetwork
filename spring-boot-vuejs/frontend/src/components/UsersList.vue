<template>
  <div>
    <table class="table">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Username</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" :key="user.username">
        <th scope="row">{{user.profilePicture}}</th>
        <td>{{user.username}}</td>
        <td>{{user.firstName}}</td>
        <td>{{user.lastName}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>



<script lang="js">
import {defineComponent} from "vue";
import api from "../api/backend-api";

export default defineComponent(
    {
      name: "UserList",
      data: function () {
        return {
          users: []
        }
      },
      methods: {
        fetchUsers() {
          return api.fetchUsers()
              .then(response => {
            this.users = response.data;
          }).catch(error => console.log(error))
        }
      },
      created(){
        console.log("List of all users")
        this.fetchUsers();
      }
    }
)

</script>




<style></style>