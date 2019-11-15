import Cookies from 'js-cookie'

/**
 * token键名
 */
const TokenKey = 'Authorization'

/**
 * 从cookie中获取token
 */
export function getToken() {
  return Cookies.get(TokenKey)
}

/**
 * 将登录回传的token存入cookie
 * @param {*} token
 */
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

/**
 * 移除cookie中的token
 */
export function removeToken() {
  return Cookies.remove(TokenKey)
}
