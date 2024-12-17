<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import BalanceRadio from '../Molecules/BalanceRadio.vue'
import DateInput from '../Atoms/DateInput.vue'
import SearchBaseDate from '../Molecules/SearchBaseDate.vue'
import NunberInput from '../Atoms/NumberInput.vue'
import SearchBaseAmount from '../Molecules/SearchBaseAmount.vue'
import FormSelect from '../Atoms/FormSelect.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Atoms/button.vue'
import { number } from 'yup'

export default {
  name: 'Modal',
  props: ['message'],
  data() {
    return {
      searchInfo: {
        fromDate: '',
        toDate: '',
        fromAmount: '',
        toAmount: '',
        incomeType: '',
        expenditureItemName: '',
        note: '',
      },
      searchResultBalanceInfo: [
        {
          balanceCode: '',
          balanceType: '',
          amount: '',
          balanceDate: '',
          incomeType: '',
          incomeTypeName: '',
          expenditureExpenseItemName: '',
          note: '',
        },
      ],

      searchResultBalanceInfoSample: [
        {
          balanceCode: 'I240400001',
          balanceType: '収入',
          amount: '111',
          balanceDate: '2024/11/19',
          incomeType: '1',
          incomeTypeName: '給与',
          expenditureExpenseItemName: '食費',
          note: 'あああ',
        },
      ],

      balanceType: '収入',
      amount: '111',
      balanceDate: '2024-11-19',
      incomeType: '1',
      incomeTypeName: '給与',
      expenditureExpenseItemName: '食費',
      note: 'あああ',

      selectedRadio: '収入',
      NullFrag: true,
      NotNullFrag: false,
      SystemErrorMessage: '',
      validationFrag: true,

      incomeTypes: [
        { value: '0', text: '' },
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資' },
      ],
      expenseItems: [
        {
          expenditureExpenseItemCode: '',
          expenditureExpenseItemName: '',
          expenditureExpenseItemNameKana: '',
        },
      ],

      validation: {
        dateResult: '',
        subDateResult: '',
        incomeTypeResult: '',
        expenditureTypeResult: '',
        amountResult: '',
        subAmountResult: '',
        noteResult: '',
      },
    }
  },
  mounted() {
    this.fetchUsers()
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('http://localhost:8080/api/expenseItems')
        this.expenseItems = response.data
      } catch (error) {
        console.error('There was an error fetching the users!', error)
      }
    },

    detailSearchIncome: function () {
      try {
        axios
          .get('http://localhost:8080/api/detailSearchIncome', {
            params: {
              fromDate: this.searchInfo.fromDate,
              toDate: this.searchInfo.toDate,
              fromAmount: this.searchInfo.fromAmount,
              toAmount: this.searchInfo.toAmount,
              incomeType: this.searchInfo.incomeType,
              expenditureItemName: this.searchInfo.expenditureItemName,
              note: this.searchInfo.note,
            },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultBalanceInfo = response.data),
              this.$emit('execute-method2', this.searchResultBalanceInfo)
          })
      } catch (error) {
        alert('検索できません')
      }
    },

    detailSearchExpenditure: function () {
      try {
        axios
          .get('http://localhost:8080/api/detailSearchExpenditure', {
            params: {
              fromDate: this.searchInfo.fromDate,
              toDate: this.searchInfo.toDate,
              fromAmount: this.searchInfo.fromAmount,
              toAmount: this.searchInfo.toAmount,
              incomeType: this.searchInfo.incomeType,
              expenditureItemName: this.searchInfo.expenditureItemName,
              note: this.searchInfo.note,
            },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultBalanceInfo = response.data),
              this.$emit('execute-method2', this.searchResultBalanceInfo)
          })
      } catch (error) {
        alert('検索できません')
      }
    },

    detailSearchBalance: function () {
      try {
        axios
          .get('http://localhost:8080/api/detailSearchBalance', {
            params: {
              fromDate: this.searchInfo.fromDate,
              toDate: this.searchInfo.toDate,
              fromAmount: this.searchInfo.fromAmount,
              toAmount: this.searchInfo.toAmount,
              incomeType: this.searchInfo.incomeType,
              expenditureItemName: this.searchInfo.expenditureItemName,
              note: this.searchInfo.note,
            },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultBalanceInfo = response.data),
              this.$emit('execute-method2', this.searchResultBalanceInfo)
          })
      } catch (error) {
        alert('検索できません')
      }
    },

    returnFalse() {
      this.$emit('execute-method1', false)
    },

    finalSetDate(dateResult: any) {
      this.validation.dateResult = dateResult
      this.validationCheck()
    },

    finalSetNote(finalNote: any, noteResult: any) {
      this.searchInfo.note = finalNote
      this.validation.noteResult = noteResult
      this.validationCheck()
    },

    finalSetAmount(amountResult: any) {
      this.validation.amountResult = amountResult
      this.validationCheck()
    },

    finalSetIncomeType(selectType: any, incomeType: any) {
      this.searchInfo.incomeType = selectType
      this.validation.incomeTypeResult = incomeType

      this.validationCheck()
    },

    finalSetExpenditureType(selectType: any, expenditureTypeResult: any) {
      this.searchInfo.expenditureItemName = selectType
      this.validation.expenditureTypeResult = expenditureTypeResult

      this.validationCheck()
    },

    finalSetBalanceType(
      selectType: any,
      selectSubType: any,
      incomeType: any,
      expenditureTypeResult: any,
    ) {
      this.searchInfo.incomeType = selectType
      this.searchInfo.expenditureItemName = selectSubType
      this.validation.incomeTypeResult = incomeType
      this.validation.expenditureTypeResult = expenditureTypeResult

      this.validationCheck()
    },

    finalSetRadioName(finalRadioName: any) {
      this.selectedRadio = finalRadioName
    },

    validationCheck() {
      if (this.selectedRadio == '収入') {
        if (
          this.validation.dateResult ||
          this.validation.subDateResult ||
          this.validation.amountResult ||
          this.validation.subAmountResult ||
          this.validation.incomeTypeResult ||
          this.validation.noteResult
        ) {
          this.validationFrag = true
        } else {
          this.validationFrag = false
        }
      } else if (this.selectedRadio == '支出') {
        if (
          this.validation.dateResult ||
          this.validation.subDateResult ||
          this.validation.amountResult ||
          this.validation.subAmountResult ||
          this.validation.expenditureTypeResult ||
          this.validation.noteResult
        ) {
          this.validationFrag = true
        } else {
          this.validationFrag = false
        }
      } else if (this.selectedRadio == '指定なし') {
        if (
          this.validation.dateResult ||
          this.validation.subDateResult ||
          this.validation.amountResult ||
          this.validation.subAmountResult ||
          this.validation.incomeTypeResult ||
          this.validation.expenditureTypeResult ||
          this.validation.noteResult
        ) {
          this.validationFrag = true
        } else {
          this.validationFrag = false
        }
      }

      if (
        !this.searchInfo.fromAmount &&
        !this.searchInfo.toAmount &&
        !this.searchInfo.fromDate &&
        !this.searchInfo.toDate &&
        !this.searchInfo.incomeType &&
        !this.searchInfo.expenditureItemName &&
        !this.searchInfo.note
      ) {
        this.validationFrag = true
      }
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <BalanceRadio
        @execute-method="finalSetRadioName"
        radioName="editRadio"
        :balanceType="balanceType"
      />
      <p>{{ selectedRadio }}</p>

      <p>
        {{ '収支日付：' }}
        <DateInput
          @execute-method="finalSetDate"
          v-model="searchInfo.toDate"
          validatedNull="true"
          id="dateid"
          :balanceDate="balanceDate"
        />
      </p>

      <p>{{ searchInfo.toDate }}</p>
      <p>{{ validation.dateResult }}</p>

      <p>
        {{ '金額：' }}
        <NunberInput
          @execute-method="finalSetAmount"
          v-model="searchInfo.toAmount"
          validatedNull="true"
          :balanceAmount="amount"
        />
      </p>

      <p>{{ searchInfo.toAmount }}</p>
      <p>{{ validation.amountResult }}</p>

      <FormSelect
        :selectedRadioName="selectedRadio"
        :expenseItems="expenseItems"
        :types="incomeTypes"
        :incomeType="incomeType"
        @executeIncome-method="finalSetIncomeType"
        @executeExpenditure-method="finalSetExpenditureType"
        @executeBalance-method="finalSetBalanceType"
        validatedNull="true"
      />

      <p>{{ searchInfo.incomeType }}</p>
      <p>{{ validation.incomeTypeResult }}</p>

      <TextArea @execute-method="finalSetNote" :balanceNote="note" validatedNull="false" />
      <p>{{ searchInfo.note }}</p>
      <p>{{ validation.noteResult }}</p>

      <span v-if="selectedRadio == '収入'">
        <Button btname="検索" @click="detailSearchIncome" :disabled="validationFrag" />
      </span>

      <span v-if="selectedRadio == '支出'">
        <Button btname="検索" @click="detailSearchExpenditure" :disabled="validationFrag" />
      </span>

      <span v-if="selectedRadio == '指定なし'">
        <Button btname="検索" @click="detailSearchBalance" :disabled="validationFrag" />
      </span>

      <Button @click="returnFalse()" btname="キャンセル" />
    </div>
    <div id="modal-overlay" @click.self="returnFalse()"></div>
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
