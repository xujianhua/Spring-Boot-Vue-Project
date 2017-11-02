<template>
    <div>
        <div class="page-title">登录</div>
        <div class="page-part">
	        <mt-field label="用户名" placeholder="请输入用户名" v-model="formLogin.name"></mt-field>
	        <mt-field label="邮箱" placeholder="请输入邮箱" type="email" v-model="formLogin.password"></mt-field>
        </div>
        <mt-button size="normal" type="primary" @click="login">登录</mt-button>
    </div>
</template>

<script type="text/javascript">
  // 引入vuex /src/helper.js中的辅助函数，
  // 将actions中的方法直接转为组件中的方法
  import {mapActions} from 'vuex'
  export default {
    data(){
      return{
        formLogin:{
          name: '',
          password: ''
        }
      }
    },
    methods:{
      ...mapActions(['userLogin']),
      // 向登录接口发起请求
      login(){
      	let user = this.formLogin;
      	let postForm = new FormData();
        postForm.append('name',user.name);  
        postForm.append('password',user.password);  
          if (true) {
            // 通过验证之后才请求登录接口
            this.$http.post('http://localhost:9000/user/login',postForm)
                .then(res => {
                	console.log(res.data);
                    if (res.data.success) {
                      this.userLogin(res.data);
                      // 登录成功 跳转至首页
                      // this.$router.push({name:'Home'}) 
                      this.$router.push('/') 
                    }else{
                      return false;
                    }
                })
                .catch(err => {
                })
          } else {
            return false;
          }

      }
    }
  }
</script>