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
 * 获取用户文章集合
 * @param {用户id} userId
 */
export function getArticles(userId) {
  return request({
    url: '/articles/' + userId,
    method: 'get'
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

