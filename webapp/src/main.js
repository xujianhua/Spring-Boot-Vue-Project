// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
Vue.use(MintUI);

import axios from './util/interceptor.js'
//将axios挂载到prototype上，在组件中可以直接使用this.$http访问
Vue.prototype.$http = axios;

import store from './store/index'

import './assets/css/normalize.css'  //自定义CSS

Vue.config.productionTip = false
/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
