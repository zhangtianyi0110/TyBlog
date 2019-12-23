import { getCategories, getCurPageArticles, getArticleCounts } from '@/api/article'

const state = {
  categories: [],
  latestarticles: [],
  articleCounts: 0,
  articleDetail: ''
}

const mutations = {
  SET_CATEGORIES: (state, categories) => {
    state.categories = categories
  },
  SET_LATEST_ARTICLES: (state, latestarticles) => {
    state.latestarticles = latestarticles
  },
  SET_ARTICLE_COUNTS: (state, articleCounts) => {
    state.articleCounts = articleCounts
  },
  SET_ARTICLE_DETAIL: (state, articleDetail) => {
    state.articleDetail = articleDetail
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
  // 获取最新的文章集合，默认数量取5
  getLatestArticles({ commit, rootState }) {
    const data = {
      userId: rootState.user.user.userId,
      state: 1,
      page: 1,
      size: 5,
      sort: 'updateDate'
    }
    return new Promise((resolve, reject) => {
      getCurPageArticles(data).then(response => {
        const { data } = response
        commit('SET_LATEST_ARTICLES', data)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 获取该作者文章总数
  getArticleCounts({ commit, rootState }) {
    const userId = rootState.user.user.userId
    return new Promise((resolve, reject) => {
      getArticleCounts(userId).then(response => {
        const { data } = response
        commit('SET_ARTICLE_COUNTS', data)
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
