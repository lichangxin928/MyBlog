<template>
    <div class="whitebg paihang">
        <h2 class="htitle">热点文章</h2>
        <section class="topnews imgscale"><router-link :to='"/detail/"+article.id' target="_blank">
          <span>{{article.title}}</span>
        </router-link></section>
        <ul>
            <li v-for="(item,index) in articles.slice(0,8)" :key="index">
                <i></i>
                <router-link :to='"/detail/"+item.id' target="_blank">{{item.title}}</router-link >
            </li>
        </ul>
    </div>
</template>

<script>
    import {getOlderByArticle} from "../../api/front";
    export default {
        name: "Order",
        data(){
            return{
                articles: [],
                article: ''
            }
        },
        mounted() {
            getOlderByArticle().then(data=>{
                this.articles=data.data
                this.article=this.articles[0]
            }).catch(error=>{

            })
        }
    }
</script>

<style scoped>

</style>