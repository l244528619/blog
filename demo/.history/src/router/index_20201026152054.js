import Vue from 'vue'
import Router from 'vue-router'
import api from '@/api'
import { cp } from 'shelljs'

Vue.use(Router)
//路由重复使用
/**
   * 重写路由的push方法
   * 解决，相同路由跳转时，报错
   * 添加，相同路由跳转时，触发watch (针对el-menu，仅限string方式传参，形如"view?id=5")
   */
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  if (typeof (location) == "string") {
    var Separator = "&";
    if (location.indexOf('?') == -1) {
      Separator = '?';
    }
    location = location + Separator + "random=" + Math.random();
  }
  return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
  routes: [
    {
      path: '/',
      component: () => import('@/view'),//路由懒加载
      redirect: "/index",
      children: [{
        path: 'index',
        name: 'index',
        component: () => import('@/view/index')
      }      
      ]
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('@/view/home')
    }
    // {
    //   path: '/login',
    //   name: 'login',
    //   component: () => import('@/view/login')
    // }
  ]
})



// //路由跳转之前进行判断
// router.beforeEach((to, from, next) =>{		
//   if(to.path === '/'){			//每次页面路由跳转的时候进行判断，如果是跳转到登陆界面就直接放行
//     next()
//   }else{
//     let token = sessionStorage.getItem('headerStore')	
//     console.log("666666666666666666",sessionStorage.getItem('headerStore')	)
//     if(token === "null" || token === ''){			//如果是跳转到其他页面，先判断token是否为空或者是否存在，不存在就跳转到登陆界面重新登陆
//       next('/')
//     }
//     else{								//如果有token直接放行
//       next()
//     }
//   }
// })

export default router