<template>
  <div v-loading="loading" class="page-contianer">
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-form ref="articleForm" :model="article" :rules="rules" label-width="100px" size="small" class="demo-ruleForm" status-icon>
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-form-item label="文章标题" prop="title">
              <el-input v-model="article.title" />
            </el-form-item>
            <el-form-item label="文章分类" prop="category">
              <el-select v-model="optionValue" placeholder="请选择文章分类">
                <el-option v-for="(category, index) in categories" :key="index" :label="category" :value="category" />
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
import { getCategories, saveArticle, uploadArticleImg } from '@/api/article'
import { isEmpty } from '@/utils'
import { MessageBox, Message } from 'element-ui'
export default {
  name: 'Edit',
  components: {

  },
  data() {
    return {
      loading: false,
      optionValue: '',
      inputVisible: false,
      inputValue: '',
      categories: [],
      article: {
        title: '',
        mdContent: '',
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
        this.categories = data.map(category => category.categoryName)
      })
    },
    // 保存文章
    saveArticle(articleSate) {
      if (!(isEmpty(this.article.mdContent))) {
        this.$message({ type: 'error', message: '数据不能为空!' })
        return
      }
      this.loading = true
        saveArticle()
        postRequest("/article/", {
          id: _this.article.id,
          title: _this.article.title,
          mdContent: _this.article.mdContent,
          htmlContent: _this.$refs.md.d_render,
          cid: _this.article.cid,
          state: state,
          dynamicTags: _this.article.dynamicTags
        }).then(resp=> {
          _this.loading = false;
          if (resp.status == 200 && resp.data.status == 'success') {
            _this.article.id = resp.data.msg;
            _this.$message({type: 'success', message: state == 0 ? '保存成功!' : '发布成功!'});
//            if (_this.from != undefined) {
            window.bus.$emit('blogTableReload')
//            }
            if (state == 1) {
              _this.$router.replace({path: '/articleList'});
            }
          }
        }, resp=> {
          _this.loading = false;
          _this.$message({type: 'error', message: state == 0 ? '保存草稿失败!' : '博客发布失败!'});
        })
    },
    /**
     * 添加图片，详见https://github.com/hinesboy/mavonEditor/blob/master/doc/cn/upload-images.md
     */
    imgAdd(pos, $file) {
      var _this = this
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      uploadArticleImg(formdata).then(response => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        const { data } = response
        // _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
        _this.$refs.md.$imglst2Url([[pos, data]])
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
