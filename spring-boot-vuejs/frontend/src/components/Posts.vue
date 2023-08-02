<template>
  <Navbar :username="username"></Navbar>
  <div class="posts">
    <div>
      <NewPost></NewPost>
      <div v-if="posts.length < 1">
        <p>{{ username }} does not have posts yet :(</p>
      </div>
      <ul v-for="post in posts" :key="post.username">
        <li>{{ post.username }}</li>
        <li>{{ post.picture }}</li>
        <li>{{ post.text }}</li>
        <li>{{ post.comments }}</li>
      </ul>
    </div>
  </div>
</template>


<script lang="js">
import {axiosApi} from "../api/backend-api";
import {defineComponent} from 'vue';
import router from "@/router";
import Navbar from "@/components/Navbar.vue";
import NewPost from "@/components/CreatePost.vue";

export default defineComponent({
  name: "Posts",
  components: {
    NewPost,
    Navbar,
  },

  data: function () {
    return {
      username: router.currentRoute.value.params.username,
      posts: []
    }
  },

  methods: {
    getUserPosts() {
      return axiosApi.get("post/get-user-posts/" + this.username)
          .then(response => {
            if (response.status === 200) {
              this.posts = response.data;
            }
          })
          .catch(error => {
            console.log(error)
          })
    }
  },

  created() {
    this.getUserPosts()
  }


})
</script>


<style></style>