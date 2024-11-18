<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';

const text = ref()

</script>

<script lang="ts">


export default {
  name: "Modal",
  props: ["message"],
  data(){
    return{
      incomeInfo:{
        selectedButtonNumber: '',
        balanceDate:'',
        amount:'',
        selectedIncomeType: '',
        note:''
    },
    revGoods:{
        selectedButtonNumber: '',
        balanceDate:'',
        amount:'',
        selectedIncomeType: '',
        note:''
    },
      selectedExpenseItem: '',
      isPush1:true,
      isPush2:true,
      incomeTypes: [
        { value: 'value1', text: '給与' },
        { value: 'value2', text: '賞与' },
        { value: 'value2', text: '副業' },
        { value: 'value2', text: 'お小遣い' },
        { value: 'value3', text: '臨時収入' },
        { value: 'value3', text: '投資' }
      ],
      expenseItems: [
        { value: 'value1', text: '食費' },
        { value: 'value2', text: '日用品' },
        { value: 'value2', text: '光熱費' },
        { value: 'value2', text: '住宅費' },
        { value: 'value3', text: '交通費' },
        { value: 'value3', text: '通信費' }
      ],
      users:[]
    }
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/users');
        this.users = response.data;
      } catch (error) {
        console.error("There was an error fetching the users!", error);
      }
    },

    send: function(){
			axios.post('http://localhost:8080/api/recieve', this.incomeInfo).
				then(response=>{console.log(response.data),this.revGoods = response.data})
		},

    returnFalse() {
      this.$emit("execute-method", false);
    },
    returnTrue() {
      this.$emit("execute-method", true);
    },
    pushedBtn1(){
      this.isPush1 = false;
      this.isPush2 = true;
      this.incomeInfo.selectedIncomeType = '';
    },
    pushedBtn2() {
      this.isPush1 = true;
      this.isPush2 = false;
      this.selectedExpenseItem = '';
    }
  },
};
</script>

<template>
    <div id="modal">
      <div id="modal-content" class="modal">
        <p id="modal-message" class="modal__message">{{ message }}</p>

        <ul>
        <li v-for="user in users" :key="user">{{user}}</li>
        </ul>

        <p>{{revGoods.selectedButtonNumber}}</p>
        <p>{{revGoods.balanceDate}}</p>
        <p>{{revGoods.selectedIncomeType}}</p>
        <p>{{revGoods.amount}}</p>
        <p>{{revGoods.note}}</p>

        <p>
        <label>支出区分：</label>
        <input type="radio" v-on:click="pushedBtn1" id="button1" value="収入" v-model="incomeInfo.selectedButtonNumber" />
        <label for="button1">収入</label>

        <input type="radio" v-on:click="pushedBtn2" id="button2" value="支出" v-model="incomeInfo.selectedButtonNumber" />
        <label for="button1">支出</label>
        </p>

        <label>収支日付：</label>
        <input type="text" class = "search_text" v-model="incomeInfo.balanceDate" placeholder="Type here"/>

        <p>
        <label>収入種別：</label>
        <select v-model="incomeInfo.selectedIncomeType" :disabled="isPush1">
        <option v-for="income_type in incomeTypes" :value="income_type.text" :key="income_type.value">
          {{ income_type.text }}
        </option>
        </select>
        </p>

        <p>
        <label>支出費目：</label>
        <select v-model="selectedExpenseItem" :disabled="isPush2">
        <option v-for="expense_item in expenseItems" :value="expense_item.value" :key="expense_item.value">
          {{ expense_item.text }}
        </option>
        </select>
        </p>

        <label>金額：</label>
        <input type="text" class = "search_text" v-model="incomeInfo.amount" placeholder="Type here"/>
        
        <p>
          <label>備考：</label>
          <input type="text" class = "search_text" v-model="incomeInfo.note" placeholder="Type here"/>
        </p>
        

        <button class="modal__btn" @click="send">
          保存
        </button>
        <button class="modal__btn" @click="returnFalse()">キャンセル</button>
      </div>
      <div id="modal-overlay"></div>
    </div>
  </template>


<style scoped>
.modal {
  padding: 10px 20px;
  border: 2px solid #a5272a;
  background: #faebd7;
  z-index: 2;
  display: block;
  text-align: center;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
}

.modal__message {
  margin-top: 10px;
}

.modal__cancel {
  margin-right: 30px;
  font-size: 15px;
}

.modal__cancel:hover {
  cursor: pointer;
  color: rgb(14, 48, 240);
  font-weight: bold;
}

.modal__btn {
  display: inline-block;
  margin: 30px auto;
  text-decoration: none;
  width: 80px;
  height: 30px;
  text-decoration: none;
  color: #000000;
  border: solid 2px #a5272a;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #faebd7;
}

.modal__btn:hover {
  background: #a5272a;
  color: white;
  cursor: pointer;
}

#modal-overlay {
  z-index: 1;
  display: block;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 120%;
  background-color: rgba(0, 0, 0, 0.75);
}
</style>