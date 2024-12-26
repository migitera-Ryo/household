<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  props: {
    radioname: String,
    radioName: String,
    setedRadioName: String,
    frag: Boolean,
  },

  data() {
    return {
      selectRadio: null,
    }
  },
  methods: {
    setRadioName() {
      this.$emit('execute-method')
    },
  },
}
</script>

<template>
  <span v-if="setedRadioName == radioname || (!setedRadioName && radioname == '収入')">
    <input type="radio" :name="radioName" @change="setRadioName" :disabled="frag" checked />
    <label for="button1">{{ radioname }}</label>
  </span>

  <span v-else>
    <input type="radio" :name="radioName" @change="setRadioName" :disabled="frag" />
    <label for="button1">{{ radioname }}</label>
  </span>
</template>

<style scoped></style>
