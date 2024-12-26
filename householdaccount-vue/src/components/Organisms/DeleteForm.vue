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
import { Component, Emit, Vue } from 'vue-property-decorator'
import Button from '../Atoms/Button.vue'
import { number } from 'yup'
import { create } from 'node_modules/axios/index.cjs'

export default {
  name: 'Modal',
  props: ['balanceCode'],
  data() {
    return {
      DeleteInfo: {
        balanceCode: '',
        balanceType: '',
        amount: '',
        balanceDate: '',
        incomeType: '',
        expenditureExpenseItemName: '',
        note: '',
        deleteFrag: '',
        version: '',
      },

      message: '',
      statusCode: 0,
      NullFrag: true,
      NotNullFrag: false,
      SystemErrorMessage: '',
      validationFrag: false,
      deleteBtnPush: true,

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
    }
  },
  created() {
    axios
      .get('http://localhost:8080/api/searchBalance/', {
        params: { ID: this.balanceCode },
      })
      .then((response) => {
        console.log(response), (this.DeleteInfo = response.data), this.searchResultChangeFormat()
      })
  },
  mounted() {
    this.fetchExpenseItems()
  },

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
      if (this.DeleteInfo) {
        const incomeYYYY = this.DeleteInfo.balanceDate.substring(0, 4)
        const incomeMM = this.DeleteInfo.balanceDate.substring(5, 7)
        const incomeDD = this.DeleteInfo.balanceDate.substring(8, 10)
        this.DeleteInfo.balanceDate = [incomeYYYY, incomeMM, incomeDD].join('-')
      }
    },

    deleteAction: function () {
      try {
        axios.post('http://localhost:8080/api/delete', this.DeleteInfo).then((response) => {
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
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <h1>1件のデータを削除します。よろしいですか?</h1>

      <ButtonGroup
        btname1="削除"
        btname2="キャンセル"
        :deleteBtnPush="deleteBtnPush"
        @execute-actionMethod="deleteAction"
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
