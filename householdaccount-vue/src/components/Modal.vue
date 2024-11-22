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
        incomeDate:'',
        amount:'',
        incomeType: '',
        note:''
    },
    expenditureInfo:{
        expenditureDate:'',
        amount:'',
        expenditureitemCode: '',
        expenditureitemName: '',
        note:''
    },
      selectedItem: '収入',
      isPush1:true,
      isPush2:true,
      incomeTypes: [
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資' }
      ],
      expenseItems2: [
        { value: '1', text: '食費' },
        { value: '2', text: '日用品' },
        { value: '3', text: '光熱費' },
        { value: '4', text: '住宅費' },
        { value: '5', text: '交通費' },
        { value: '6', text: '通信費' }
      ],
      expenseItems:[
        {expenditureExpenseItemCode:'',
          expenditureExpenseItemName:'',
          expenditureExpenseItemNameKana:'' 
        }
      ]
    }
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/expenseItems');
        this.expenseItems = response.data;
      } catch (error) {
        console.error("There was an error fetching the users!", error);
      }
    },

    send: function(){
			axios.post('http://localhost:8080/api/recieve', this.incomeInfo).
				then(response=>{console.log(response)})
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
      //this.income_type.text = '';
    },
    pushedBtn2() {
      this.isPush1 = true;
      this.isPush2 = false;
      //this.selectedExpenseItem = '';
    }
  },
};
</script>

<template>
    <div id="modal">
      <div id="modal-content" class="modal">
        <p id="modal-message" class="modal__message">{{ message }}</p>

        <p>
        <label>支出区分：</label>
        <input type="radio" v-model="selectedItem" v-on:click="pushedBtn1" id="button1" value="収入" name = "radio"/>
        <label for="button1">収入</label>

        <input type="radio" v-model="selectedItem" v-on:click="pushedBtn2" id="button2" value="支出" name = "radio"/>
        <label for="button2">支出</label>
        </p>
        
        <span v-if="selectedItem=='収入'">
        <label>収支日付：</label>
        <input type="date" class = "search_text" v-model="incomeInfo.incomeDate" placeholder="Type here"/>

        <p>
        <label>収入種別：</label>
        <select v-model="incomeInfo.incomeType" :disabled="false">
        <option v-for="income_type in incomeTypes" :value="income_type.value" :key="income_type.value">
          {{ income_type.text }}
        </option>
        </select>
        </p>

        <p>
        <label>支出費目：</label>
        <select v-model="expenditureInfo.expenditureitemName" :disabled="true">
        <option v-for="expense_item in expenseItems" :value="expense_item.expenditureExpenseItemName" :key="expense_item.expenditureExpenseItemCode">
          {{ expense_item.expenditureExpenseItemName }}
        </option>
        </select>
        </p>

        <label>金額：</label>
        <input type="text" class = "search_text" v-model="incomeInfo.amount" placeholder="Type here"/>
        
        <p>
          <label>備考：</label>
          <input type="text" class = "search_text" v-model="incomeInfo.note" placeholder="Type here"/>
        </p>
      </span>


      <span v-if="selectedItem=='支出'">
        <label>収支日付：</label>
        <input type="date" class = "search_text" v-model="expenditureInfo.expenditureDate" placeholder="Type here"/>

        <p>
        <label>収入種別：</label>
        <select v-model="incomeInfo.incomeType" :disabled="true">
        <option v-for="income_type in incomeTypes" :value="income_type.value" :key="income_type.value">
          {{ income_type.text }}
        </option>
        </select>
        </p>

        <p>
        <label>支出費目：</label>
        <select v-model="expenditureInfo.expenditureitemName":disabled="false">
        <option v-for="expense_item in expenseItems" :value="expense_item.expenditureExpenseItemName" :key="expense_item.expenditureExpenseItemCode">
          {{ expense_item.expenditureExpenseItemName }}
        </option>
        </select>
        </p>

        <label>金額：</label>
        <input type="text" class = "search_text" v-model="expenditureInfo.amount" placeholder="Type here"/>
        
        <p>
          <label>備考：</label>
          <input type="text" class = "search_text" v-model="expenditureInfo.note" placeholder="Type here"/>
        </p>
      </span>
        

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