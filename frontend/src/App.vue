<template>
  <div id="app">
    <coupon-header></coupon-header>
    <coupon-body></coupon-body>
  </div>
</template>

<script>
import CouponHeader from './components/CouponHeader'
import CouponBody from './components/CouponBody'

export default {
  name: 'App',
  components: {
    CouponBody, CouponHeader
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
