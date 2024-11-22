<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';
</script>

<script lang="ts">
import NunberInput from './components/NumberInput.vue'
</script>

<template>
    <label>金額：</label>
    <NunberInput/>
    <label>{{ ~ }}</label>
    <NunberInput/>
</template>


<style scoped>

</style>