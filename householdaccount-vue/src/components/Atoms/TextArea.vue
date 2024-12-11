<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  data() {
    return {
      note: '',
      noteResult: '',
    }
  },
  methods: {
    setNote() {
      this.$emit('execute-method', this.note)
    },

    noteCheckValidate() {
      const note_error_message = this.noteValidate(this.note)
      if (note_error_message === true) {
        this.noteResult = ''
      } else {
        this.noteResult = note_error_message
      }
    },

    noteValidate(note: any) {
      if (note.length > 200) {
        return '200文字以内で入力してください'
      }
      return true
    },
  },
}
</script>

<template>
  <p>
    <label>備考：</label>
    <input type="text" v-model="note" placeholder="200文字以内で入力" @blur="setNote" />
    <p>{{ noteResult }}</p>
  </p>
</template>

<style scoped></style>
