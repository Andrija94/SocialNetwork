<template>
  <div class="feed position-relative">
    <div class="position-absolute line w-50">
      <p class="text-body-secondary h4">Your feed</p>
    </div>
    <div>
      <div class="container border border-primary w-25 p-5 mt-5" v-for="post in list" :key="post.username">
        <div v-if="post.picture.length > 0" class="position-relative contain"><img class="img-fluid border rounded"
                                                    :src="'data:image/jpg;base64,' + post.picture" id="img"
                                                    alt="Some picture"></div>
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
                  <div v-if="post.comments.length > 0">
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
</template>

<script lang="js">
import {defineComponent} from 'vue';
import posts from "@/components/Posts.vue";

export default defineComponent({
  name: "Feed",
  computed: {
    posts() {
      return posts
    }
  },
  props: {
    list: Array
  }


})

</script>

<style>

.line {
  left: 0;
  right: 0;
  margin: -80px auto 0;
}

::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #888;
}

::-webkit-scrollbar-thumb:hover {
  background: #555;
}

</style>