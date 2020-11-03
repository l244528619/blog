// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import api from '@/api'
import store from '@/store';
// import axios  from 'axios ';


// //定义全局默认配置所有的请求头中都带上Authorization的token
// axios.defaults.headers.common['Authorization'] = ''

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




// //所有求情发送出去之前要经过axios请求拦截器
// axios.interceptors.request.use(
//   config => {
//     if(config.url != '/login'){			//如果不是登录得请求
//       if(sessionStorage.getItem('headerStore')){				//判断浏览器中是否有token
//         config.headers.Authorization = sessionStorage.getItem('headerStore')		//如果有token就带上token请求服务器数据
//       }else{
//         this.$router.push('/')							//如果浏览器中没有token就跳转到登陆界面
//       }
//     }
//     return config;
//   },
//   err => {
//     return Promise.reject(err);
//   }
// ),
// //所有求情的结果返回之前要经过axios响应拦截器
// axios.interceptors.response.use(
//   res => {
//     if(res.data.msg === 'S'){					//如果服务器对用户验证通过返回“S”成功标签就将用户需要的数据传递给请求的地方
//       return res
//     }
//   },
//   err => {
//     err				//如果服务器对用户验证不通过，清空浏览器中可能是失效了得token并跳转到登陆界面
//     sessionStorage.removeItem('headerStore');
//     this.$router.push('/');
//   }
// ),
//路由跳转之前进行判断npm
router.beforeEach((to, from, next) =>{		
  if(to.path === '/'){			//每次页面路由跳转的时候进行判断，如果是跳转到登陆界面就直接放行
    next()
  }else{
    let token = sessionStorage.getItem('headerStore')	
    if(token === "null" || token === ''){			//如果是跳转到其他页面，先判断token是否为空或者是否存在，不存在就跳转到登陆界面重新登陆
      next('/')
    }
    else{								//如果有token直接放行
      next()
    }
  }
});