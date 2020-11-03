import axios from 'axios'
import qs from 'qs'
import { error } from 'shelljs';
import {
    Message
} from 'element-ui';

const baseUrl="http://47.98.226.42/"
//axios全局配置
const request = axios.create({
    //请求接口
    baseURL: baseUrl,
    //超时设置
    timeout: 8000,
    //请求头设置
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
    },
    //携带cookie
    withCredentials: true,

})
// 在发送请求之前做些什么("请求拦截器")
request.interceptors.request.use((config) => {
    //假设接口需要对接token，可以用store保存token,在拦截器中设置到header中
    //store需要安装、引入
    if(sessionStorage.getItem('headertoken')){
        config.header.headertoken = sessionStorage.getItem('headertoken')
    }
    console.log(config, "cccccccccccc",essionStorage.getItem('headertoken'));
    return config               //注意响应拦截器中也要添加返回值!!!!!!!!!!!!!!!!!!!!
}, error => {
    // 对请求错误做些什么
    return Promise.reject(error);
}
)
const errorHandle= (status,msg="")=>{
        // 200以外状态处理
        switch (status) {
            case 304:
                break;
            case 408:
                Message.error("未找到token信息！", 'error');
                break;
            case 400:
                Message.error("参数错误！", 'error');
                break;
            case 401:
                Message.error("未登录，请先登陆", 'error');
                break;
            case 403:
                Message.error("跨域问题", 'error')
                break;
            case 404:
                Message.error('请求地址出错', 'error')
                break;
            case 500:
                Message.error('服务器内部错误', 'error')
                break;
            case 504:
                Message.error('网关超时', 'error');
                break;
            case 503:
                Message.error('服务不可用', 'error')
                break;
            default:
                Message.error('其他错误，请进一步排查', 'error')
        }
    }

//respone拦截器==>对响应做处理
request.interceptors.response.use(
    // 处理响应数据
    response => {
        // 如果返回的状态码为200，说明接口请求成功，可以正常拿到数据     
        // 否则的话抛出错误
        if (response.status == 200) {
            return Promise.resolve(response);
        } else {
            return Promise.reject(response);
        }
    },
    // 服务器状态码不是2开头的的情况
    error => {
        if (error.response) {
            errorHandle(error.response.status)
        }else {
            errorHandle(0)
        }
        return Promise.reject(error)
    }
)

let http ={
    /** get 请求
     * @param  {接口地址} url
     * @param  {请求参数} params
     */
    get:function(url,params){
        return new Promise((resolve,reject)=>{
            request.get(url,{params})
            .then((response)=>{
                resolve(response.data);
            })
            .catch((error)=>{
                reject(error)
            })
        })
    },
    post:function(url,params){
        return new Promise((resolve,reject)=>{
            request.post(url,qs.stringify(params))
            .then(response=>{
                resolve(response.data);
            })
            .catch(error=>{
                reject(error)
            })
        })
    }
}
export default http