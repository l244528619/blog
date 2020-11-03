// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import api from '@/api'
import store from '@/store';




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


//路由跳转之前进行判断
router.beforeEach((to, from, next) =>{		
  if(to.path === '/'){			//每次页面路由跳转的时候进行判断，如果是跳转到登陆界面就直接放行
    next()
  }else{
    let token = sessionStorage.getItem('headerStore')	
    console.log("666666666666666666",sessionStorage.getItem('headerStore')	)
    if(token === "null" || token === ''){			//如果是跳转到其他页面，先判断token是否为空或者是否存在，不存在就跳转到登陆界面重新登陆
      next('/')
    }
    else{								//如果有token直接放行
      next()
    }
  }
})