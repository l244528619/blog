import Vue from 'vue';
import qs from 'querystring';
import axios from 'axios';
import iView from 'iview';

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
Vue.use(iView);
window.Vue = Vue;
window.Qs = qs;
window.axios = axios;
