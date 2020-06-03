import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/login'
import Home from '@/components/Home/home'
import User from '@/components/UserPage/user'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      component: Home,
      meta: {
        requireAuth: true
      },
      children: [{
        path: 'home',
        component: Home,
        meta: {
          name: '管理页面',
          menuShow: true,
          requireAuth: true
        }
      }]
    },
    {
      path: '/user',
      component: User,
      meta: {
        requireAuth: true
      },
      children: [{
        path: 'user',
        component: User,
        meta: {
          name: '用户页面',
          menuShow: true,
          requireAuth: true
        }
      }]
    },
  ]
})
