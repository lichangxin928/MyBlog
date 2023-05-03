<template>
    <div>
        <header id="header">
          <div class="navbox">
            <h2 id="mnavh"><span class="navicon"></span></h2>
            <div class="logo"><a href="/">个人博客</a></div>
            <nav>
              <ul id="starlist">
                <li><a href="/">首页</a></li>

                <li v-for="(item,index) in channels " :key="index" :class="{menu: item.children}">
                  <!-- 如果是单个的 -->
                  <router-link v-if="item.single" :to='"/channel_detail/"+item.id'>
                    {{item.name}}
                  </router-link>
                  <!-- 如果不是，则添加一个 ul 列表 -->
                  <router-link v-else :to='"/list/"+item.id'>{{item.name}}</router-link>
                  <ul class="sub" v-if="item.children">
                    <li v-for="(sub,sindex) in item.children" :key="sindex">
                      <router-link v-if="sub.single" :to='"/channel_detail/"+sub.id'>
                        {{sub.name}}
                      </router-link>
                      <router-link v-else :to='"/list/"+sub.id'>{{sub.name}}</router-link>
                    </li>
                  </ul>
                </li>
                <li>
                  <router-link v-if="!token" to="/user" target="_blank">用户登录</router-link>
                </li>
                <li>
                  <el-dropdown v-if="token" trigger="click" @command="handleCommand" style="">
                    <el-link class="user" :underline="false">
                      <table>
                        <tr>
                          <td valign="top">{{token.nickName}}</td>
                          <td valign="top">
                            <el-icon class="el-icon-caret-bottom"></el-icon>
                          </td>
                          <td></td>
                          <td valign="middle"><img :src="token.avatar"  alt=""></td>
                        </tr>
                      </table>
                    </el-link>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item command="index">首页</el-dropdown-item>
                      <el-dropdown-item command="info">个人信息</el-dropdown-item>
                      <el-dropdown-item command="blog">我的文章</el-dropdown-item>
                      <el-dropdown-item command="history">浏览记录</el-dropdown-item>
                      <el-dropdown-item command="password">修改密码</el-dropdown-item>
                      <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </li>
              </ul>
            </nav>
            <div class="searchico"></div>
          </div>
        </header>
        <!-- 查询 -->
        <div class="searchbox">
          <div class="search">
            <div>
              <el-form :inline="true" ref="queryForm" :model="queryForm" label-width="80px">
                <el-form-item>
                  <el-input v-model="queryForm.title" style="width: 330px" placeholder="按标题查询"></el-input>
                </el-form-item>
                <el-form-item>
                  <a href="#content" name="search">
                    <el-button icon="el-icon-search" @click="search" type="primary">查询</el-button>
                  </a>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div class="searchclose"></div>
        </div>
    </div>

</template>

<script>
    import { queryByPos, search } from '@/api/front'
    import $ from 'jquery'
    import token from "@/store/token";


    export default {
        name: "Header",

        data() {
            return {
                // header 中的提示栏
                channels: [],
                queryForm: {
                    name: '',
                    userName: ''
                },
                articles: [],
                pageNo: 1,
                token:token.getUser()
            }
        },
        mounted() {
            // 获取 Head 中的栏目信息
            queryByPos('a').then(data => {
                this.channels = data.data.slice(0,7)
            }).catch(error => {

            })
            $("#mnavh").click(function () {
                $("#starlist").toggle();
                $("#mnavh").toggleClass("open");
            });

            //search
            $(".searchico").click(function () {
                $(".search").toggleClass("open");
            });
            //searchclose
            $(".searchclose").click(function () {
                $(".search").removeClass("open");
            });
        },
        methods: {
          handleCommand(command){
            if (command== 'info'){
              this.$router.push("/info")
            }
            if (command== 'index'){
              this.$router.push("/")
            }
            if (command== 'logout'){
              this.$store.commit('logout')
              this.$router.go(0);
            }
            if (command== 'password'){
              this.$router.push("/password")
            }
          },
            search() {
                let param = this.queryForm
                this.pageNo = 1
                param.page = this.pageNo
                this.list(param)
            },
            list(param) {
                search(param).then(data => {
                    this.articles = data
                    this.$emit('child-even', this.articles)
                    this.$emit('child-evenTitle', this.queryForm.title)
                    this.total = data.total
                }).catch(error => {
                    this.$message.error(error)
                })
            },
        }

    }
</script>

<style scoped>
.logo{
  /*height: 60px;*/
  color: #ffffff;
  font-size: 20px;
  font-weight: bold;
}
.logo img{
  width: 40px;
  height: 40px;
}
.user{
  color: #ffffff !important;
}
.user img{
  width: 30px;
  height: 30px;
  border-radius: 15px;
}
</style>