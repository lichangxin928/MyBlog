<template>
    <div>
        <Header></Header>
        <article>
            <div class="lbox">
                <TagBlog></TagBlog>
            </div>
            <div class="rbox">
                <Order></Order>
                <Like></Like>
                <Tag></Tag>
            </div>
        </article>
        <Footer></Footer>
    </div>
</template>

<script>
    import  '@/assets/css/base.css'
    import  '@/assets/css/m.css'
    import Header from "../../components/front/Header";
    import TagBlog from "../../components/front/TagBlog";
    import Order from "../../components/front/Order";
    import Footer from "../../components/front/Footer";
    import Tag from "../../components/front/Tag";
    import Like from "../../components/front/Like";

    export default {
        name: "List",
        components: {
            Tag, Footer, Order,  TagBlog , Header,Like
        },
        data(){
            return{
            }
        },

        beforeRouteUpdate(to,from ,next){
            console.log(to.params)
            next()
        }
    }
</script>

<style scoped>

</style>