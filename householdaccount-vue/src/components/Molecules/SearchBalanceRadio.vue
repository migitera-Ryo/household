<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import RadioButton from '../Atoms/RadioButton.vue'

export default {
  props: ['radioName'],
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
    setRadioBalance() {
      this.selectedRadioName = '指定なし'
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
    @execute-method="setRadioIncome"
  />
  <RadioButton
    v-model="selectedRadioName"
    radioname="支出"
    :radioName="radioName"
    @execute-method="setRadioExpenditure"
  />
  <RadioButton
    v-model="selectedRadioName"
    radioname="指定なし"
    :radioName="radioName"
    @execute-method="setRadioBalance"
  />
</template>

<style scoped></style>
