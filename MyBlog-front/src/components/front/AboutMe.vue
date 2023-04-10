<template>
    <div class="card">
        <h2>我的名片</h2>
        <p>网名：LCX </p>
        <p>职业：程序员</p>
        <p>现居：中国.四川.成都</p>
        <p>Email：2431424266@qq.com</p>
        <ul class="linkmore">
            <li><a href="/" target="_blank" class="iconfont icon-zhuye" title="网站地址"></a></li>
            <li><a href="http://wpa.qq.com/msgrd?v=3&uin=2431424266&site=qq&menu=yes" target="_blank" class="iconfont icon---" title="QQ联系我"></a></li>
            <li><a class="iconfont icon-youxiang" href="https://mail.qq.com/"></a></li>
            <li id="weixin"><a href="#" target="_blank" class="iconfont icon-weixin" title="添加我的微信"></a><i><img src="@/assets/images/wx.png"></i></li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "AboutMe",
        data(){
          return {

          }
        },
      mounted() {

      }
    }
</script>

<style scoped>

</style>