<template>
  <div class="register-container">
      <el-form :rules="rules" class="register-form" status-icon  label-position="left"
           label-width="0px" v-loading="loading">
      <h3 class="register-title">TyBlog后台注册系统</h3>
      <el-form-item prop="account">
        <el-input type="text" v-model="registerFrom.username" auto-complete="off" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass">
        <el-input type="password" v-model="registerFrom.password" auto-complete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="checkPass2">
        <el-input type="password" v-model="registerFrom.checkPassword" auto-complete="off" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click="toLogin" style="width: 48%">去登录</el-button>
        <el-button type="primary" @click.native.prevent="register" style="width: 48%">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name:"register",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
    return {
      registerFrom: {
        username: '',
        password: '',
        checkPassword: ''
      },
      rules: {
        account: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        checkPass: [{required: true, message: '请输入密码', trigger: 'blur'}],
        checkPass2: [{ validator: validatePass, trigger: 'blur' }]
      },
      loading: false

    }
  },
  methods: {
    toLogin(){
      this.$router.push('login')
    },
    register(){

      console.log('注册成功');
    }
  },
  components: {

  }
}
</script>

<style scoped>
.register-container {
  background: url('~@/assets/register-background.jpg');
  background-size:100% 100%;
  background-repeat:no-repeat;
  height: 100%;
  display: flex;
}

.register-form {
  position: relative;
  border-radius: 15px;
  background-clip: padding-box;
  margin: auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  overflow: hidden;
  background: #fff;
  opacity:0.9;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.register-title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
