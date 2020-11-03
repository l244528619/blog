import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    /**
    * 公共数据存放区 */
    state: {
        // getItem(后面的要和前面的一样 才会使刷新时页面参数不丢失)
        headerStore: window.sessionStorage.getItem('headerStore') ? window.sessionStorage.getItem('headerStore') : '',

        // 通过vuex来传递数据 若传递的数据为对象 则要使用JSON.parse转换为字符串
        userName: window.sessionStorage.getItem("userName") || {},


    },
    /**
     * 修改数据要通过的mutations */
    mutations: {
        headerSet: (state, token) => {
            state.headerStore = token;
            window.sessionStorage.setItem('headerStore', token)
        },
        // 传递的数据
        userSet: (state, data) => {
            state.userName = data
            // 数据为对象时
            // state.homeAdmin = {
            //     ...data
            // }
            console.log(data)
            window.sessionStorage.setItem('userName', data)
        }
    },
    /**
     * 获取数据可以用上的getters */
    getters: {
        // getToken(state) {
        //     return state.headerStore
        // },

        // getHome:(state)=>{
        //     return state.userName
        // }
    },
    /**
     * 异步处理可能用上的actions */
    actions: {
    }


})
export default store