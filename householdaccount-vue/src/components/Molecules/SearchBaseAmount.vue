<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import NunberInput from '../Atoms/NumberInput.vue'

export default {
  data() {
    return {
      firstAmount: '',
      lastAmount: '',
      amountResult: '',
      subAmountResult: '',
    }
  },
  methods: {
    setAmount(amountResult: any) {
      this.amountResult = amountResult
      this.amountCheckValidate()
      this.$emit('execute-method', this.firstAmount, this.lastAmount)
    },

    amountCheckValidate() {
      const amount_error_message = this.amonutCompareValidate(this.firstAmount,this.lastAmount)
      if (amount_error_message === true) {
        this.subAmountResult = ''
      } else {
        this.subAmountResult = amount_error_message
      }
    },

    amonutCompareValidate(firstAmount: any,lastAmount:any) {
      if (firstAmount > 0 && lastAmount > 0) {
        if(firstAmount > lastAmount)
        return 'from金額 < to金額に修正してください'
      }
      return true
    },
  },
}
</script>

<template>
  <p>
    <label>金額：</label>
    <NunberInput v-model="firstAmount" @execute-method="setAmount" />
    <label>{{ '~' }}</label>
    <NunberInput v-model="lastAmount" @execute-method="setAmount" />
    <p>{{ amountResult }}</p>
    <p>{{ subAmountResult }}</p>
  </p>
</template>

<style scoped></style>
