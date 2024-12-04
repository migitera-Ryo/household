<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';

interface Props{
    btname:string;
}defineProps<Props>();

</script>

<script lang="ts">

</script>

<template>
    <button>{{btname}}</button>
</template>


<style scoped>

</style>