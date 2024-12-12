<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  props: ['validatedNum'],
  data() {
    return {
      date: '',
      dateResult: '',
    }
  },
  methods: {
    setDate() {
      this.dateCheckValidate()
      this.$emit('execute-method', this.dateResult)
    },

    dateCheckValidate() {
      const date_error_message = this.dateValidate(this.date)
      if (date_error_message === true) {
        this.dateResult = ''
      } else {
        this.dateResult = date_error_message
      }
    },

    dateValidate(date: any) {
      if (date.match(/^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/)) {
        return true
      }
      return 'YYYY/MM/DDの形式で入力してください'
    },
  },
}
</script>

<template>
  <input type="date" id="date" v-model="date" placeholder="Type here" @blur="setDate" />
</template>

<style scoped></style>
