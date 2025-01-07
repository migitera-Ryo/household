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
import { create } from 'node_modules/axios/index.cjs'

export default {
  name: 'Modal',
  props: ['balanceCode'],
  data() {
    return {
      SearchToEditInfo: {
        balanceCode: '',
        balanceType: '',
        amount: '',
        balanceDate: '',
        incomeType: '',
        expenditureExpenseItemName: '',
        note: '',
        version: '',
      },

      message: '',
      statusCode: 0,
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
  created() {
    axios
      .get('http://localhost:8080/api/searchBalance/', {
        params: { ID: this.balanceCode },
      })
      .then((response) => {
        console.log(response),
          (this.SearchToEditInfo = response.data),
          this.searchResultChangeFormat()
      })
  },
  mounted() {
    this.fetchExpenseItems()
  },

  // beforeUpdate: function () {
  //   this.searchResultChangeFormat()

  //   if (this.editInfor.balanceType) {
  //     this.selectedRadio = this.editInfor.balanceType
  //   }
  // },

  methods: {
    async fetchExpenseItems() {
      try {
        //支出費目を取得
        const response = await axios.get('http://localhost:8080/api/expenseItems')
        this.expenseItems = response.data
      } catch (error) {
        console.error('There was an error fetching the users!', error)
      }
    },

    searchResultChangeFormat() {
      if (this.SearchToEditInfo) {
        const incomeYYYY = this.SearchToEditInfo.balanceDate.substring(0, 4)
        const incomeMM = this.SearchToEditInfo.balanceDate.substring(5, 7)
        const incomeDD = this.SearchToEditInfo.balanceDate.substring(8, 10)
        this.SearchToEditInfo.balanceDate = [incomeYYYY, incomeMM, incomeDD].join('-')
      }
    },

    editAction: function () {
      try {
        axios.post('http://localhost:8080/api/edit', this.SearchToEditInfo).then((response) => {
          console.log(response),
            (this.message = response.data),
            (this.statusCode = response.status),
            this.statusCheck()
        })
      } catch (error) {
        alert(error)
      }
    },

    statusCheck() {
      if (this.statusCode == 200) {
        this.$emit('execute-method1', this.message)
      }
    },

    returnFalse() {
      this.$emit('execute-method1')
    },

    finalSetDate(dateResult: any, finalDate: any) {
      this.validation.dateResult = dateResult
      this.SearchToEditInfo.balanceDate = finalDate
      this.validationCheck()
    },

    finalSetNote(finalNote: any, noteResult: any) {
      this.SearchToEditInfo.note = finalNote
      this.validation.noteResult = noteResult
      this.validationCheck()
    },

    finalSetAmount(amountResult: any, finalamount: any) {
      this.validation.amountResult = amountResult
      this.SearchToEditInfo.amount = finalamount
      this.validationCheck()
    },

    finalSetIncomeType(selectType: any, incomeTypeResult: any) {
      this.SearchToEditInfo.incomeType = selectType
      this.validation.incomeTypeResult = incomeTypeResult

      this.validationCheck()
    },

    finalSetExpenditureType(selectType: any, expenditureTypeResult: any) {
      this.SearchToEditInfo.expenditureExpenseItemName = selectType
      this.validation.expenditureTypeResult = expenditureTypeResult

      this.validationCheck()
    },

    finalSetBalanceType(
      selectType: any,
      selectSubType: any,
      incomeType: any,
      expenditureTypeResult: any,
    ) {
      this.SearchToEditInfo.incomeType = selectType
      this.SearchToEditInfo.expenditureExpenseItemName = selectSubType
      this.validation.incomeTypeResult = incomeType
      this.validation.expenditureTypeResult = expenditureTypeResult

      this.validationCheck()
    },

    finalSetRadioName(finalRadioName: any) {
      this.SearchToEditInfo.balanceType = finalRadioName
    },

    validationCheck() {
      if (this.SearchToEditInfo.balanceType == '収入') {
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
      } else if (this.SearchToEditInfo.balanceType == '支出') {
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
      } else if (this.SearchToEditInfo.balanceType == '指定なし') {
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
      <BalanceRadio
        @execute-method="finalSetRadioName"
        radioName="editRadio"
        :balanceType="SearchToEditInfo.balanceType"
        :disabledFrag="true"
        :key="SearchToEditInfo.balanceType"
      />

      <p>
        {{ '収支日付：' }}
        <DateInput
          @execute-method="finalSetDate"
          validatedNull="true"
          id="dateid"
          :balanceDate="SearchToEditInfo.balanceDate"
          :key="SearchToEditInfo.balanceDate"
        />
      </p>
      <p>{{ validation.dateResult }}</p>

      <p>
        {{ '金額：' }}
        <NunberInput
          @execute-method="finalSetAmount"
          validatedNull="true"
          :balanceAmount="SearchToEditInfo.amount"
          firstCheckFrag="true"
          :key="SearchToEditInfo.amount"
        />
      </p>
      <p>{{ validation.amountResult }}</p>

      <FormSelect
        :selectedRadioName="SearchToEditInfo.balanceType"
        :expenseItems="expenseItems"
        :types="incomeTypes"
        :selectedIncomeType="SearchToEditInfo.incomeType"
        :selectedExpenditureType="SearchToEditInfo.expenditureExpenseItemName"
        :key="SearchToEditInfo.incomeType || SearchToEditInfo.expenditureExpenseItemName"
        @executeIncome-method="finalSetIncomeType"
        @executeExpenditure-method="finalSetExpenditureType"
        @executeBalance-method="finalSetBalanceType"
        validatedNull="true"
      />
      <p>{{ validation.incomeTypeResult }}</p>

      <TextArea
        @execute-method="finalSetNote"
        :balanceNote="SearchToEditInfo.note"
        validatedNull="false"
        :key="SearchToEditInfo.note"
      />
      <p>{{ SearchToEditInfo.note }}</p>
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
        @execute-actionMethod="editAction"
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
