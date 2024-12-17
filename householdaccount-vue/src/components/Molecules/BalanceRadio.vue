<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import RadioButton from '../Atoms/RadioButton.vue'

export default {
  props: ['radioName', 'balanceType'],
  data() {
    return {
      selectedRadioName: '',
    }
  },
  methods: {
    setRadioIncome() {
      this.selectedRadioName = '収入'
      this.$emit('execute-method', this.selectedRadioName)
    },
    setRadioExpenditure() {
      this.selectedRadioName = '支出'
      this.$emit('execute-method', this.selectedRadioName)
    },
  },
}
</script>

<template>
  <label>支出区分：</label>
  <RadioButton
    v-model="selectedRadioName"
    radioname="収入"
    :radioName="radioName"
    :setedRadioName="balanceType"
    @execute-method="setRadioIncome"
  />
  <RadioButton
    v-model="selectedRadioName"
    radioname="支出"
    :radioName="radioName"
    :setedRadioName="balanceType"
    @execute-method="setRadioExpenditure"
  />
</template>

<style scoped></style>
