<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import Button from '../Atoms/Button.vue'

export default {
  props: ['btname1', 'btname2', 'disabledFrag'],
  data() {
    return {
      selectedRadioName: '',
    }
  },
  methods: {
    actinoMethod() {
      this.$emit('execute-actionMethod')
    },
    cancelMethod() {
      this.$emit('execute-cancelMethod')
    },
  },
}
</script>

<template>
  <p>
    <Button @click="actinoMethod" :btname="btname1" :disabled="disabledFrag" />
    <Button @click="cancelMethod" :btname="btname2" />
  </p>
</template>

<style scoped></style>
