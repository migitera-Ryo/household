<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  props: ['validatedNull', 'id'],
  data() {
    return {
      date: '',
      dateResult: '',
    }
  },
  methods: {
    setDate() {
      console.log(this.date)
      this.dateCheckValidate()
      this.$emit('execute-method', this.dateResult)
    },

    dateCheckValidate() {
      const date_error_message = this.dateValidate(this.date)
      if (date_error_message === true) {
        this.dateResult = ''
      } else if (this.validatedNull == 'true') {
        this.dateResult = 'YYYY/MM/DDの形式で入力してください!!!!!!!!!'
      }
    },

    dateValidate(date: any) {
      if (this.validatedNull == 'true') {
        if (date.match(/^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/)) {
          return true
        } else {
          //dateの表示を空欄にする処理
          return false
        }
      }

      if (this.validatedNull == 'false') {
        if (date.match(/^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/)) {
          return true
        } else {
          document.getElementById(this.id)!.value = ''
          return false
        }
      }
    },
  },
}
</script>

<template>
  <input type="date" :id="id" v-model="date" placeholder="Type here" @blur="setDate" />
</template>

<style scoped></style>
