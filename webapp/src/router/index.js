import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Login from '@/components/Login'
import Register from '@/components/Register'

Vue.use(Router)

const router = new Router({
  routes: [{
      path: '/',
      name: 'index',
      // 路由元信息 meta
      meta: {
          requireAuth: true // 添加该字段，表示进入这个路由是需要登录的
      },
      component: Home
  },{
    path: '/home',
    name: 'home',
    // 路由元信息 meta
    meta: {
        requireAuth: true // 添加该字段，表示进入这个路由是需要登录的
    },
    component: Home
  },{
      path: '/login',
      name: 'login',
      component: Login
  }, {
      path: '/register',
      name: 'register',
      component: Register
  }]
})


//设置路由拦截
//在vue-router的全局钩子中设置拦截 
//每个路由皆会的钩子函数
//to 进入 from 离开 next 传递
router.beforeEach((to, from, next) => {
 let token = localStorage.getItem('token')
 if (to.meta.requireAuth) {
     if (token) {
         next()
     } else {
         next({
             path: '/login',
             query: {
                 redirect: to.fullPath
             }
         })
     }
 } else {
     next()
 }
})

export default router;