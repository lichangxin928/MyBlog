<template>

    <div>
        <div class="links whitebg">
            <h2 class="htitle"><span class="sqlink"></span>常用链接</h2>
            <ul>
                <li v-for="(item,index) in friendLink" :key="index">
                    <a :href="item.url" target="_blank">{{item.title}}</a></li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {getListLink} from "@/api/friendLink";
    export default {
        name: "FriendLink",
        data(){
            return{
                friendLink: []
            }
        },
        created() {
            getListLink().then(data=>{
                this.friendLink=data.data
            })
        }
    }
</script>

<style scoped>

</style>