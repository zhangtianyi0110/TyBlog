import router from "@/router"
import store from '@/store'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // get token from cookie
import NProgress from 'nprogress' // 进度条
import 'nprogress/nprogress.css' // 进度条样式

const whiteList = ['/login'] // no redirect whitelist


// 判断是否需要登录权限 以及是否登录
router.beforeEach(async(to, from, next) => {
  debugger
  // 进度条开始
  NProgress.start()


  /* 判断是否有token，没有需要登录 */
  const hasToken = getToken()
  if(hasToken) {
    if(to.path ==='/login') {
      /* 如果有token且访问登录界面，直接跳转首页 */
      next({path: '/'})
      NProgress.done()
    } else {
      /**查看是否有角色 */
      const hasRoles = store.getters.roles && store.getters.roles.length > 0
      if(hasRoles) {
        next()
      } else {
        /**没有就获取角色信息 */
        try {

          const { roles } = await store.dispatch('user/getInfo')

          next({ ...to, replace: true })
        } catch (error) {
          /**清空token,回到login页面 */
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
      next()
      NProgress.done()
    }

  } else {
    /** 没有 token   */
    if(whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      /**没有权限重定向到login */
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
 })

 router.afterEach(() => {
  // 进度条完成
  NProgress.done()
})