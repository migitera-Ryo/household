<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  props: {
    radioname: String,
    state: String,
  },
  methods: {
    setRadioName() {
      this.$emit('execute-method')
    },
  },
}
</script>

<template>
  <span v-if="radioname == '収入'">
    <input type="radio" name="radio" @change="setRadioName" checked />
    <label for="button1">{{ radioname }}</label>
  </span>

  <span v-else>
    <input type="radio" name="radio" @change="setRadioName" />
    <label for="button1">{{ radioname }}</label>
  </span>
</template>

<style scoped></style>
