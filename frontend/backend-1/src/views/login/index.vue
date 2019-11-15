<template>
  <div class="login-container">
      <el-form :rules="rules" :model="loginForm" ref='loginForm' class="login-form" status-icon label-position="left"
           label-width="0px" v-loading="loading">
      <h3 class="login-title">TyBlog后台系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login-remember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" @click="toRegister" style="width: 48%">去注册</el-button>
        <el-button type="primary" @click.native.prevent="login" style="width: 48%">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default{
    data(){
      return {
        rules: {
          username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
          password: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {min: 6, message: '长度不能小于6个字符', trigger: 'blur' }
          ]
        },
        checked: true,
        loginForm: {
          username: 'tyblog',
          password: '123456'
        },
        loading: false
      }
    },
    methods: {
      login(){
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('user/login', this.loginForm).then(() => {
              this.$router.push({ path: this.redirect || '/' })
              this.loading = false
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        });
      },
      toRegister(){
        this.$router.push('register')
      }
    }
  }
</script>
<style>
  .login-container {
    background: url('~@/assets/login-background.jpg');
    background-size:100% 100%;
    background-repeat:no-repeat;
    height: 100%;
    display: flex;
  }

  .login-form {
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

  .login-title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  .login-remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #889aa4;
    cursor: pointer;
    user-select: none;
  }
</style>
