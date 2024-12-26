<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { createApp, ref } from 'vue'
import axios from 'axios'
import Modal from './components/Modal.vue'
import SearchModal from './components/DeatailSearchModal.vue'
import EditModal from './components/Organisms/CreateEditForm.vue'
import DeleteModal from './components/Organisms/DeleteForm.vue'
import Button from './components/Atoms/Button.vue'
import moment from 'moment'

const header = ref([
  { text: '収支No' },
  { text: '収支区分' },
  { text: '収支日付' },
  { text: '種別' },
  { text: '金額' },
  { text: '備考' },
  { text: '編集' },
  { text: '削除' },
])
</script>

<script lang="ts">
export default {
  components: {
    Modal,
    SearchModal,
    EditModal,
    DeleteModal,
  },
  data() {
    return {
      targetCode: '',
      searchResultIncomeInfo: [
        {
          // balanceClassification: '',
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
          // balanceClassification: '',
          expenditureNo: '',
          expenditureDate: '',
          amount: '',
          expenditureExpenseItemName: '',
          note: '',
        },
      ],
      searchResultBalanceInfoForEdit: [
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
      searchResultBalanceInfoForEdit2: ref({
        balanceCode: '',
        balanceType: '',
        amount: '',
        balanceDate: '',
        incomeType: '',
        incomeTypeName: '',
        expenditureExpenseItemName: '',
        note: '',
      }),

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
      balanceCodeEorEdit: '',
      modal: false,
      searchModal: false,
      editModalShowFrag: false,
      deleteModalShowFrag: false,
      searchBalanceCode: '',
      searchFrag: false,
      edit: createApp(EditModal),
    }
  },
  props: {
    msg: String,
  },
  methods: {
    searchResultChangeFormat() {
      if (this.searchResultBalanceInfo.length === 0) {
        this.searchFrag = false
      } else {
        this.searchFrag = true
      }

      for (let i = 0; i < this.searchResultBalanceInfo.length; i++) {
        if (this.searchResultBalanceInfo[i].incomeType == '1') {
          this.searchResultBalanceInfo[i].incomeTypeName = '給与'
        } else if (this.searchResultBalanceInfo[i].incomeType == '2') {
          this.searchResultBalanceInfo[i].incomeTypeName = '賞与'
        } else if (this.searchResultBalanceInfo[i].incomeType == '3') {
          this.searchResultBalanceInfo[i].incomeTypeName = '副業'
        } else if (this.searchResultBalanceInfo[i].incomeType == '4') {
          this.searchResultBalanceInfo[i].incomeTypeName = 'お小遣い'
        } else if (this.searchResultBalanceInfo[i].incomeType == '5') {
          this.searchResultBalanceInfo[i].incomeTypeName = '臨時収入'
        } else if (this.searchResultBalanceInfo[i].incomeType == '6') {
          this.searchResultBalanceInfo[i].incomeTypeName = '投資'
        }
        const incomeYYYY = this.searchResultBalanceInfo[i].balanceDate.substring(0, 4)
        const incomeMM = this.searchResultBalanceInfo[i].balanceDate.substring(5, 7)
        const incomeDD = this.searchResultBalanceInfo[i].balanceDate.substring(8, 10)
        this.searchResultBalanceInfo[i].balanceDate = [incomeYYYY, incomeMM, incomeDD].join('/')
      }
    },
    moment: function (date: any) {
      return moment(date).format('l')
    },
    searchBalanceInfo: function () {
      try {
        // axios
        //   .get('http://localhost:8080/api/searchIncome/', {
        //     params: { ID: this.searchBalanceCode },
        //   })
        //   .then((response) => {
        //     console.log(response),
        //       (this.searchResultIncomeInfo = response.data),
        //       this.searchResultChangeFormat()
        //   })

        // axios
        //   .get('http://localhost:8080/api/searchExpenditure/', {
        //     params: { ID: this.searchBalanceCode },
        //   })
        //   .then((response) => {
        //     console.log(response),
        //       (this.searchResultExpenditureInfo = response.data),
        //       this.searchResultChangeFormat()
        //   })

        axios
          .get('http://localhost:8080/api/searchBalance/', {
            params: { ID: this.searchBalanceCode },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultBalanceInfo = response.data),
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
    showEditModal(balanceCode: any) {
      // モーダル表示する際の処理が必要ならここに書く
      this.balanceCodeEorEdit = balanceCode
      if (this.searchResultBalanceInfoForEdit2) {
        const edit = createApp(EditModal)
        edit.mount('#showEditModal')
      }

      this.editModalShowFrag = true
    },

    showDeleteModal(balanceCode: any) {
      // モーダル表示する際の処理が必要ならここに書く
      this.balanceCodeEorEdit = balanceCode

      const del = createApp(DeleteModal)
      del.mount('#showDeleteModal')

      this.deleteModalShowFrag = true
    },
    executeMethod(yes: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
      this.modal = false
      if (yes) {
        alert('登録されました')
      }
    },

    executeSearchMethod(incomeDetailSearchResult: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える

      this.searchModal = false
    },

    executeEditMethod(editResultMessage: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
      this.edit.unmount()
      this.editModalShowFrag = false

      if (editResultMessage) {
        alert(editResultMessage)
      }
    },

    executeDeleteMethod(deleteResultMessage: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える
      this.deleteModalShowFrag = false

      if (deleteResultMessage) {
        alert(deleteResultMessage)
      }
    },

    executeSearchResultSetMethod(DetailSearchResult: any) {
      // モーダルを非表示にして、モーダルでの選択結果によって処理を変える

      this.searchResultBalanceInfo = DetailSearchResult
      this.searchResultChangeFormat()
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
      <SearchModal
        v-show="searchModal"
        @execute-method1="executeSearchMethod"
        @execute-method2="executeSearchResultSetMethod"
      ></SearchModal>

      <div id="showEditModal" v-if="editModalShowFrag == true">
        <EditModal
          @execute-method1="executeEditMethod"
          @execute-method2="executeSearchResultSetMethod"
          :balanceCode="balanceCodeEorEdit"
        />
      </div>

      <div id="showDeleteModal" v-if="deleteModalShowFrag == true">
        <DeleteModal
          @execute-method1="executeDeleteMethod"
          :balanceCode="balanceCodeEorEdit"
        ></DeleteModal>
      </div>

      <div class="table_box" v-if="searchFrag == true">
        <table class="table_style">
          <thead>
            <tr>
              <th class="sticky" v-for="(label, key) in header" :key="key">
                {{ label.text }}
              </th>
            </tr>
          </thead>
          <tbody v-for="balancedata in searchResultBalanceInfo" :key="balancedata.balanceCode">
            <tr v-if="balancedata.balanceType == '収入'">
              <td>{{ balancedata.balanceCode }}</td>
              <td>{{ balancedata.balanceType }}</td>
              <td>{{ balancedata.balanceDate }}</td>
              <td>{{ balancedata.incomeTypeName }}</td>
              <td>{{ balancedata.amount }}</td>
              <td>{{ balancedata.note }}</td>
              <td><button @click="showEditModal(balancedata.balanceCode)">編集</button></td>
              <td><button @click="showDeleteModal(balancedata.balanceCode)">削除</button></td>
            </tr>
            <tr v-if="balancedata.balanceType == '支出'">
              <td>{{ balancedata.balanceCode }}</td>
              <td>{{ balancedata.balanceType }}</td>
              <td>{{ balancedata.balanceDate }}</td>
              <td>{{ balancedata.expenditureExpenseItemName }}</td>
              <td>{{ balancedata.amount }}</td>
              <td>{{ balancedata.note }}</td>
              <td><button @click="showEditModal(balancedata.balanceCode)">編集</button></td>
              <td><button @click="showDeleteModal(balancedata.balanceCode)">削除</button></td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="searchResultBalanceInfo.length === 0">
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
