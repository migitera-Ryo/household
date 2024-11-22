<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';
</script>

<script lang="ts">
import DateInput from './components/DateInput.vue'
</script>

<template>
    <label>収支日付：</label>
    <DateInput/>
    <label>{{ ~ }}</label>
    <DateInput/>
</template>


<style scoped>

</style>