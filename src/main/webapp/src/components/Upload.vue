<script setup lang="ts">
import type {Ref, UnwrapRef} from 'vue'
import {ref} from 'vue'
import axios from "axios";

const myfile: Ref<UnwrapRef<any>> = ref(undefined)

function upload() {
  // TODO: move to pinia.
  const formData = new FormData()
  formData.append('myfile', myfile.value.files[0])
  axios.post('/gpx/upload', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })

  // Clear the file field.
  myfile.value.value = null;
}
</script>

<template>
  <form>
    <input type="file" ref="myfile"/>
    <input type="submit" @click.prevent="upload($event)"/>
  </form>
</template>

<style scoped>
</style>
