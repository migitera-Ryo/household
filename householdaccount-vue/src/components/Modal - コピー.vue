<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios';
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
        expenditureItemCode: '',
        expenditureItemName: '',
        note:''
    },

      selectedRadio: '収入',
      isPush1:true,
      isPush2:true,
      message:null,

      incomeTypes: [
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資' }
      ],
      expenseItems:[
        {expenditureExpenseItemCode:'',
          expenditureExpenseItemName:'',
          expenditureExpenseItemNameKana:'' 
        }
      ],

      validation: {
        incomeDateResult: '',
        expenditureDateResult: '',
        incomeTypeResult: '',
        expenditureItemResult: '',
        incomeAmountResult: '',
        expenditureAmountResult: '',
        incomeNoteResult: '',
        expenditureNoteResult: '',
      },
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
				then(response=>{console.log(response),this.message = response.data})
		},

    sendExpenditre: function(){
			axios.post('http://localhost:8080/api/expenditure', this.expenditureInfo).
				then(response=>{console.log(response),this.$emit("execute-method", true)})
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
    },
    


    amountCheckValidate() {
      if(this.selectedRadio=='収入'){
        const amount_error_message = this.amonutValidate(this.incomeInfo.amount)
        if(amount_error_message === true) {
          this.validation.incomeAmountResult = '';
         } else {
          this.validation.incomeAmountResult = amount_error_message;
        } 
      }else if(this.selectedRadio=='支出'){
        const amount_error_message = this.amonutValidate(this.expenditureInfo.amount)
        if(amount_error_message === true) {
          this.validation.expenditureAmountResult = '';
         } else {
          this.validation.expenditureAmountResult = amount_error_message;
        } 
      }
    },

    dateCheckValidate() {
      if(this.selectedRadio=='収入'){
        const date_error_message = this.dateValidate(this.incomeInfo.incomeDate)
        if(date_error_message === true) {
          this.validation.incomeDateResult = '';
        } else {
          this.validation.incomeDateResult = date_error_message;
        }
      }else if(this.selectedRadio=='支出'){
        const date_error_message = this.dateValidate(this.expenditureInfo.expenditureDate)
        if(date_error_message === true) {
          this.validation.expenditureDateResult = '';
        } else {
          this.validation.expenditureDateResult = date_error_message;
        }
      }
      
    },

    expenditureItemCheckValidate() {
      const expenditureItem_error_message = this.expenditureItemValidate(this.expenditureInfo.expenditureItemCode)
      if(expenditureItem_error_message === true) {
        this.validation.expenditureItemResult = '';
      } else {
        this.validation.expenditureItemResult = expenditureItem_error_message;
      }
    },

    incomeTypeCheckValidate() {
      const incomeType_error_message = this.incomeTypeValidate(this.incomeInfo.incomeType)
      if(incomeType_error_message === true) {
        this.validation.incomeTypeResult = '';
      } else {
        this.validation.incomeTypeResult = incomeType_error_message;
      }
    },

    noteCheckValidate() {
      if(this.selectedRadio=='収入'){
        const note_error_message = this.noteValidate(this.incomeInfo.note)
        if(note_error_message === true) {
          this.validation.incomeNoteResult = '';
        } else {
          this.validation.incomeNoteResult = note_error_message;
        }
      }else if(this.selectedRadio=='支出'){
        const note_error_message = this.noteValidate(this.expenditureInfo.note)
        if(note_error_message === true) {
          this.validation.expenditureNoteResult = '';
        } else {
          this.validation.expenditureNoteResult = note_error_message;
        }
      }
    },

    dateValidate(date:any) {
      if(!date.match(/^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/)){
        return 'YYYY/MM/DDの形式で入力してください';
      }
      return true;
    },

    amonutValidate(amount:any) {
      if(!amount) {
        return '金額を入力してください';
      }
      const regex = /^[0-9]+(\.[0-9]+)?$/;
      if(!regex.test(amount)) {
        return '整数で入力してください';
      }
      if(amount.length > 8) {
        return '8桁以内で入力してください';
      }
      return true;
    },

    incomeTypeValidate(incometype:any) {
      if(!incometype) {
        return '選択してください';
      }
      return true;
    },

    expenditureItemValidate(expenditureItem:any) {
      if(!expenditureItem) {
        return '選択してください';
      }
      return true;
    },

    noteValidate(note:any) {
      if(note.length > 200) {
        return '200文字以内で入力してください';
      }
      return true;
    }
  },
};
</script>

<template>
    <div id="modal">
      <div id="modal-content" class="modal">
        <p>
        <label>支出区分：</label>
        <input type="radio" v-model="selectedRadio" v-on:click="pushedBtn1" id="button1" value="収入" name = "radio"/>
        <label for="button1">収入</label>

        <input type="radio" v-model="selectedRadio" v-on:click="pushedBtn2" id="button2" value="支出" name = "radio"/>
        <label for="button2">支出</label>
        </p>
        
        <span v-if="selectedRadio=='収入'">
        <label>収支日付：</label>
        <input name = "date" type="date" class = "search_text" v-model="incomeInfo.incomeDate" placeholder="Type here" @blur="dateCheckValidate" required/>
        <p>{{ validation.incomeDateResult }}</p>

        <p>
        <label>収入種別：</label>
        <select v-model="incomeInfo.incomeType" :disabled="false" @blur="incomeTypeCheckValidate">
        <option v-for="income_type in incomeTypes" :value="income_type.value" :key="income_type.value">
          {{ income_type.text }}
        </option>
        </select>
        <p>{{ validation.incomeTypeResult }}</p>
        </p>

        <p>
        <label>支出費目：</label>
        <select v-model="expenditureInfo.expenditureItemCode" :disabled="true">
        <option v-for="expense_item in expenseItems" :value="expense_item.expenditureExpenseItemCode" :key="expense_item.expenditureExpenseItemCode">
          {{ expense_item.expenditureExpenseItemName }}
        </option>
        </select>
        </p>

        <label>金額：</label>
        <input name = "amount" type="text" class = "search_text" v-model="incomeInfo.amount" placeholder="Type here" @blur="amountCheckValidate"/>
        <p>{{ validation.incomeAmountResult }}</p>

        <p>
          <label>備考：</label>
          <input type="text" class = "search_text" v-model="incomeInfo.note" placeholder="Type here" @blur="noteCheckValidate"/>
          <p>{{ validation.incomeNoteResult }}</p>
        </p>

        <p>{{message}}</p>

        <button class="modal__btn" @click="send">
          保存
        </button>
      </span>


      <span v-if="selectedRadio=='支出'">
        <label>収支日付：</label>
        <input type="date" class = "search_text" v-model="expenditureInfo.expenditureDate" placeholder="Type here" @blur="dateCheckValidate"/>
        <p>{{ validation.expenditureDateResult }}</p>

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
        <select v-model="expenditureInfo.expenditureItemCode":disabled="false" @blur="expenditureItemCheckValidate">
        <option v-for="expense_item in expenseItems" :value="expense_item.expenditureExpenseItemCode" :key="expense_item.expenditureExpenseItemCode">
          {{ expense_item.expenditureExpenseItemName }}
        </option>
        </select>
        <p>{{ validation.expenditureItemResult }}</p>
        </p>

        <label>金額：</label>
        <input type="text" class = "search_text" v-model="expenditureInfo.amount" placeholder="Type here" @blur="amountCheckValidate"/>
        <p>{{ validation.expenditureAmountResult }}</p>
        
        <p>
          <label>備考：</label>
          <input type="text" class = "search_text" v-model="expenditureInfo.note" placeholder="Type here" @blur="noteCheckValidate"/>
          <p>{{ validation.expenditureNoteResult }}</p>
        </p>

        <button class="modal__btn" @click="sendExpenditre">
          保存
        </button>
      </span>
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