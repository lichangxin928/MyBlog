<!-- 首页 -->
<template>
    <div v-title data-title="LCX博客">
        <!-- Header 部分 -->
        <Header @child-even="parentEven" @child-evenTitle="parentEvenTitle"></Header>
        <article>
            <div class="lbox">
                <Table v-if="xx"></Table>
                <!-- 最新博客 -->
                <NewBlog v-if="xx" :search="newData" :childevenTitle="title"></NewBlog>
            </div>
            <div class="rbox">
                <!-- 关于我 -->
                <AboutMe></AboutMe>
                <!-- 排行榜 -->
                <Order></Order>
                <!-- 标签 -->
                <Tag></Tag>
                <!-- 常用链接 -->
                <FriendLink></FriendLink>
            </div>
        </article>
        <!-- footer 部分 -->
        <Footer></Footer>
    </div>
</template>

<script>

    import '@/assets/css/base.css'
    import '@/assets/css/m.css'
    import FriendLink from "../../components/front/FriendLink"
    import Header from "../../components/front/Header";
    import AboutMe from "../../components/front/AboutMe";
    import Table from "../../components/front/Table";
    import NewBlog from "../../components/front/NewBlog";
    import Order from "../../components/front/Order";
    import Footer from "../../components/front/Footer";
    import Tag from '../../components/front/Tag'

    export default {
        name: "Index",
        components: {
            Footer,
            Order, NewBlog, Table, AboutMe, Header, Tag, FriendLink
        },

        data() {
            return {
                articles: [],
                pages: '',
                total: 0,
                pageNo: '',
                xx: true,
                newData: '',
                title: ''
            }
        },
        beforeRouteUpdate(to, from, next) {
            this.xx = false;
            this.$nextTick(() => {
                this.xx = true
            })
            next()
        },
        methods: {
            parentEven(data) {
                console.log(data)
                console.log(data)
                console.log('aaaa')
                this.newData = data;
            },
            parentEvenTitle(data) {
                this.title = data
            }
        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>