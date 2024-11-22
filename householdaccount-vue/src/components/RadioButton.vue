<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';

interface Props{
    radioname:string;
}defineProps<Props>();

</script>

<script lang="ts">

</script>

<template>
    <input type="radio"/>
    <label for="button1">{{radioname}}</label>
</template>


<style scoped>

</style>
