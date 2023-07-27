<template>
  <div class="home">

    <users-list></users-list>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import UsersList from "@/components/UsersList.vue";
import TestComponent from "@/components/TestCompont.vue";

export default defineComponent({
  name: 'Home',
  components: {
    UsersList
  },
});
</script>
