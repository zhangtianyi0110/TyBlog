import request from '@/utils/request'

export function getCategories(username) {
  return request({
    url: '/categories/' + username,
    method: 'get'
  })
}
