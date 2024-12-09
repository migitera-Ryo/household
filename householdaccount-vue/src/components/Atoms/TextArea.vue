<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  data() {
    return {
      note: '',
    }
  },
  methods: {
    setNote() {
      this.$emit('execute-method', this.note)
    },
  },
}
</script>

<template>
  <p>
    <label>備考：</label>
    <input type="text" v-model="note" placeholder="200文字以内で入力" @blur="setNote" />
  </p>
</template>

<style scoped></style>
