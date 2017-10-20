// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Icon from 'vue-awesome/components/Icon'
import VueGoodTable from 'vue-good-table'
import VueCharts from 'vue-charts'

let VueCookie = require('vue-cookie')

Vue.config.productionTip = false
Vue.component('icon', Icon)
Vue.use(VueCookie)
Vue.use(VueGoodTable)
Vue.use(VueCharts)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
