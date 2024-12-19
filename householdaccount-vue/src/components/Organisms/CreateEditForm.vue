<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import BalanceRadio from '../Molecules/BalanceRadio.vue'
import DateInput from '../Atoms/DateInput.vue'
import SearchBaseDate from '../Molecules/SearchBaseDate.vue'
import NunberInput from '../Atoms/NumberInput.vue'
import ButtonGroup from '../Molecules/ButtonGroup.vue'
import FormSelect from '../Atoms/FormSelect.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Atoms/Button.vue'
import { number } from 'yup'

export default {
  name: 'Modal',
  props: ['editInfor'],
  data() {
    return {
      searchInfo: {
        balanceType: '',
        balanceDate: '',
        amount: '',
        incomeType: '',
        expenditureItemName: '',
        note: '',
      },
      editToSearchInfo: {
        balanceCode: '',
        balanceType: '',
        amount: '',
        balanceDate: '',
        incomeType: '',
        expenditureExpenseItemName: '',
        note: '',
      },

      searchResultBalanceInfoSample: {
        balanceCode: 'I240400001',
        balanceType: '収入',
        amount: '111',
        balanceDate: '2024/11/19',
        incomeType: '1',
        incomeTypeName: '給与',
        expenditureExpenseItemName: '食費',
        note: 'あああ',
      },

      balanceType: '支出',
      amount: '',
      balanceDate: '2024-11-19',
      incomeType: '1',
      incomeTypeName: '給与',
      expenditureExpenseItemName: '食費',
      note: 'あああ',

      selectedRadio: '収入', //balanceTypeに値があればbalanceTypeを入れる
      NullFrag: true,
      NotNullFrag: false,
      SystemErrorMessage: '',
      validationFrag: false,

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
  beforeUpdate: function () {
    this.searchResultChangeFormat()

    this.editToSearchInfo.balanceCode = this.editInfor.balanceCode
    this.editToSearchInfo.balanceType = this.editInfor.balanceType
    this.editToSearchInfo.amount = this.editInfor.amount
    this.editToSearchInfo.balanceDate = this.editInfor.balanceDate
    this.editToSearchInfo.incomeType = this.editInfor.incomeType
    this.editToSearchInfo.expenditureExpenseItemName = this.editInfor.expenditureExpenseItemName
    this.editToSearchInfo.note = this.editInfor.note

    if (this.editInfor.balanceType) {
      this.selectedRadio = this.editInfor.balanceType
    }
  },
  methods: {
    async fetchUsers() {
      try {
        //支出費目を取得
        const response = await axios.get('http://localhost:8080/api/expenseItems')
        this.expenseItems = response.data
      } catch (error) {
        console.error('There was an error fetching the users!', error)
      }
    },

    searchResultChangeFormat() {
      if (this.editInfor) {
        const incomeYYYY = this.editInfor.balanceDate.substring(0, 4)
        const incomeMM = this.editInfor.balanceDate.substring(5, 7)
        const incomeDD = this.editInfor.balanceDate.substring(8, 10)
        this.editInfor.balanceDate = [incomeYYYY, incomeMM, incomeDD].join('-')
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

    finalSetIncomeType(selectType: any, incomeTypeResult: any) {
      this.searchInfo.incomeType = selectType
      this.validation.incomeTypeResult = incomeTypeResult

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
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <p>aaa{{ editToSearchInfo }}</p>

      <BalanceRadio
        @execute-method="finalSetRadioName"
        radioName="editRadio"
        :balanceType="editInfor.balanceType"
      />
      <!-- <p>{{ selectedRadio }}</p> -->

      <p>
        {{ '収支日付：' }}
        <DateInput
          @execute-method="finalSetDate"
          v-model="balanceDate"
          validatedNull="true"
          id="dateid"
          :balanceDate="editInfor.balanceDate"
        />
      </p>

      <p>{{ editInfor.balanceDate }}</p>
      <p>{{ validation.dateResult }}</p>

      <p>
        {{ '金額：' }}
        <NunberInput
          @execute-method="finalSetAmount"
          v-model="amount"
          validatedNull="true"
          :balanceAmount="editToSearchInfo.amount"
          :firstCheckFrag="amount"
        />
      </p>

      <p>editInfor1 {{ editInfor.amount }}</p>
      <p>editInfor2 {{ validation.amountResult }}</p>

      <FormSelect
        :selectedRadioName="selectedRadio"
        :expenseItems="expenseItems"
        :types="incomeTypes"
        :selectedIncomeType="editInfor.incomeType"
        :selectedExpenditureType="editInfor.expenditureExpenseItemName"
        @executeIncome-method="finalSetIncomeType"
        @executeExpenditure-method="finalSetExpenditureType"
        @executeBalance-method="finalSetBalanceType"
        validatedNull="true"
      />

      <p>{{ editInfor.incomeType }}</p>
      <p>{{ validation.incomeTypeResult }}</p>

      <TextArea
        @execute-method="finalSetNote"
        :balanceNote="editInfor.note"
        validatedNull="false"
      />
      <p>{{ note }}</p>
      <p>{{ validation.noteResult }}</p>

      <!-- <span v-if="selectedRadio == '収入'">
        <Button btname="検索" @click="detailSearchIncome" :disabled="validationFrag" />
      </span>

      <span v-if="selectedRadio == '支出'">
        <Button btname="検索" @click="detailSearchExpenditure" :disabled="validationFrag" />
      </span>

      <span v-if="selectedRadio == '指定なし'">
        <Button btname="検索" @click="detailSearchBalance" :disabled="validationFrag" />
      </span> -->

      <ButtonGroup
        btname1="保存"
        btname2="キャンセル"
        :disabledFrag="validationFrag"
        @execute-cancelMethod="returnFalse"
      />
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
