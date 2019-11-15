<template>
 <div class="page">
  <router-view :key="key" />
 </div>
</template>

<script type="text/ecmascript-6">
export default {
name:"AppMain",
 data() {
  return {

  }
 },
  components: {

  },
  computed: {
    key() {
      return this.$route.path
    }
  }
}
</script>

<style scoped>
</style>
