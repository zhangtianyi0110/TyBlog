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
      <el-table-column v-if="showEdit || showDelete" type="selection" width="35" align="left">
        <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange">全选</el-checkbox>
      </el-table-column>
      <el-table-column label="标题" width="180">
        <el-checkbox-group @change="handleCheckedCitiesChange">
          <el-checkbox label="..">..</el-checkbox>
        </el-checkbox-group>
        <template slot-scope="scope">
          <el-link type="primary" @click="getArticleInfo(scope.row)">{{ scope.row.title }}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="作者" width="180">
        <template slot-scope="scope">
          <el-link type="primary" @click="getAuthorInfo()">{{ scope.row.author.nickname }}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="category.categoryName" label="所属分类" />
      <el-table-column prop="updateDate" label="最后更新时间" />
      <el-table-column v-if="showEdit || showDelete" label="操作">

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
    }
  },
  data() {
    return {
      loading: false,
      keywords: '',
      articleData: [],
      page: 1,
      size: 10,
      showEdit: false,
      showDelete: false,
      checkAll: false
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
      if (this.state === 0) {
        this.showEdit = true
        this.showDelete = true
        getCurPageArticles(requestData).then(response => {
          const { data } = response
          if (response.code === 200) {
            this.articleData = data
          }
        })
      } else if (this.state === 1) {
        getCurPageArticles(requestData).then(response => {
          const { data } = response
          if (response.code === 200) {
            this.articleData = data
          }
        })
      } else if (this.state === 2) {
        getCurPageArticles(requestData).then(response => {
          const { data } = response
          if (response.code === 200) {
            this.articleData = data
          }
        })
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
      this.loading = true
      console.log(this.keywords)
      if (!this.keywords) {
        this.loading = false
        this.$message.error('查询条件为空！')
      } else {
        await getArticlesByTitle(this.keywords).then(response => {
          const { data } = response
          if (data) {
            this.articleData.length = 0
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
    }
  }
}
</script>

<style scoped>
</style>
