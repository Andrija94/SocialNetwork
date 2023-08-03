<template>

  <div class="home">
    <div>
      <Navbar :username="username"></Navbar>
    </div>
    <div>
      <NewPost></NewPost>
      <Feed :list="posts"></Feed>
    </div>

    <!--    <users-list></users-list>-->
  </div>
</template>

<script lang="js">
import api, {axiosApi} from "../api/backend-api";
import {defineComponent} from 'vue';
import router from "@/router";
import Navbar from "@/components/Navbar.vue";
import Feed from "@/components/Feed.vue";
import NewPost from "@/components/CreatePost.vue";

export default defineComponent({
  name: 'Home',
  components: {
    NewPost,
    Navbar, Feed
  },
  data: function () {
    return {
      username: router.currentRoute.value.params.username,
      user: null,
      posts: []
    }
  },

  methods: {
    fetchUser() {
      return api.fetchUser(this.username)
          .then(response => {
            if (response.status === 200) {
              this.user = response.data;
            }
          })
          .catch(error => {
            if (error.response.status === 404) {
              alert("User not found!")
            }
          })
    },

    getFeed () {
      return axiosApi.get("post/get-all-posts")
          .then(response => {
            this.posts = response.data;
          })
          .catch(error => {
            console.log(error)
          })
    }
  },

  created() {
    this.fetchUser()
    this.getFeed()
  }
});
</script>

<style>

</style>
