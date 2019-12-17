import { getCategories, getArticles } from '@/api/article'

const state = {
  categories: [],
  articles: []
}

const mutations = {
  SET_CATEGORIES: (state, categories) => {
    state.categories = categories
  },
  SET_ARTICLES: (state, articles) => {
    state.articles = articles
  }
}

const actions = {
  // 获取所有分类
  getCategories({ commit, rootState }) {
    const userId = rootState.user.user.userId
    return new Promise((resolve, reject) => {
      getCategories(userId).then(response => {
        const { data } = response
        commit('SET_CATEGORIES', data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 获取所有文章集合
  getArticles({ commit, rootState }) {
    const userId = rootState.user.user.userId
    return new Promise((resolve, reject) => {
      getArticles(userId).then(response => {
        const { data } = response
        commit('SET_ARTICLES', data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
