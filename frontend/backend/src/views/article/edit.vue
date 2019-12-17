<template>
  <div v-loading="loading" class="page-contianer">
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-form ref="articleForm" :model="article" :rules="rules" label-width="auto" size="small" class="demo-ruleForm" status-icon>
        <el-card class="box-card" shadow="always">
          <div slot="header" class="clearfix">
            <el-form-item label="文章标题" prop="title">
              <el-input v-model="article.title" />
            </el-form-item>
            <el-form-item label="文章分类" prop="categoryName">
              <el-select v-model="article.categoryName" placeholder="请选择文章分类" @change="getCategoryId">
                <!-- <el-select v-model="article.categoryName" placeholder="请选择文章分类"> -->
                <el-option
                  v-for="(category, index) in categories"
                  :key="index"
                  :value="category.categoryName"
                />
              </el-select>
            </el-form-item>
          </div>
          <div>
            <mavon-editor
              ref="md"
              v-model="article.mdContent"
              style="height: 100%;width: 100%;"
              @imgAdd="imgAdd"
              @imgDel="imgDel"
            />
            <el-divider />
            <el-form-item label="文章标签">
              <el-tag
                v-for="tag in tags"
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
            <div class="div-footer">
              <div>
                <el-form-item label="是否可以评论" prop="isComment">
                  <el-switch v-model="article.isComment" inactive-color="#ff4949" />
                </el-form-item>
                <el-form-item label="是否公开" prop="isRead">
                  <el-switch v-model="article.isRead" inactive-color="#ff4949" />
                </el-form-item>
                <el-form-item v-if="article.isRead" label="是否加密" prop="isReadPassword">
                  <el-switch v-model="isReadPassword" inactive-color="#ff4949" />
                  <div v-if="isReadPassword">
                    <el-input v-model="article.readPassword" placeholder="请输入密码">
                      <template slot="prepend">密码</template>
                    </el-input>
                  </div>
                </el-form-item>
              </div>
              <div style="margin-left: 200px">
                <el-form-item label="文章配图">
                  <!-- <el-input v-model="article.articleImg" placeholder="请输入文章配图URL" /> -->
                  <!-- <el-image class="article-img" :src="article.articleImg"  /> -->
                  <el-card :body-style="{ padding: '0px' }">
                    <img :src="article.articleImg" class="article-img">
                    <div style="padding: 14px;">
                      <div class="bottom clearfix">
                        <el-button type="text" class="button" @click="dialogVisible = true">更换配图</el-button>
                        <el-dialog title="更换配图" :visible.sync="dialogVisible">
                          <el-tabs type="border-card">
                            <el-tab-pane label="用户管理">默认图片</el-tab-pane>
                            <el-tab-pane label="配置管理">自定义图片</el-tab-pane>
                          </el-tabs>
                          <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogVisible = false">取 消</el-button>
                            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
                          </div>
                        </el-dialog>
                      </div>
                    </div>
                  </el-card>
                </el-form-item>
              </div>
            </div>
            <el-form-item>
              <el-button type="primary" @click.native.prevent="saveArticle(0)">保存到草稿箱</el-button>
              <el-button type="primary" @click.native.prevent="saveArticle(1)">发表文章</el-button>
            </el-form-item>
          </div>
        </el-card>
      </el-form>
    </el-scrollbar>
  </div>
</template>

<script type="text/ecmascript-6">
import { mapGetters } from 'vuex'
import { getArticleImg, saveArticle, uploadArticleImg } from '@/api/article'
import { isEmpty } from '@/utils'
import { Message } from 'element-ui'
export default {
  name: 'Edit',
  components: {

  },
  data() {
    return {
      loading: false,
      inputVisible: false,
      inputValue: '',
      dialogVisible: false,
      // categories: [],
      isReadPassword: false,
      tags: ['标签一', '标签二', '标签三'],
      categoryId: '',
      article: {
        title: '',
        categoryName: '',
        articleImg: '',
        mdContent: '',
        htmlContent: '',
        state: '',
        isComment: true,
        isRead: true,
        readPassword: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' }
        ],
        categoryName: [
          { required: true, message: '请选择文章类别', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'user',
      'categories'
    ])
  },
  mounted() {
    // this.getCategories()
    this.getArticleImg()
  },
  methods: {
    // 获取当前用户所有的文章分类
    // getCategories() {
    //   const userId = this.$store.state.user.user.userId
    //   getCategories(userId).then(response => {
    //     const { data } = response
    //     this.categories = data
    //   })
    // },
    // 获取文章随机默认配图
    getArticleImg() {
      getArticleImg().then(response => {
        const { data } = response
        this.article.articleImg = data
      })
    },
    // 更换文章配图
    updateArticleImg() {

    },
    // 获取当前文章的分类id
    getCategoryId() {
      const curCategory = this.categories.filter(category => category.categoryName === this.article.categoryName)[0]
      this.categoryId = curCategory.categoryId
    },
    // 保存文章
    saveArticle(state) {
      this.$refs.articleForm.validate(valid => {
        if (!valid) {
          this.$message({ type: 'error', message: '数据不能为空!' })
          return false
        }
      })
      if (!(isEmpty(this.article.mdContent))) {
        this.$message({ type: 'error', message: '数据不能为空!' })
        return
      }
      this.loading = true
      this.article.state = state
      this.article.htmlContent = this.$refs.md.d_render
      const data = {
        article: this.article,
        tags: this.tags,
        category: this.categoryId,
        author: this.$store.state.user.user.userId
      }
      saveArticle(data).then(response => {
        this.loading = false
        if (response.code === 200) {
          this.$message({ type: 'success', message: state === 0 ? '保存成功!' : '发布成功!' })
          if (state === 1) {
            this.$router.replace({ path: '/article/list' })
          }
        }
      }, response => {
        this.loading = false
        this.$message({ type: 'error', message: state === 0 ? '保存草稿失败!' : '博客发布失败!' })
      })
    },
    /**
     * 添加图片，详见https://github.com/hinesboy/mavonEditor/blob/master/doc/cn/upload-images.md
     */
    imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      uploadArticleImg(formdata).then(response => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        const { data } = response
        // this.$refs.md.$imgUpdateByUrl(pos, json.msg)
        this.$refs.md.$imglst2Url([[pos, data]])
      }).catch(error => {
        console.log('err' + error) // for debug
        Message({
          message: error.message,
          type: 'error',
          duration: 5 * 1000
        })
      })
    },
    imgDel(pos) {
      console.log(pos)
    },

    // 删除tag
    handleClose(tag) {
      this.tags.splice(this.tags.indexOf(tag), 1)
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
        this.tags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    }
  }
}
</script>

<style scoped  lang="scss">
  .box-card{
    .clearfix{
      display: flex;
    }
  }
  .article-img {
    width: 200px;
    height: 100px;
    margin: 0px;
  }
  .div-footer {
    display: flex;
  }
</style>
