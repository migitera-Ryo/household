<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { createApp, ref } from 'vue'
import axios from 'axios'

const incomeHeader = ref([
  { text: '収支No' },
  { text: '収支区分' },
  { text: '収支日付' },
  { text: '種別' },
  { text: '金額' },
  { text: '備考' },
  { text: '編集' },
  { text: '削除' },
])

const dataes = [
  { no: 1, name: 'Daiki', email: 'daiki@hoge.net' },
  { no: 2, name: 'Naoki', email: 'naoki@hoge.net' },
]
</script>

<script lang="ts">
import Modal from './components/Modal.vue'
import SearchModal from './components/DeatailSearchModal.vue'
import Button from './components/Atoms/button.vue'
import moment from 'moment'

export default {
  name: 'HollowWorld',
  components: {
    Modal,
    SearchModal,
  },
  data() {
    return {
      grades: [
        {
          no: 'I240400001',
          balance: '収入',
          balance_date: '2024/4/20',
          type: '貯金',
          amount: 200000,
          note: '',
        },
        {
          no: 'E240400001',
          balance: '支出',
          balance_date: '2024/4/20',
          type: '食費',
          amount: 1000,
          note: 'あああ',
        },
      ],
      searchResultIncomeInfo: [
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
      searchResultExpenditureInfo: [
        {
          balanceClassification: '',
          expenditureNo: '',
          expenditureDate: '',
          amount: '',
          expenditureExpenseItemName: '',
          note: '',
        },
      ],
      message: '登録画面',
      modal: false,
      searchModal: false,
      searchBalanceCode: '',
      i: '',
    }
  },
  props: {
    msg: String,
  },
  methods: {
    searchResultChangeFormat() {
      for (let i = 0; i < this.searchResultIncomeInfo.length; i++) {
        if (this.searchResultIncomeInfo[i].incomeType == '1') {
          this.searchResultIncomeInfo[i].incomeTypeName = '給与'
        } else if (this.searchResultIncomeInfo[i].incomeType == '2') {
          this.searchResultIncomeInfo[i].incomeTypeName = '賞与'
        } else if (this.searchResultIncomeInfo[i].incomeType == '3') {
          this.searchResultIncomeInfo[i].incomeTypeName = '副業'
        } else if (this.searchResultIncomeInfo[i].incomeType == '4') {
          this.searchResultIncomeInfo[i].incomeTypeName = 'お小遣い'
        } else if (this.searchResultIncomeInfo[i].incomeType == '5') {
          this.searchResultIncomeInfo[i].incomeTypeName = '臨時収入'
        } else if (this.searchResultIncomeInfo[i].incomeType == '6') {
          this.searchResultIncomeInfo[i].incomeTypeName = '投資'
        }
        const incomeYYYY = this.searchResultIncomeInfo[i].incomeDate.substring(0, 4)
        const incomeMM = this.searchResultIncomeInfo[i].incomeDate.substring(5, 7)
        const incomeDD = this.searchResultIncomeInfo[i].incomeDate.substring(8, 10)
        this.searchResultIncomeInfo[i].incomeDate = [incomeYYYY, incomeMM, incomeDD].join('/')
      }

      for (let i = 0; i < this.searchResultExpenditureInfo.length; i++) {
        const expenditureYYYY = this.searchResultExpenditureInfo[i].expenditureDate.substring(0, 4)
        const expenditureMM = this.searchResultExpenditureInfo[i].expenditureDate.substring(5, 7)
        const expenditureDD = this.searchResultExpenditureInfo[i].expenditureDate.substring(8, 10)
        this.searchResultExpenditureInfo[i].expenditureDate = [
          expenditureYYYY,
          expenditureMM,
          expenditureDD,
        ].join('/')
      }
    },
    moment: function (date: any) {
      return moment(date).format('l')
    },
    searchBalanceInfo: function () {
      try {
        axios
          .get('http://localhost:8080/api/searchIncome/', {
            params: { ID: this.searchBalanceCode },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultIncomeInfo = response.data),
              this.searchResultChangeFormat()
          })

        axios
          .get('http://localhost:8080/api/searchExpenditure/', {
            params: { ID: this.searchBalanceCode },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultExpenditureInfo = response.data),
              this.searchResultChangeFormat()
          })
      } catch (error) {
        console.error('There was an error fetching the users!', error)
        alert(error)
      }
    },
    showModal() {
      // モーダル表示する際の処理が必要ならここに書く
      this.modal = true
    },
    showSearchModal() {
      // モーダル表示する際の処理が必要ならここに書く
      this.searchModal = true
    },
    executeMethod(yes: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
      this.modal = false
      if (yes) {
        alert('登録されました')
      }
    },
    executeSearchMethod(yes: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
      this.searchModal = false
    },
  },
}
</script>

<template>
  <v-app>
    <v-app-bar>
      <v-app-bar-title>
        <header>
          <h1 style="color: #ffffff">家計簿システム</h1>
        </header>
      </v-app-bar-title>
    </v-app-bar>

    <v-main>
      <input
        type="text"
        v-model="searchBalanceCode"
        class="search_text"
        placeholder="Type here"
        @keyup.enter="searchBalanceInfo"
      />

      <div class="bt_create">
        <button align="right" class="bt_create" @click="showModal()">収支登録</button>
        <modal v-show="modal" @execute-method="executeMethod"></modal>
      </div>

      <Button @click="showSearchModal()" btname="検索" />
      <SearchModal v-show="searchModal" @execute-method="executeSearchMethod"></SearchModal>

      <div
        class="table_box"
        v-if="searchResultIncomeInfo.length > 1 || searchResultExpenditureInfo.length > 1"
      >
        <table class="table_style">
          <thead>
            <tr>
              <th class="sticky" v-for="(label, key) in incomeHeader" :key="key">
                {{ label.text }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-if="searchResultIncomeInfo.length > 1"
              v-for="balancedata in searchResultIncomeInfo"
              :key="balancedata.incomeNo"
            >
              <td>{{ balancedata.incomeNo }}</td>
              <td>{{ '収入' }}</td>
              <td>{{ balancedata.incomeDate }}</td>
              <td>{{ balancedata.incomeTypeName }}</td>
              <td>{{ balancedata.amount }}</td>
              <td>{{ balancedata.note }}</td>
              <td><button>編集</button></td>
              <td><button>削除</button></td>
            </tr>
            <tr
              v-if="searchResultExpenditureInfo.length > 1"
              v-for="balancedata in searchResultExpenditureInfo"
              :key="balancedata.expenditureNo"
            >
              <td>{{ balancedata.expenditureNo }}</td>
              <td>{{ '支出' }}</td>
              <td>{{ balancedata.expenditureDate }}</td>
              <td>{{ balancedata.expenditureExpenseItemName }}</td>
              <td>{{ balancedata.amount }}</td>
              <td>{{ balancedata.note }}</td>
              <td><button>編集</button></td>
              <td><button>削除</button></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="searchResultIncomeInfo.length < 1 && searchResultExpenditureInfo.length < 1">
        <img src="./assets/1.png" class="logo" width="125" height="125" />
        <p class="logo">{{ '検索結果が見つかりません' }}</p>
      </div>
    </v-main>
  </v-app>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100ch;
  background: #000000;
  width: 100%;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

.bt_create {
  width: 100px;
  height: 30px;
}
.search_text {
  width: 300px;
  height: 30px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.table_box {
  overflow-y: auto;
  height: 300px;
  width: auto;
  -webkit-overflow-scrolling: touch;
  border-top: 0;
  border-spacing: 0;
  border-bottom: #000;
}

table {
  border-spacing: 0;
  width: 100%;
  height: 100%;
  display: table;
  border-collapse: collapse;
  box-sizing: border-box;
  text-indent: initial;
  unicode-bidi: isolate;
  border-color: gray;
}

/*スクロールバー*/
.sticky {
  position: sticky;
  top: 0;
  left: 1;
  background: none;
  border-top: none;
  border-bottom: none;
  background-color: #d0cece;
  box-shadow: 1px 0 0 #d0cece;
  height: 100%;
  text-wrap: wrap;
}

.sticky:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #d0cece;
  z-index: -1;
}

table td {
  text-align: center;
  background: #f5f5f5;
  border: 3px solid white;
  border-spacing: 10px;
}
</style>
