/**
 * vuex的user模块
 */
import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
// import { resetRouter } from '@/router'


const state = {
  token: getToken(),
  name: '',
  avatar: '',
  roles: [],
  perms: []
}

/**
 * 设置user相关的mutation
 */
const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_PERMS: (state, perms) => {
    state.perms = perms
  },
}

/**
 * 异步使用
 */
const actions = {
  //用户登录（异步）
  login({ commit }, userInfo){
    debugger
    const {username, password} = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password }).then(response => {
        const {data} = response
        commit('SET_TOKEN', data)//token存入vuex
        setToken(data)//token存入cookie
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}