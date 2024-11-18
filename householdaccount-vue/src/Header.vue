<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'
import Vue from "vue";


const header = ref([
  {text:'収支No'},
  {text:'収支区分'},
  {text:'収支日付'},
  {text:'種別'},
  {text:'金額'},
  {text:'備考'},
  {text:'編集'},
  {text:'削除'}
])

const dataes = [
  {no:1, name:'Daiki', email:'daiki@hoge.net'},
  {no:2, name:'Naoki', email:'naoki@hoge.net'}
]

/*import Vue from "vue";
var vm = new Vue({
   el: '#app',
   data: {
       grades: [
        {no:1, name:'Daiki', email:'daiki@hoge.net'},
        {no:2, name:'Naoki', email:'naoki@hoge.net'},
        {no:3, name:'Yuki', email:'yuki@hoge.net'},
        {no:4, name:'Aoi', email:'aoi@hoge.net'},
        {no:5, name:'Hnako', email:'hanako@hoge.net'},
        {no:6, name:'Momo', email:'momo@hoge.net'},
        {no:7, name:'Taro', email:'taro@hoge.net'}, 
        {no:8, name:'Shiori', email:'siori@hoge.net'},
        {no:9, name:'Jiro', email:'jiro@hoge.net'},
        {no:10, name:'Sigeru', email:'sigeru@hoge.net'}
       ]
   }
});*/

</script>

<script lang="ts">
export default {
        name: "TableSample1.vue",
        data() {
            return {
              grades:[
              {no:'I240400001', balance:'収入', balance_date:'2024/4/20',type:'貯金', amount:200000, note:''},
              {no:'E240400001', balance:'支出', balance_date:'2024/4/20',type:'食費', amount:1000, note:'あああ'}
                ]
            }
        }
    }
</script>


<template>
    <h1>Header</h1>
</template>



<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}
</style>
