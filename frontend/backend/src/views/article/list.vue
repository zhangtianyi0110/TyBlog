<template>
  <div class="page-contianer">
    <el-card class="box-card">
      <el-tabs type="article-card" shadow="always">
        <el-tab-pane label="全部文章">全部文章</el-tab-pane>
        <el-tab-pane label="配置管理">配置管理</el-tab-pane>
        <el-tab-pane label="角色管理">角色管理</el-tab-pane>
        <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
      </el-tabs>
    </el-card>

  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'List',
  components: {

  },
  data() {
    return {

    }
  }
}
</script>

<style scoped  lang='scss'>
  .box-card {
    margin: 50px
  }
</style>
