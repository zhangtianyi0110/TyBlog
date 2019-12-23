const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  user: state => state.user.user,
  roles: state => state.user.roles,
  perms: state => state.user.perms,
  permission_routes: state => state.permission.routes,
  categories: state => state.article.categories,
  articles: state => state.article.latestarticles,
  articleCounts: state => state.article.articleCounts,
  articleDeatil: state => state.article.articleDeatil
}
export default getters
