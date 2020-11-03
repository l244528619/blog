// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import api from '@/api'
import store from '@/store';


//定义全局默认配置所有的请求头中都带上Authorization的token
axios.defaults.headers.common['headerStore'] = ''

Vue.config.productionTip = false;
Vue.prototype.$api =api;
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
