import request from '@/utils/request'

/**
 * 获取用户的所有分类
 * @param {用户id} userId
 */
export function getCategories(userId) {
  return request({
    url: '/categories/' + userId,
    method: 'get'
  })
}

/**
 * 获取用户文章总数
 *
 * @export
 * @param {用户id} userId
 */
export function getArticleCounts(userId) {
  return request({
    url: '/articles/count/' + userId,
    method: 'get'
  })
}

/**
 * 获取用户文章集合,分页数据
 * @param {包括用户id,当前页,当前页数量,文章状态} data
 */
export function getCurPageArticles(data) {
  return request({
    url: '/articles/' + data.userId + '/' + data.state,
    method: 'get',
    params: {
      page: data.page,
      size: data.size,
      sort: data.sort
    }
  })
}

/**
 * 获取文章随机配图
 */
export function getArticleImg() {
  return request({
    url: '/articles/img',
    method: 'get'
  })
}

/**
 * 保存文章
 * @param {文章对象} data
 */
export function saveArticle(data) {
  return request({
    url: '/articles',
    method: 'post',
    data
  })
}

/**
 * 上传文章内图片
 * @param {文章内图片} data
 */
export function uploadArticleImg(data) {
  return request({
    url: '/articles/articleImg',
    method: 'post',
    data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

