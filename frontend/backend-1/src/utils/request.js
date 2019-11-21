import axios from "axios"
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

/**
 * 新建一个axios实例，并配置
 */
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,//url = baseURL + api
  timeout: 50000//请求超时时间
})

//axios请求拦截设置
service.interceptors.request.use(
  config => {
    debugger
    //请求前配置,如果有token就放在请求头中
    if(store.getters.token){
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    //请求错误后处理
    console.log(error)
    return Promise.reject(error)
  }

)

service.interceptors.response.use(
  //响应拦截器
  response => {
    debugger
    const res = response.data

    // if the custom code is not 200, it is judged as an error.
    if (res.code !== 200) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 4010: Illegal token; 4020: Other clients logged in; 4030: Token expired;
      if (res.code === 4010 || res.code === 4020 || res.code === 4030) {
        // to re-login
        MessageBox.confirm('您已退出登录，您可以点击取消停留在此页，也可以重新尝试登陆', '确认退出', {
          confirmButtonText: '重新登陆',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }

      if(res.code === 500){
        Message({
          message: '服务器错误500' || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      }

      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service