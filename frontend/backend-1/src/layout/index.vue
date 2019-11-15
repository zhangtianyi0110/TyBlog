<template>
 <div class="layout-home">
   <el-container>
    <el-header>
      <Navbar/>
    </el-header>
    <el-container>
      <el-aside>
        <Siderbar/>
      </el-aside>
      <el-main>
        <AppMain/>
      </el-main>
    </el-container>
  </el-container>
 </div>
</template>

<script type="text/ecmascript-6">
import Navbar from './components/Navbar'
import Siderbar from './components/Siderbar/index'
import AppMain from './components/AppMain'

export default {
  name:"",
  data() {
    return {

    }
  },
  components: {
    Navbar,
    Siderbar,
    AppMain
  }
}
</script>

<style scoped>
  .home-container {
    height: 100%;
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
  }
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

</style>
