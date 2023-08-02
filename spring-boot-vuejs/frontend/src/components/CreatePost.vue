<template>
  <div class="m-3">
    <button type="button" class="btn d-grid col-2 border border-success-subtle" data-bs-toggle="modal"
            data-bs-target="#exampleModal">
      Create New Post
    </button>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">New Post</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body w-100 h-100">
          <div class="imagePreviewWrapper" style="{ 'background-image': url(previewImage)}" @click="selectImage">
            <img :src="previewImage" class="uploading-image w-100 h-100"/>
          </div>
          <div class="m-3">

            <input type="file" @change="pickFile($event)">
          </div>
          <div class="m-3 w-75">
            <label for="text">Enter post description:</label>
            <textarea v-model="dto.text" id="text" name="text" rows="3" cols="50"></textarea>
          </div>


        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">Close</button>
          <button @click="createPost" type="button" class="btn btn-outline-success">Create</button>
        </div>
      </div>
    </div>
  </div>

</template>


<script>
import {defineComponent} from "vue";
import {axiosApi} from "@/api/backend-api";
import router from "@/router";


export default defineComponent({
  name: "NewPost",

  data: function () {
    return {
      previewImage: null,
      image: null,
      dto: {
        username: router.currentRoute.value.params.username,
        text: ""
      }
    }
  },

  methods: {
    pickFile(e) {
      const image = e.target.files[0];
      this.image = image;
      const reader = new FileReader();
      reader.readAsDataURL(image);
      reader.onload = e => {
        this.previewImage = e.target.result;
        return true
      }

    },

    createPost() {
      if (this.image !== null) {
        const imageSize = this.image.size;
        const file = Math.round(imageSize / 1024)
        if (file > 1024) {
          alert("Image is to big, select image less than 1MB!")
          return
        }
      }

      const fd = new FormData()
      fd.append("file", this.image)
      const params = new URLSearchParams();
      params.append("username", this.dto.username);
      params.append("text", this.dto.text)
      console.log(this.dto)
      return axiosApi.post("/post/create-post", fd, {
        params: params,
        headers: {"Content-Type": "multipart/form-data"}
      })
          .then(response => {
            if (response.status === 400) {alert(response.data)}

      })
          .catch(error => {
            if (error.response.status === 400) {
              alert(error.response.data)
            }
          })

    },


  }
})
</script>


<style>
.imagePreviewWrapper {
  width: 250px;
  height: 250px;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: cover;
  background-position: center center;
}

</style>