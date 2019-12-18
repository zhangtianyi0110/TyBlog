<template>
  <div class="page-contianer" v-loading="loading">

  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'Detail',
  components: {

  },
  mounted: function () {
    const article = this.$route.query.article;
    this.activeName = this.$route.query.an
    var _this = this;
    this.loading = true;
    getRequest("/article/" + aid).then(resp=> {
      if (resp.status == 200) {
        _this.article = resp.data;
      }
      _this.loading = false;
    }, resp=> {
      _this.loading = false;
      _this.$message({type: 'error', message: '页面加载失败!'});
    });
  },
  data() {
    return {
      loading: false
    }
  }
}
</script>

<style scoped>
</style>
