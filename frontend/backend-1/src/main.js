import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import '@/styles/index.css'
import './premission.js'

// import {mock} from '../mock/index.js'
// if(process.env.NODE_ENV === 'development'){
//   mock()
// }

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
