<template>
  <div v-loading="loading" class="page-contianer">
    msg
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'Detail',
  components: {

  },
  data() {
    return {
      loading: false
    }
  },
  mounted() {
    const article = this.$route.query.article
    console.log(article)
    this.activeName = this.$route.query.an
    this.loading = true
  }
}
</script>

<style scoped>
</style>
