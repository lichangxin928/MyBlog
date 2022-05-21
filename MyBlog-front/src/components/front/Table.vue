<template>
    <div class="tab_box whitebg">

        <template>
        <div class="tab_buttons">
            <ul>
                <li v-for="(item,index) in channels" :key="index"  @click="curlist(item.id)" :class="{newscurrent: cur==item.id}">{{item.name}}</li>
            </ul>
        </div>
        <div class="newstab">
            <div class="newsitem" :v-show="cur==cur">
                <!-- 最新博客 -->
                <div class="newspic">
                    <ul>
                        <li v-if="xxxx.id!=0"><router-link  :to='"/detail/"+xxxx.id' target="_blank"><img src="https://img1.baidu.com/it/u=2345050893,4074298123&fm=253&fmt=auto&app=138&f=JPEG?w=1067&h=500"><span >{{xxxx.title}}</span></router-link></li>
                        <li v-if="yyyy.id!=0"><router-link  :to='"/detail/"+yyyy.id' target="_blank" ><img src="https://img2.baidu.com/it/u=1794648869,2132213213&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=937"><span>{{yyyy.title}}</span></router-link></li>
                        
                    </ul>
                </div>
                <ul class="newslist">
                    <span v-if="xxxx.id==0&&yyyy.id==0"><br/><br/>这个栏目什么都没有呀~~~</span>
                    <li v-for="(item,index) in articles" :key="index"><i></i><router-link :to='"/detail/"+item.id' target="_blank">{{item.title}}</router-link>
                        <p>{{item.summary}}</p>
                    </li>
                </ul>
            </div>
        </div>
        </template>
    </div>
</template>

<script>

    import {queryByPos,getChannelArticlePos} from '@/api/front'

    export default {
        name: "Table",
        data(){
            return{
                articles:[],
                channels : [],
                xxxx: {
                    id: '',
                    title:''
                },
                yyyy: {
                    id: '',
                    title:''
                },
                cur:''
            }
        },
        mounted() {
            queryByPos('b').then(data=>{
                this.channels=data.data

            }).catch(error=>{
                this.$message.error(error)
            })
            this.curlist(this.cur)

        },methods:{
            curlist(id){
                this.cur=id
                getChannelArticlePos(id).then(data=>{
                    this.articles=data.data
                    console.log(this.articles)
                    if (data.data[0]==null){
                        this.xxxx.id=0
                        this.xxxx.title=''
                    }else {
                        this.xxxx=data.data[0]
                    }

                    if (data.data[1]==null){
                        this.yyyy.id=0
                        this.yyyy.title=''
                    }else {
                        this.yyyy=data.data[1]
                    }

                }).catch(error=>{
                    this.$message.error(error)
                })

            }
        }
    }
</script>

<style scoped>

</style>