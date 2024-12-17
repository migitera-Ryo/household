<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">

export default {
  props: {
    validatedNull:String,
    selectedRadioName: String,
    incomeType:String,
    expenseItems: [
      {
        expenditureExpenseItemCode: String,
        expenditureExpenseItemName: String,
        expenditureExpenseItemNameKana: String,
      },
    ],
  },
  data() {
    return {
      incomeType: this.incomeType,
      expenditureType: '',
      incomeTypeResult: '',
      expenditureTypeResult: '',
      types: [
        // { value: '0', text: '' },
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資' },
      ],
    }
  },
  methods: {
    setIncomeType() {
      this.incomeTypeCheckValidate()
      this.$emit('executeIncome-method', this.incomeType, this.incomeTypeResult)
    },
    setExpenditureType() {
      this.expenditureItemCheckValidate()
      this.$emit('executeExpenditure-method', this.expenditureType, this.expenditureTypeResult)
    },
    setBalanceType() {
      this.incomeTypeCheckValidate()
      this.expenditureItemCheckValidate()
      this.$emit('executeBalance-method', this.incomeType, this.expenditureType, this.incomeTypeResult, this.expenditureTypeResult)
    },

    incomeTypeValidate(incometype: any) {
      if (this.validatedNull == 'true') {
        if (!incometype) {
        return '選択してください'
      }
        return true;
      }else{
        return true
      }
        
    },

    incomeTypeCheckValidate() {
      const incomeType_error_message = this.incomeTypeValidate(this.incomeType)
      if (incomeType_error_message === true) {
        this.incomeTypeResult = ''
      } else {
        this.incomeTypeResult = incomeType_error_message
      }
    },

    expenditureItemValidate(expenditureType:any) {
      if (this.validatedNull == 'true') {
        if(!expenditureType) {
        return '選択してください';
      }
        return true;
      }else{
        return true;
      }
    },

    expenditureItemCheckValidate() {
      const expenditureItem_error_message = this.expenditureItemValidate(this.expenditureType)
      if(expenditureItem_error_message === true) {
        this.expenditureTypeResult = '';
      } else {
        this.expenditureTypeResult = expenditureItem_error_message;
      }
    },
  },
}
</script>

<template>
  <span v-if="selectedRadioName == '収入'">
    <p>
      <label>{{ '収入種別' }}</label>
      <select v-model="incomeType" @blur="setIncomeType()">
        <option v-for="income_type in types" :value="income_type.value" :key="income_type.text">
          {{ income_type.text }}
        </option>
      </select>
      <!-- <p>{{ incomeType }}</p> -->
      <p>{{ incomeTypeResult }}</p>
    </p>

    <p>{{incomeType}}</p>

    <p>
      <label>{{ '支出費目' }}</label>
      <select v-model="expenditureType" @blur="setExpenditureType" :disabled="true">
        <option
          v-for="expenditure_type in expenseItems"
          :value="expenditure_type.expenditureExpenseItemName"
          :key="expenditure_type.expenditureExpenseItemCode"
        >
          {{}}
        </option>
      </select>
    </p>
  </span>

  <span v-if="selectedRadioName == '支出'">
    <p>
      <label>{{ '収入種別' }}</label>
      <select v-model="incomeType" @blur="setIncomeType" :disabled="true">
        <option v-for="income_type in types" :value="income_type.text" :key="income_type.vaule">
          {{}}
        </option>
      </select>
    </p>

    <p>
      <label>{{ '支出費目' }}</label>
      <select v-model="expenditureType" @blur="setExpenditureType">
        <option
          v-for="expenditure_type in expenseItems"
          :value="expenditure_type.expenditureExpenseItemName"
          :key="expenditure_type.expenditureExpenseItemCode"
        >
          {{ expenditure_type.expenditureExpenseItemName }}
        </option>
      </select>
      <p>{{ expenditureTypeResult }}</p>
    </p>
  </span>

  <span v-if="selectedRadioName == '指定なし'">
    <p>
      <label>{{ '収入種別' }}</label>
      <select v-model="incomeType" @blur="setBalanceType">
        <option v-for="income_type in types" :value="income_type.value" :key="income_type.text">
          {{ income_type.text }}
        </option>
      </select>
      <p>{{ incomeTypeResult }}</p>
    </p>

    <p>
      <label>{{ '支出費目' }}</label>
      <select v-model="expenditureType" @blur="setBalanceType">
        <option
          v-for="expenditure_type in expenseItems"
          :value="expenditure_type.expenditureExpenseItemName"
          :key="expenditure_type.expenditureExpenseItemCode"
        >
        {{ expenditure_type.expenditureExpenseItemName }}
        </option>
      </select>
      <p>{{ expenditureTypeResult }}</p>
    </p>
  </span>
</template>

<style scoped></style>
