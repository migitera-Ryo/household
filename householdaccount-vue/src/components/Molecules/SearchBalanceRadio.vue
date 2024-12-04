<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import RadioButton from '../Atoms/RadioButton.vue'
</script>

<template>
  <label>支出区分：</label>
  <RadioButton radioname="収入" />
  <RadioButton radioname="支出" />
  <RadioButton radioname="指定なし" />
</template>

<style scoped></style>
