<template>
 <div class="Header">
   <div>
    <slot name="left">左边</slot>
   </div>
   <div>
    <slot name="right">右边</slot>
   </div>
 </div>
</template>

<script type="text/ecmascript-6">
export default {
  name:"",
  data() {
    return {

    }
  },
  components: {

  }
}
</script>

<style scoped>
.Header {
  display: flex;
  flex-flow:  row nowrap;
  justify-content: space-between;
}

</style>
