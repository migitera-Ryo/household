<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

interface Props {
  btname: string
}
defineProps<Props>()
</script>

<script lang="ts"></script>

<template>
  <button class="modal__btn">{{ btname }}</button>
</template>

<style scoped>
.modal__btn {
  display: inline-block;
  margin: 30px auto;
  text-decoration: none;
  width: 80px;
  height: 30px;
  text-decoration: none;
  color: #000000;
  border: solid 2px #a5272a;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #faebd7;
}

.modal__btn:hover {
  background: #a5272a;
  color: white;
  cursor: pointer;
}
</style>
