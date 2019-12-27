<template>
  <div class="page-contianer">
    <el-input
      v-model="keywords"
      placeholder="通过标题搜索博客..."
      prefix-icon="el-icon-search"
      style="width: 400px"
      size="small"
    />
    <el-button type="primary" icon="el-icon-search" size="small" style="margin-left: 3px" @click="searchClick">搜索</el-button>
    <el-table :data="articleData" stripe style="width: 100%">
      <el-table-column label="标题" width="180">
        <template slot-scope="scope">
          <el-link type="primary" @click="getArticleInfo(scope.row)">{{ scope.row.title }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="author.nickname" label="作者" width="180" />
      <el-table-column prop="category.categoryName" label="分类" />
      <el-table-column prop="updateDate" label="最后更新时间" />
    </el-table>
  </div>
</template>

<script type="text/ecmascript-6">
import { getCurPageArticles, getArticlesByTitle } from '@/api/article'
export default {
  name: 'ArticleTab',
  props: {
    state: {
      type: Number,
      default: -1
    }
  },
  data() {
    return {
      loading: false,
      keywords: '',
      articleData: [],
      page: 1,
      size: 10
    }
  },
  mounted() {
    this.loading = true
    this.init()
    this.loading = false
  },
  methods: {
    init() {
      const requestData = {
        userId: this.$store.state.user.user.userId,
        state: this.state,
        page: this.page,
        size: this.size
      }
      console.log(requestData.state)
      if (this.state === 0) {
        console.log(this.state)
      } else if (this.state === 1) {
        console.log(this.state)
      } else if (this.state === 2) {
        console.log(this.state)
      } else {
        getCurPageArticles(requestData).then(response => {
          const { data } = response
          if (response.code === 200) {
            this.articleData = data
          }
        })
      }
    },
    async searchClick() {
      console.log(this.keywords)
      if (!this.keywords) {
        this.$message.error('查询条件为空！')
      } else {
        await getArticlesByTitle(this.keywords).then(response => {
          const { data } = response
          if (data) {
            this.articleData = data
          } else {
            this.$message.error('查无此文章！')
          }
        }).catch(response => {
          this.$message.error('查询出错！')
        })
      }
    },
    getArticleInfo(row) {
      this.$store.commit('article/SET_ARTICLE_DETAIL', row)
      this.$router.push({
        path: '/article/detail'
      })
    }
  }
}
</script>

<style scoped>
</style>
