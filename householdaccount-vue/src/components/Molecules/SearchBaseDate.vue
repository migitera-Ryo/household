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
      dateResult: '',
      subDateResult: '',
    }
  },
  methods: {
    setDate(dateResult:any) {
      this.dateResult = dateResult
      this.dateCheckValidate()
      this.$emit('execute-method', this.firstDate, this.lastDate)
    },

    dateCheckValidate() {
      const date_error_message = this.dateCompareValidate(this.firstDate, this.lastDate)
      if (date_error_message === true) {
        this.subDateResult = ''
      } else {
        this.subDateResult = date_error_message
      }
    },

    dateCompareValidate(firstDate: any, lastDate:any) {
      if (firstDate != '' && lastDate != '') {
        if(firstDate > lastDate){
          return 'from日付 < to日付に修正してください'
        }
      }
      return true
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
    <p>{{ dateResult }}</p>
    <p>{{ subDateResult }}</p>
  </p>
</template>

<style scoped></style>
