import axios from 'axios'
import {
  MessageBox,
  Message
} from 'element-ui'
import store from '@/store'
import _ from 'lodash'
import {
  getToken,
  removeToken
} from '@/utils/auth'
import router, {
  resetRouter
} from '@/router'
const secretHelper = require("@/utils/secretHelper");

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 18000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['Authorization'] = 'Bearer ' + getToken()
      // Authorization: Bearer 0b79bab50daca910b000d4f1a2b675d604257e42
      const access_token = getToken()
      config.params = config.params ? _.assign(config.params, {
        access_token: access_token
      }) : {
        access_token
      }
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response
    // if the custom code is not 20000, it is judged as an error.
    if (res.status !== 200 && res.status !== 204) {
      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.status === 401) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      } else {
        Message({
          message: res.message || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      }
      console.log(res)
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      // console.log(JSON.parse(secretHelper.decrypt('1c7e2886c0885f20b9d49105393db3a585bc474ae55ad76a0f4636e511bc0e3e411629e356a8571bdd7df7ce34b35962b29611e7db190b8cf6211496d5b4cdfe6e5e344837189f3649fcddf30fa6a7b94fd71c408e8f8c3ba6a58e2be0be7bdf4661e40e6ff6e00d653e1c87182ebdcf771b61b40be4988ae46a7408798de80303ffdb19d21b0e175e82a1c37b954c5ffb4df37bdbc852c4760f003d321d0cdbef0f7c8aee4986806edb2fea9f3dc307', "07291f5384e49b358f00b2db945f3f07", "d7b81e2b6451693a")))
      
      //data解密----length40是临时判断
      if (process.env.NODE_ENV == 'production' || res.data.length > 20) {
        res.data = res.data ? JSON.parse(secretHelper.decrypt(res.data, "07291f5384e49b358f00b2db945f3f07", "d7b81e2b6451693a")) : res.data;
      }
      return res
    }
  },
  error => {
    console.log('err' + error)
    if (error.message.includes("401")) {
      // to re-login
      MessageBox.confirm('您的账号已在别处登录,请重新登录', '提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeToken();
        location.reload();
      })
    } else {
      Message({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)
export default service
