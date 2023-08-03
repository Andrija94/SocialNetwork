<template>
  <Navbar :username="username"></Navbar>
  <div class="posts">
    <div>
      <NewPost></NewPost>
      <div v-if="posts.length < 1">
        <p>{{ username }} does not have posts yet :(</p>
      </div>
      <div>
        <div class="container border border-primary w-25 p-5 mt-5" v-for="post in posts" :key="post.username">
          <div v-if="post.picture.length > 0" class="position-relative contain">
            <img class="img-fluid border rounded" :src="'data:image/jpg;base64,' + post.picture" id="img"
                                                      alt="Some picture">
          </div>
          <div class="m-2">{{ post.text }}</div>
          <div>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
              Comments
            </button>

            <!-- Modal -->
            <div class="modal modal-dialog-scrollable" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content position-relative">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <textarea rows="3" cols="50"></textarea>
                    <button class=" btn btn-primary" type="submit">Submit</button>
                    <div v-if="post.comments > 0">
                      <div v-for="comment in post.comments" v-bind:key="comment">
                        <div>{{comment}}</div>
                      </div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
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
              console.log(this.posts)
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