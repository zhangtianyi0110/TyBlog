import axios from "axios"
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

/**
 * 新建一个axios实例，并配置
 */
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,//url = baseURL + api
  timeout: 5000//请求超时时间
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