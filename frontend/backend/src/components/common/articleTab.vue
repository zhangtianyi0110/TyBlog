<template>
  <div class="page-contianer">
    <el-input
      v-model="keywords"
      placeholder="通过标题搜索博客..."
      prefix-icon="el-icon-search"
      style="width: 400px"
      size="small"
    />
    <el-button type="primary" icon="el-icon-search" size="small" style="margin-left: 3px" @click="searchClick(state)">搜索</el-button>
    <el-table v-loading="loading" :data="articleData" stripe style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column v-if="showEdit || showDelete" type="selection" width="35" align="left" />
      <el-table-column label="标题" width="180">
        <template slot-scope="scope">
          <el-button type="text" @click="getArticleInfo(scope.row)">{{ scope.row.title }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="作者" width="180">
        <template slot-scope="scope">
          <el-button type="primary" plain @click="getAuthorInfo()">{{ scope.row.author.nickname }}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="category.categoryName" label="所属分类" />
      <el-table-column label="最后更新时间">
        <template slot-scope="scope">
          <i class="el-icon-time"><span style="margin-left: 10px">{{ scope.row.updateDate }}</span></i>
        </template>
      </el-table-column>
      <el-table-column v-if="showEdit || showDelete" label="操作">
        <template slot-scope="scope">
          <el-button v-if="showEdit" size="small" @click="editArticle(scope.$index, scope.row)">编辑</el-button>
          <el-button v-if="showDelete" size="small" type="danger" @click="deleteArticle(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
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
    },
    showEdit: {
      type: Boolean,
      default: false
    },
    showDelete: {
      type: Boolean,
      default: false
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
    async init() {
      const requestData = {
        userId: this.$store.state.user.user.userId,
        state: this.state,
        page: this.page,
        size: this.size
      }
      await getCurPageArticles(requestData).then(response => {
        const { data } = response
        if (response.code === 200) {
          this.articleData = data
        }
      })
    },
    async searchClick(state) {
      this.loading = true
      if (!this.keywords) {
        this.loading = false
        const requestData = {
          userId: this.$store.state.user.user.userId,
          state: this.state,
          page: this.page,
          size: this.size
        }
        await getCurPageArticles(requestData).then(response => {
          const { data } = response
          if (response.code === 200) {
            this.articleData = data
          }
        })
      } else {
        this.articleData = []
        await getArticlesByTitle(this.keywords).then(response => {
          const { data } = response
          if (data && data.state === state) {
            this.articleData.push(data)
          } else {
            this.$message.error('查无此文章！')
          }
        }).catch(response => {
          this.$message.error('查询出错！')
        })
      }
      this.loading = false
    },
    getArticleInfo(row) {
      this.$store.commit('article/SET_ARTICLE_DETAIL', row)
      this.$router.push({
        path: '/article/detail'
      })
    },
    getAuthorInfo() {
      this.$router.push({
        path: '/user/profile'
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    editArticle(index, row) {
      this.$router.push({
        path: '/article/edit',
        query: { article: row }
      })
    },
    deleteArticle() {

    }
  }
}
</script>

<style scoped>
</style>
