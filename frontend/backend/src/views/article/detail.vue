<template>
  <div v-loading="loading" class="detail-contianer">
    <el-card class="detail-card">
      <div class="header" :style="url">
        <h3>{{ article.title }}</h3>

      </div>
      <div>
        msg
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
    this.article = this.$route.query.article.articleId === undefined ? this.$store.state.article.latestarticles[0] : this.$route.query.article
    this.init()
    this.loading = false
  },
  methods: {
    init() {
      this.url = `background-image: url(` + this.article.articleImg + `)`
      this.activeName = this.$route.query.an
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
    }
  }
}
</style>
