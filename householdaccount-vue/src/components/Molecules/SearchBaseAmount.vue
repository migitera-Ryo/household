<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import NunberInput from '../Atoms/NumberInput.vue'

export default {
  props: ['validatedNull'],
  data() {
    return {
      firstAmount: '',
      lastAmount: '',
      amountResult: '',
      subAmountResult: '',
      frag:Boolean,
      subFrag:Boolean
    }
  },
  methods: {
    setAmount(amountResult: any) {
      this.amountResult = amountResult
      this.amountCheckValidate()
      this.$emit('execute-method', this.firstAmount, this.lastAmount, this.amountResult, this.subAmountResult)
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
      if (firstAmount && lastAmount) {
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
    <NunberInput v-model="firstAmount" @execute-method="setAmount" :validatedNull = "validatedNull"/>
    <label>{{ '~' }}</label>
    <NunberInput v-model="lastAmount" @execute-method="setAmount" :validatedNull = "validatedNull"/>
    <p>{{ amountResult }}</p>
    <p>{{ subAmountResult }}</p>
  </p>
</template>

<style scoped></style>
