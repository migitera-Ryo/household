<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import SearchBalanceRadio from './Molecules/SearchBalanceRadio.vue'
import SearchBaseDate from './Molecules/SearchBaseDate.vue'
import SearchBaseAmount from './Molecules/SearchBaseAmount.vue'
import FormSelect from './Atoms/FormSelect.vue'
import TextArea from './Atoms/TextArea.vue'
import Button from './Atoms/button.vue'

export default {
  name: 'Modal',
  props: ['message'],
  components: {
    //SearchBalanceRadio,
    // SearchBaseDate,
    // SearchBaseAmount,
    // FormSelect,
    // TextArea,
    // Button
  },
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
      detailSearchResultInfo: [
        {
          balanceClassification: '',
          incomeNo: '',
          incomeDate: '',
          amount: '',
          incomeType: '',
          incomeTypeName: '',
          note: '',
        },
      ],
      incomeInfo: {
        incomeDate: '',
        amount: '',
        incomeType: '',
        note: '',
        incomefromDate: '',
        incometoDate: '',
        incomefromAmount: '',
        incometoAmount: '',
      },
      expenditureInfo: {
        expenditureDate: '',
        amount: '',
        expenditureItemCode: '',
        expenditureItemName: '',
        note: '',
      },

      selectedRadio: '収入',
      selectedRadio2: '収入',
      isPush1: true,
      isPush2: true,
      message: null,
      SystemErrorMessage: '',
      amountErrorFrag: false,
      dateErrorFrag: true,
      expenditureItemErrorFrag: true,
      incomeTypeErrorFrag: true,
      noteErrorFrag: true,

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

      expenseItemsSample: [
        {
          expenditureExpenseItemCode: 'E0001',
          expenditureExpenseItemName: '食費',
          expenditureExpenseItemNameKana: 'ショクヒ',
        },
        {
          expenditureExpenseItemCode: 'E0002',
          expenditureExpenseItemName: '日用品',
          expenditureExpenseItemNameKana: 'ニチヨウヒン',
        },
      ],

      validation: {
        dateResult: '',
        typeResult: '',
        amountResult: '',
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

    detailSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/detailsearch', {
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
            console.log(response), (this.detailSearchResultInfo = response.data)
          })
      } catch (error) {
        alert('検索できません')
      }
    },

    returnFalse() {
      this.$emit('execute-method', false)
    },
    // returnTrue() {
    //   this.$emit('execute-method', true)
    // },
    // pushedBtn1() {
    //   this.isPush1 = false
    //   this.isPush2 = true
    //   //this.income_type.text = '';
    // },
    // pushedBtn2() {
    //   this.isPush1 = true
    //   this.isPush2 = false
    //   //this.selectedExpenseItem = '';
    // },

    // incomeCreateCheckValidate() {
    //   const amount_error_message = this.amonutValidate(
    //     this.searchInfo.fromAmount,
    //     this.searchInfo.toAmount,
    //   )
    //   const date_error_message = this.dateValidate(this.searchInfo.fromDate, this.searchInfo.toDate)
    //   const incomeType_error_message = this.incomeTypeValidate(this.searchInfo.incomeType)
    //   const note_error_message = this.noteValidate(this.searchInfo.note)
    //   if (
    //     amount_error_message === true &&
    //     date_error_message === true &&
    //     incomeType_error_message === true &&
    //     note_error_message === true
    //   ) {
    //     return false
    //   } else {
    //     return true
    //   }
    // },
    // expenditureCreateCheckValidate() {
    //   const amount_error_message = this.amonutValidate(
    //     this.searchInfo.fromAmount,
    //     this.searchInfo.toAmount,
    //   )
    //   const date_error_message = this.dateValidate(this.searchInfo.fromDate, this.searchInfo.toDate)
    //   const expenditureItem_error_message = this.expenditureItemValidate(
    //     this.expenditureInfo.expenditureItemCode,
    //   )
    //   const note_error_message = this.noteValidate(this.searchInfo.note)
    //   if (
    //     amount_error_message === true &&
    //     date_error_message === true &&
    //     expenditureItem_error_message === true &&
    //     note_error_message === true
    //   ) {
    //     return false
    //   } else {
    //     return true
    //   }
    // },

    amountCheckValidate() {
      const amount_error_message = this.amonutValidate(
        this.searchInfo.fromAmount,
        this.searchInfo.toAmount,
      )
      if (amount_error_message === true) {
        this.validation.amountResult = ''
      } else {
        this.validation.amountResult = amount_error_message
      }
    },

    dateCheckValidate() {
      const date_error_message = this.dateValidate(this.searchInfo.fromDate, this.searchInfo.toDate)
      if (date_error_message === true) {
        this.validation.dateResult = ''
      } else {
        this.validation.dateResult = date_error_message
      }
    },

    noteCheckValidate() {
      const note_error_message = this.noteValidate(this.searchInfo.note)
      if (note_error_message === true) {
        this.validation.noteResult = ''
      } else {
        this.validation.noteResult = note_error_message
      }
    },

    dateValidate(fromDate: any, toDate: any) {
      const regex = /^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/
      if (fromDate == '' && toDate == '') {
        return true
      } else {
        if (!fromDate.match(/^([0-9]{4})-(0[1-9]|1[0-2])-([0-2][0-9]|3[01])$/)) {
          return 'YYYY/MM/DDの形式で入力してください'
        }
        return true
      }
    },

    amonutValidate(fromAmount: any, toAmount: any) {
      const regex = /^[0-9]+(\.[0-9]+)?$/
      if (fromAmount == '' && toAmount == '') {
        return true
      } else {
        if (fromAmount.length > 8 || toAmount.length > 8) {
          return '8桁以内で入力してください'
        }

        if (fromAmount != '') {
          if (!regex.test(fromAmount)) {
            return '金額(from)を半角の整数で入力してください'
          } else if (toAmount == '') {
            return true
          }
        }

        if (toAmount != '') {
          if (!regex.test(toAmount)) {
            return '金額(to)を半角の整数で入力してください'
          } else if (fromAmount == '') {
            return true
          }
        }

        return true
      }
    },

    incomeTypeValidate(incometype: any) {
      if (!incometype) {
        return '選択してください'
      }
      return true
    },

    expenditureItemValidate(expenditureItem: any) {
      if (!expenditureItem) {
        return '選択してください'
      }
      return true
    },

    noteValidate(note: any) {
      if (note.length > 200) {
        return '200文字以内で入力してください'
      }
      return true
    },

    finalSetDate(fromDate: any, toDate: any) {
      this.searchInfo.fromDate = fromDate
      this.searchInfo.toDate = toDate
      this.dateCheckValidate()
    },

    finalSetNote(finalNote: any) {
      this.searchInfo.note = finalNote
      this.noteCheckValidate()
    },

    finalSetAmount(fromAmount: any, toAmount: any) {
      this.searchInfo.fromAmount = fromAmount
      this.searchInfo.toAmount = toAmount
      this.amountCheckValidate()
    },

    finalSetType(selectType: any, selectSubType: any) {
      if (this.selectedRadio2 == '収入') {
        this.searchInfo.incomeType = selectType
      } else if (this.selectedRadio2 == '支出') {
        this.searchInfo.expenditureItemName = selectType
      } else if (this.selectedRadio2 == '指定なし') {
        this.searchInfo.incomeType = selectType
        this.searchInfo.expenditureItemName = selectSubType
      }
    },

    finalSetRadioName(finalRadioName: any) {
      this.selectedRadio2 = finalRadioName
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <SearchBalanceRadio @execute-method="finalSetRadioName" />

      <SearchBaseDate @execute-method="finalSetDate" />
      <p>{{ validation.dateResult }}</p>

      <SearchBaseAmount @execute-method="finalSetAmount" />
      <p>{{ validation.amountResult }}</p>

      <FormSelect
        :selectedRadioName="selectedRadio2"
        :expenseItems="expenseItems"
        :types="incomeTypes"
        @execute-method="finalSetType"
      />

      <TextArea @execute-method="finalSetNote" />
      <p>{{ validation.noteResult }}</p>

      <Button btname="検索" @click="detailSearch" />

      <p>{{ searchInfo.fromDate }}</p>
      <p>{{ searchInfo.toDate }}</p>
      <p>{{ searchInfo.fromAmount }}</p>
      <p>{{ searchInfo.toAmount }}</p>
      <p>{{ searchInfo.incomeType }}</p>
      <p>{{ searchInfo.expenditureItemName }}</p>
      <p>{{ searchInfo.note }}</p>

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
