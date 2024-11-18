<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { createApp, ref } from 'vue'

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
</script>

<script lang="ts">
import Modal from './components/Modal.vue'

export default {
        name: "HollowWorld",
        components: {
          Modal,
        },
        data() {
            return {
              grades:[
              {no:'I240400001', balance:'収入', balance_date:'2024/4/20',type:'貯金', amount:200000, note:''},
              {no:'E240400001', balance:'支出', balance_date:'2024/4/20',type:'食費', amount:1000, note:'あああ'}
              ],
              message: "登録画面",
              modal: false,
            };
        },
        props: {
          msg: String,
        },
        methods: {
          showModal() {
            // モーダル表示する際の処理が必要ならここに書く
            this.modal = true;
          },
          executeMethod(yes: any) {
            // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
            this.modal = false;
            if (yes) {
               alert("はい が押されました。");
            } else {
              alert("いいえ が押されました。");
            }
          },
        },
    }
</script>


<template>
    <header>
    <h1 style="color:#ffffff">家計簿システム</h1>
  </header>

  <input type="text" class = "search_text" v-model="textInput" placeholder="Type here"/>
  <div align="right" class="bt_create">
    <h1>{{ msg }}</h1>

    <button align="right" class="bt_create" @click="showModal()">収支登録</button>
    <modal
      :message="message"
      v-show="modal"
      @execute-method="executeMethod"
    ></modal>
  </div>



  <div id="app">
  <table border="1">
  <thead>
    <tr>
     <th v-for="(label,key) in header" :key="key">{{label.text}}</th>
   </tr>
  </thead>
  <tbody>
  <tr v-for="grade in grades" :key="grade.no">
      <td>{{grade.no}}</td>
      <td>{{grade.balance}}</td>
      <td>{{grade.balance_date}}</td>
      <td>{{grade.type}}</td>
      <td>{{grade.amount}}</td>
      <td>{{grade.note}}</td>
      <td><button>編集</button></td>
      <td><button>削除</button></td>
   </tr>
   </tbody>
</table>
</div>

  
</template>



<style scoped>
header {
  line-height: 1.5;
  max-height: 100ch;
  background:#000000;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

.bt_create{
  width: 100px;
  height: 30px;
  position: relative; /* 今の位置を基準 */
  top: 1px;
  left: 150px; /* 左から20px */

}
.search_text{
  width: 300px;
  height: 30px;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
