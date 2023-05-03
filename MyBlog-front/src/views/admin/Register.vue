<template>

  <div class="login-page" v-title data-title="用户注册">
    <div class="login-page-bg">
      <h2 style="opacity: 1;font-weight: 300">用户注册</h2>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item prop="userName">
          <el-input placeholder="请输入用户名" v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item  prop="email">
          <el-input placeholder="请输入用户邮箱" type="input" v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item  prop="password">
          <el-input placeholder="请输入登录密码" type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item  prop="password_again">
          <el-input placeholder="再次输入登录密码" @blur="checkPassword()" type="password" v-model="form.password_again"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 50%"  @click="register()">注册账号</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>

</template>

<script>
import {register} from "@/api/user";
export default {
  name: "Register",
  data(){
    let checkEmail = (rule, value, cb) => {
      //  验证邮箱的正则表达式
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return cb()
      }
      cb(new Error('请输入合法的邮箱'))
    };
    let validateMobilePhone = (rule, value, callback) => {
      if (value !== this.form.password) {
        callback(new Error('两次密码输入不一致~'));
      }
      callback()
    }
    return{

      form:{
        userName:'',
        email:'',
        password:'',
        password_again:''
      },
      loading :false,
      rules:{
        userName:[{required:true,message:'请输入用户名'}],
        email:[{validator: checkEmail, trigger: 'blur'}],
        password:[{required:true,message:'请输入登录密码'}],
        password_again:[{required:true,message:'请再次输入登录密码'},{validator: validateMobilePhone, trigger: 'blur'}],
      }
    }
  },
  methods:{
    register(){
      register(this.form).then(data=>{
        if(data.data == false){
          this.$message.error("邮箱已被注册！")
        }else {
          this.$message("注册成功！")
          setTimeout(()=>{
            this.$router.push("/login")
          },1000)
        }
      })
    },
  }
}
</script>

<style scoped>


.login-page{
  height: 100%;
  background-size: cover ;
}
.login-page-bg{
  position: absolute;
  top: 20%;
  left: 30%;
  right: 30%;
  bottom: 25%;

  height: 420px;
  /*background-color: #ffffff;*/
  background-color: #ffffff;
  border-radius:5px;
  padding-top: 20px;
  color: black;
  text-align: center;
  padding-right: 50px;
  box-shadow: #666 0px 0px 10px;
  -webkit-box-shadow: #666 0px 0px 10px;
  -moz-box-shadow: #666 0px 0px 10px;

}

.login-page-bg h2{
  margin-bottom: 30px;
}
</style>