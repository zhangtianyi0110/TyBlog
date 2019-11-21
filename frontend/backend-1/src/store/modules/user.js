/**
 * vuex的user模块
 */
import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'


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
  //获取用户信息
  getInfo({ commit, state }){
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          reject('验证失败, 请重新登陆.')
        }

        const { roles, perms, user } = data
        // 必须要一个角色
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_NAME', user.nickName)
        commit('SET_AVATAR', user.avatarUrl)
        commit('SET_ROLES', roles)
        commit('SET_PERMS', perms)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //退出登录
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}