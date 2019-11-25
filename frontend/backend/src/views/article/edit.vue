<template>
  <div class="page-contianer">
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-form ref="articleForm" :model="article" :rules="rules" label-width="100px" size="small" class="demo-ruleForm" status-icon>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-form-item label="文章标题" prop="title">
              <el-input v-model="article.title" />
            </el-form-item>
            <el-form-item label="文章分类" prop="category">
              <el-select v-model="optionValue" placeholder="请选择文章分类">
                <el-option v-for="(category, index) in article.categories" :key="index" :label="category" :value="category" />
              </el-select>
            </el-form-item>
          </div>
          <div>
            <mavon-editor v-model="value" />
            <el-divider />
            <el-form-item label="文章标签">
              <el-tag
                v-for="tag in article.tags"
                :key="tag"
                closable
                :disable-transitions="false"
                effect="plain"
                @close="handleClose(tag)"
              >
                {{ tag }}
              </el-tag>
              <el-input
                v-if="inputVisible"
                ref="saveTagInput"
                v-model="inputValue"
                size="small"
                class="input-new-tag"
                @keyup.enter.native="handleInputConfirm"
                @blur="handleInputConfirm"
              />
              <el-button v-else class="button-new-tag" size="small" @click="showInput">新增Tag</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click.native.prevent="submitForm('articleForm')">保存到草稿箱</el-button>
              <el-button type="primary" @click.native.prevent="submitForm('articleForm')">发表文章</el-button>
            </el-form-item>
          </div>
        </el-card>
      </el-form>
    </el-scrollbar>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapGetters } from 'vuex'
import { getCategories } from '@/api/article'
export default {
  name: 'Edit',
  components: {

  },
  data() {
    return {
      value: '',
      optionValue: '',
      inputVisible: false,
      inputValue: '',
      article: {
        title: '',
        categories: [],
        tags: ['标签一', '标签二', '标签三']
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择文章类别', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'user'
    ])
  },
  mounted() {
    this.getCategories()
  },
  methods: {
    // 获取当前用户所有的文章分类
    getCategories() {
      const username = this.$store.state.user.user.username
      getCategories(username).then(response => {
        const { data } = response
        this.article.categories = data.map(category => category.categoryName)
      })
    },

    // 删除tag
    handleClose(tag) {
      this.article.tags.splice(this.article.tags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.article.tags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }
  }
}
</script>

<style scoped>
</style>
