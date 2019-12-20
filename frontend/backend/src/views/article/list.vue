<template>
  <div v-loading="loading" class="page-contianer">
    <el-card class="box-card">
      <el-tabs type="article-card" shadow="always">
        <el-tab-pane label="全部文章">
          <!-- <article-tab :activeName="activeName" :showDelete="false" :showEdit="false" :showRestore="false" :state="-1" /> -->
          <article-tab :state="-1" />
        </el-tab-pane>
        <el-tab-pane label="配置管理">配置管理</el-tab-pane>
        <el-tab-pane label="角色管理">角色管理</el-tab-pane>
        <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
      </el-tabs>
    </el-card>

  </div>
</template>

<script type="text/ecmascript-6">
import articleTab from '@/components/common/articleTab'
export default {
  name: 'List',
  components: {
    articleTab
  },
  data() {
    return {
      loading: false
    }
  }
}
</script>

<style scoped  lang='scss'>
  .box-card {
    margin: 50px
  }
</style>
