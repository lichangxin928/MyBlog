<template>
    <div class="whitebg lanmu">
        <img v-if="channel.channelImg==null ||channel.channelImg=='' " width="130px" height="100px" src="https://img0.baidu.com/it/u=1957696789,270527443&fm=253&fmt=auto&app=138&f=JPEG?w=130&h=100">
        <img v-else :src="channel.channelImg">
        <h1>{{channel.name}}</h1>
        <p>{{channel.summary}}</p>
    </div>
</template>

<script>

    export default {
        name: "ChannelInfo",
        props:['channel'],
        data(){
            return{}
        }
    }
</script>

<style scoped>

</style>