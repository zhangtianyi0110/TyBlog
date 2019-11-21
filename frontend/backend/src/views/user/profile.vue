<template>
  <div class="profile-container">
    <github-svg class="github-svg" />
    <el-card class="box-card" shadow="hover">
      <div>关于我</div>
      <img :src="user.avatarUrl" class="image">
      <div style="padding: 14px;">
        <span>{{ user.nickname }}</span>
        <el-divider />
        <span>{{ user.profile }}</span>
      </div>
    </el-card>
    <el-tabs type="border-card" class="box-tabs">
      <el-tab-pane label="用户信息">
        <div class="info-item">
          <i class="fa fa-user fa-1x" aria-hidden="true">昵称：{{ user.nickname }}</i>
        </div>
        <div class="info-item">
          <i class="fa fa-envelope fa-1x" aria-hidden="true">邮箱：{{ user.email }}</i>
        </div>
        <div class="info-item">
          <i class="fa fa-envelope fa-1x" aria-hidden="true">博客：<a :href="blogUrl" target="_blank">{{ blogUrl }}</a></i>
        </div>
        <div class="info-item">
          <i class="fa fa-github fa-1x" aria-hidden="true">Github：<a :href="user.githubUrl" target="_blank">{{ user.githubUrl }}</a></i>
        </div>
      </el-tab-pane>
      <el-tab-pane label="我的技能">
        <div class="info-item">
          <span>Java</span>
          <el-progress :percentage="40" />
        </div>
        <div class="info-item">
          <span>Spring</span>
          <el-progress :percentage="20" />
        </div>
        <div class="info-item">
          <span>Vue</span>
          <el-progress :percentage="20" />
        </div>
      </el-tab-pane>
      <el-tab-pane label="个人信息管理">
        <span>个人信息管理</span>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapGetters } from 'vuex'
import githubSvg from '@/components/Github'
export default {
  name: 'Profile',
  components: {
    githubSvg
  },
  data() {
    return {
      blogUrl: 'www.zhangtianyi.vip'
    }
  },
  computed: {
    ...mapGetters(['user'])
  }
}
</script>

<style lang='scss' scoped>
.profile {
  &-container {
    margin: 30px;
    display: flex;

    .github-svg {
      z-index: 9999;
      position: absolute;
      top: 0;
      border: 0;
      right: 0;
    }

    .box-card {
      padding: 0px;
      width: 30%;

      .image {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        margin: 30px 30px;
      }
    }

    .box-tabs {
      margin-left: 50px;
      width: 100%;
      .info-item {
        margin-top: 20px;
      }
    }
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
