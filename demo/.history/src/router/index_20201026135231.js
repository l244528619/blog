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

export default new Router({
  routes: [
    {
      path: '/',
      component: () => import('@/view'),//路由懒加载
      redirect: "/index",
      children: [{
        path: 'index',
        name: 'index',
        component: () => import('@/view/index')
      },
      {
        path: 'home',
        name: 'home',
        component: () => import('@/view/home')
      }
      
      ]
    },
    // {
    //   path: '/login',
    //   name: 'login',
    //   component: () => import('@/view/login')
    // }
  ]
})

