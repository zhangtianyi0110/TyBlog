<template>
  <div v-loading="loading" class="detail-contianer">
    <el-card class="detail-card">
      <div class="header" :style="url">
        <div class="title-info">
          <h2>{{ article.title }}</h2>
          <h5>发表于 {{ article.publishDate }} | 更新于 {{ article.updateDate }} | TyBlog</h5>
          <h5>阅读量 {{ article.readCount }} | 点赞数 {{ article.likes }} | 评论数 {{}} </h5>
        </div>
      </div>
      <div>
        <div style="text-align: left" v-html="article.htmlContent" />
      </div>
    </el-card>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'Detail',
  components: {

  },
  data() {
    return {
      loading: false,
      url: '',
      article: ''
    }
  },
  mounted() {
    this.loading = true
    this.init()
    this.loading = false
  },
  methods: {
    init() {
      this.article = this.$route.query.article.articleId === undefined ? this.$store.state.article.latestarticles[0] : this.$route.query.article
      console.log(this.article)
      this.$store.commit('article/SET_ARTICLE_DETAIL', this.article)
      this.url = `background-image: url(` + this.article.articleImg + `)`
    }
  }
}
</script>

<style scoped lang="scss">

.detail-contianer {

  .detail-card {

    .header {

      height: 200px;
      width: 100%;
      padding: 0px;
      background-size:100% 100%;
      background-repeat:no-repeat;

      .title-info {
        padding-left: 10%;
        padding-top: 8%;
        color: #ffffff;
      }
    }
  }
}
</style>
