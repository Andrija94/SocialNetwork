<template>
  <div class="feed">
    <div>
      <p>Your feed</p>
    </div>
    <div>
      <ul v-for="post in list" :key="post.username">
        <li>{{post.picture}}</li>
        <li>{{post.text}}</li>
        <li>{{post.comments}}</li>
      </ul>
    </div>
  </div>
</template>

<script lang="js">
import {defineComponent} from 'vue';
import router from "@/router";


export default defineComponent ({
  name: "Feed",
  props: {
    list: Array
  }

})

</script>