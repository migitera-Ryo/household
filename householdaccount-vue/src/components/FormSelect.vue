<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';

interface Props{
    types:{
        value: Int,
        text: String
    }
}defineProps<Props>();
</script>

<script lang="ts">

</script>

<template>
    <p>
        <label>収入種別：</label>
        <select>
        <option v-for="income_type in types" :value="income_type.value" :key="income_type.value">
          {{ income_type.text }}
        </option>
        </select>
        </p>


    <input type="date" placeholder="Type here"/>
</template>


<style scoped>

</style>