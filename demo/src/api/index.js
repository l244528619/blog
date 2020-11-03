import {liveApi} from './APIS/live'
import {manageApi} from './APIS/manage'
// 使用该api 要在main.js中引入并且绑定在原型中
const api = {
    liveApi,
    manageApi,
    
}
export default api
