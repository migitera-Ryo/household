<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import DateInput from '../Atoms/DateInput.vue'

export default {
  data() {
    return {
      firstDate: '',
      lastDate: '',
    }
  },
  methods: {
    setDate() {
      this.$emit('execute-method', this.firstDate, this.lastDate)
    },
  },
}
</script>

<template>
  <p>
    <label>収支日付：</label>
    <DateInput v-model="firstDate" @execute-method="setDate" />
    <label>{{ '~' }}</label>
    <DateInput v-model="lastDate" @execute-method="setDate" />
  </p>
</template>

<style scoped></style>
