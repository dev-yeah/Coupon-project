// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueResource from 'vue-resource'
import App from './App'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'sweetalert2/dist/sweetalert2.css'

// ajax
import axios from 'axios'

Vue.config.productionTip = false

Object.defineProperty(Vue.prototype, '$axios', {value: axios})

Vue.use(BootstrapVue)
Vue.use(VueResource)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>'
})
