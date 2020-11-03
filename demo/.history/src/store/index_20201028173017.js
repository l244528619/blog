import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    /**
    * 公共数据存放区 */
    state: {
        headerStore: window.sessionStorage.getItem('headertoken') ? window.sessionStorage.getItem('headertoken') : '',

        // 通过vuex来传递数据 若传递的数据为对象 则要使用JSON.parse转换为字符串
        homeAdmin: sessionStorage.getItem("hmoeName") || {},


    },
    /**
     * 修改数据要通过的mutations */
    mutations: {
        headerSet: (state, token) => {
            state.headerStore = token;
            window.sessionStorage.setItem('headerStore', token)
        },
        // 传递的数据
        homeSet: (state, data) => {
            state.homeAdmin = data
            // 数据为对象时
            // state.homeAdmin = {
            //     ...data
            // }
            console.log(data)
            window.sessionStorage.setItem('homeAdmin', data)
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
export default store