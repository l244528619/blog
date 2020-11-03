import Vue from 'vue'
import Vuex from 'vuex' 
Vue.use(Vuex)
const store = new Vuex.Store({
     /**
     * 公共数据存放区 */
    state:{
        headerStore: window.sessionStorage.getItem('headertoken')?window.sessionStorage.getItem('headertoken'):''
    },
    /**
     * 修改数据要通过的mutations */

})