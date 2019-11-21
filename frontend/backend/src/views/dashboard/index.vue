<template>
  <div class="dashboard-container">
    <github-svg class="github-svg" />
    <div>
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix" />
        <div class="body">
          <div class="body-item">
            <el-image class="body-image" :src="user.avatarUrl" :fit="fit" />
            <div class="body-msg">
              <span>{{ getTimeInfo(user.nickname) }}</span>
              <br>
              <span>上次登录时间：{{ user.lastLoginTime }}</span>
            </div>
          </div>
          <div class="body-item">
            <el-card class="box-card" shadow="never">
              <div slot="header" class="clearfix">
                <span><strong>今日访问人数</strong></span>
              </div>
              <el-tag effect="dark" class="box-tag">1111</el-tag>
            </el-card>
            <el-card class="box-card" shadow="never">
              <div slot="header" class="clearfix">
                <span><strong>文章总量</strong></span>
              </div>
              <el-tag effect="dark" class="box-tag">1111</el-tag>
            </el-card>
            <el-card class="box-card" shadow="never">
              <div slot="header" class="clearfix">
                <span><strong>评论总量</strong></span>
              </div>
              <el-tag effect="dark" class="box-tag">1111</el-tag>
            </el-card>
          </div>
        </div>
      </el-card>
    </div>
    <div class="dashboard-text">name: {{ user.name }}</div>
    <div class="dashboard-text">roles: <span v-for="role in roles" :key="role">{{ role }}</span></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getTimeInfo } from '@/utils/index'
import githubSvg from '@/components/Github'

export default {
  name: 'Dashboard',
  components: {
    githubSvg
  },
  data() {
    return {
      fit: 'cover'
    }
  },
  computed: {
    ...mapGetters([
      'user',
      'roles'
    ])
  },
  methods: {
    getTimeInfo(name) {
      return getTimeInfo(name)
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
    .github-svg {
      position: absolute;
      top: 0;
      border: 0;
      right: 0;
    }
    .box-card {
      .body {
        display: flex;
        justify-content: space-between;
        .body-item {
          display: flex;
          justify-content: space-between;
          .body-image {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin: 30px 30px;
          }
          .body-msg {
            padding: 30px;
            font-size: 100%;
            line-height: 46px;
          }
          .box-card {
            margin-left: 20px;
            width: 200px;
            align-content: center;
            .box-tag {
              display: flex;
              justify-content: center;
            }
          }
        }
      }
    }
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
