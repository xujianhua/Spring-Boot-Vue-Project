<template>
    <div>
       <mt-tabbar v-model="selected">
		  <mt-tab-item id="home">
		    <img slot="icon" src="">
		          首页
		  </mt-tab-item>
		  <mt-tab-item id="login">
		    <img slot="icon" src="">
		      暂无
		  </mt-tab-item>
		  <mt-tab-item id="">
		    <img slot="icon" src="">
		    暂无
		  </mt-tab-item>
		  <mt-tab-item id="">
		    <img slot="icon" src="">
		     暂无
		  </mt-tab-item>
		</mt-tabbar>
    </div>
</template>

<script>
    export default {
        data(){
            return {
                message: '',
                selected:'Home'
            }
        },
        watch: {
          selected: function (val, oldVal) {
          	console.log(val)
          	
            this.$router.push("/"+val);
          }
        }
    }
</script>

<style scoped>
    
</style>