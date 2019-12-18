import { getCategories, getArticleCounts } from '@/api/article'

const state = {
  categories: [],
  // articles: [],
  articleCounts: 0
}

const mutations = {
  SET_CATEGORIES: (state, categories) => {
    state.categories = categories
  },
  // SET_ARTICLES: (state, articles) => {
  //   state.articles = articles
  // },
  SET_ARTICLECOUNTS: (state, articleCounts) => {
    state.articleCounts = articleCounts
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
  // // 获取所有文章集合
  // getArticles({ commit, rootState }) {
  //   const userId = rootState.user.user.userId
  //   return new Promise((resolve, reject) => {
  //     getArticles(userId).then(response => {
  //       const { data } = response
  //       commit('SET_ARTICLES', data)
  //       resolve()
  //     }).catch(error => {
  //       reject(error)
  //     })
  //   })
  // },
  // 获取该作者文章总数
  getArticleCounts({ commit, rootState }) {
    const userId = rootState.user.user.userId
    return new Promise((resolve, reject) => {
      getArticleCounts(userId).then(response => {
        const { data } = response
        commit('SET_ARTICLECOUNTS', data)
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
