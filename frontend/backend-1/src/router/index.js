import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from "@/layout/index";

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register/index')
  },
  {
    path: '/404',
    component: () => import('@/views/404/index'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
      path: 'home',
      name: 'Home',
      component: () => import('@/views/home/index'),
      meta: { title: 'Home', icon: 'Home' }
      },
      // {
      //   path: '',
      //   name: '',
      //   component: () => import(''),
      //   meta: {title: '', icon: ''}
      // }
    ]
  }
  // {
  //   path: '/',
  //   name: 'home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'about',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

// const router = new VueRouter({
//   mode: 'history',
//   //base: process.env.BASE_URL,
//   base: process.env.VUE_APP_BASE_API,
//   routes
// })

const createRouter = () => new VueRouter({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  base: process.env.VUE_APP_BASE_API,
  routes
})

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}
const router = createRouter()

export default router
