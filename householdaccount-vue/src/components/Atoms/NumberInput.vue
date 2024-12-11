<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  data() {
    return {
      amount: '',
      amountResult: '',
    }
  },
  methods: {
    setAmount() {
      this.amountCheckValidate()
      this.$emit('execute-method', this.amountResult)
    },

    amountCheckValidate() {
      const amount_error_message = this.amonutValidate(this.amount)
      if (amount_error_message === true) {
        this.amountResult = ''
      } else {
        this.amountResult = amount_error_message
      }
    },

    amonutValidate(amount: any) {
      if (!amount) {
        return '金額を入力してください'
      }
      const regex = /^[0-9]+(\.[0-9]+)?$/
      if (!regex.test(amount)) {
        return '半角の整数で入力してください'
      }
      if (amount.length > 8) {
        return '8桁以内で入力してください'
      }
      return true
    },
  },
}
</script>

<template>
  <input type="text" v-model="amount" placeholder="Type here" @blur="setAmount" />
  <!-- <p>{{ amountResult }}</p> -->
</template>

<style scoped></style>
