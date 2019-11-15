import request from "@/utils/request";

/**
 *
 *  登录请求api
 * @export
 * @param {请求数据} data
 * @returns promise对象
 */
export function login(data) {
  debugger
  return request({
    url: 'user/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: 'user/logout',
    method: 'get'
  })
}

export function getInfo() {
  return request({
    url: 'user/info',
    method: 'get'
  })
}