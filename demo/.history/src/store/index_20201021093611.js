import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    /**
    * 公共数据存放区 */
    state: {
        headerStore: window.sessionStorage.getItem('headertoken') ? window.sessionStorage.getItem('headertoken') : ''
    },
    /**
     * 修改数据要通过的mutations */
    mutations: {
        headerSet: (state, token) => {
            state.headerStore = token;
            window.sessionStorage.setItem('headerStore', token)
        }
    }, 
    /**
     * 获取数据可以用上的getters */
    getters: {
        getToken(state) {
            return state.headerStore
        }
    },
    /**
     * 异步处理可能用上的actions */
    actions: {
    }


})