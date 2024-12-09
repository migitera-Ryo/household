<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  data() {
    return {
      date: '',
    }
  },
  methods: {
    setDate() {
      this.$emit('execute-method')
    },
  },
}
</script>

<template>
  <input type="date" v-model="date" placeholder="Type here" @blur="setDate" />
</template>

<style scoped></style>
