<template>
  <div class="container">
    <!-- Email Box -->
    <div class="box">
    <h3>Email Address</h3>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="exampleInputGroup1"
                    label-for="exampleInput1"
                    description="We'll never share your email with anyone else.">
        <b-form-input id="exampleInput1"
                      type="email"
                      v-model="form.email"
                      required
                      placeholder="Enter your email XD">
        </b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    </div>
    <!-- Coupon List Box -->
    <h3>List of issued coupons</h3>
    <div class="page-count-box">
    <b-form-select v-model="selected" @change="changePageCount" :options="countOption" size="10" />
    </div>
    <div>
      <b-table :small=true head-variant="dark  " striped hover :items="coupons"
       :fields="fields" :show-empty=true empty-text="There are no recordsd to show"
       empty-filtered-text="bbb">
        <template slot="table-caption empty">
          This is coupon list being issued.
        </template>
      </b-table>
      </div>
      <b-pagination align="center" size="sm" :total-rows="totalRow"
               v-model="pageNum" @change="changePageNum" :per-page="pageCount">
      </b-pagination>
    <br>
  </div>
</template>

<script>
import swal from 'sweetalert2'

const pageCountSelect = [
  {value: 5, text: '5'},
  {value: 10, text: '10'},
  {value: 15, text: '15'}
]

export default {
  name: 'coupon-body',
  data () {
    return {
      form: {
        email: ''
      },
      apiUrl: 'http://localhost:9000/coupon/',
      couponCode: '',
      show: true,
      fields: [ 'id', 'email', 'couponCode', 'issueDttm' ],
      coupons: [],
      selected: 5,
      totalRow: 0,
      pageNum: 1,
      pageCount: 5,
      countOption: pageCountSelect
    }
  },
  mounted () {
    this.getCouponsByPaging()
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault()
      this.checkEmail()
    },
    onReset (evt) {
      evt.preventDefault()
      /* Reset our form values */
      this.form.email = ''
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true }) /* nextTick : 전체가 렌더링된 상태 보장 */
    },

    checkEmail: function () {
      var that = this
      this.$axios.get(this.apiUrl + this.form.email, {
        email: this.form.email
      }).then(function (response) {
        if (response.data.validEmail) {
          swal({
            title: 'Would you like to get a coupon?',
            text: 'Press OK to issue a coupon!',
            type: 'question',
            showCancelButton: true,
            confirmButtonColor: '#0090EA',
            cancelButtonColor: '#E52065',
            cancelButtonText: 'NO'
          }).then(result => {
            if (result.value) {
              that.issueEmail()
            }
          })
        } else {
          swal({
            title: 'Can not issue a coupon :(',
            text: 'The coupon has already been issued with this email!',
            type: 'error',
            confirmButtonColor: '#E52065'
          })
        }
      })
    },

    issueEmail: function () {
      var that = this
      this.$axios.post(this.apiUrl, {
        email: this.form.email
      }).then(function (response) {
        swal({
          type: 'success',
          text: 'Coupon code : [' + response.data.couponCode + ']'
        }).then(() => {
          console.log('들어오나')
          that.getCouponsByPaging()
        })
        that.form.email = ''
      })
    },

    getCouponsByPaging: function () {
      this.$axios.get(this.apiUrl, {
        params: {
          pageNum: this.pageNum,
          pageCount: this.pageCount
        }
      }).then(function (response) {
        this.coupons = response.data.list
        this.totalRow = response.data.totalRow
      }.bind(this))
    },

    changePageNum: function (page) {
      this.pageNum = page
      this.getCouponsByPaging()
    },

    changePageCount: function (count) {
      this.pageCount = count
      this.getCouponsByPaging()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
div .box {
  margin-top: 60px;
  margin-bottom: 100px;
}
div .page-count-box {
  width: 10% !important;
}
</style>
