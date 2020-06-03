import Vue from 'vue';
import Vuex from 'vuex';
import actions from './actions.js'
import mutations from './mutations.js'
import getters from './getters.js'

Vue.use(Vuex);

const state = {
  userName: '', //用户名
  isLogin: false,
}

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations
});

export default store;
