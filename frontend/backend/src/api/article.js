import request from '@/utils/request'

export function getCategories(username) {
  return request({
    url: '/categories/' + username,
    method: 'get'
  })
}

export function saveArticle(data) {
  return request({
    url: '/articles',
    method: 'post',
    data
  })
}

export function uploadArticleImg(data) {
  debugger
  return request({
    url: '/articles/articleImg',
    method: 'post',
    data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

export function deleteArticleImg(data) {
  return request({
    url: '/articles/articleImg',
    method: 'delete',
    data
  })
}
