<template>
    <div v-title data-title="LCX博客后台 | 友情链接">
        <div>
            <el-form :inline="true" ref="queryForm" :model="queryForm" label-width="80px">
                <el-form-item>
                    <el-input v-model="queryForm.title" placeholder="按标题查询"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
                    <el-button icon="el-icon-plus" @click="add" type="success" >新增</el-button>
                </el-form-item>
            </el-form>
        </div>

        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="ID"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="title"
                    label="标题">
            </el-table-column>

            <el-table-column
                    prop="url"
                    label="链接地址">
            </el-table-column>
            <el-table-column
                    prop="target"
                    label="打开方式">
            </el-table-column>

            <el-table-column
                   align="center"
                   width="150"
                    label="操作">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" size="small" type="primary" icon="el-icon-edit"></el-button>
                    <el-button @click="del(scope.row)"  size="small" type="danger" icon="el-icon-delete"></el-button>
                </template>
            </el-table-column>

        </el-table>
        <el-pagination
                background
                layout="total,prev, pager, next,jumper"
                @current-change="handleCurrentChange"
                :current-page="pageNo"
                :page-size="7"
                :total="total">
        </el-pagination>

        <el-dialog :visible.sync="addVisble" v-if="addVisble" :close-on-click-modal="false">
            <Add @after="addseach" @hideDialog="hidden"></Add>
        </el-dialog>

        <el-dialog :visible.sync="editVisble" v-if="editVisble" :close-on-click-modal="false">
            <Edit @after="search" :data="formData" @hideDialog="hidden"></Edit>
        </el-dialog>
    </div>
</template>

<script>
    import {query,del} from "@/api/friendLink";
    import Add from './Add'
    import Edit from './Edit'

    export default {
        name: "Index",
        components:{
            Add,Edit
        },
        data() {
            return {
                queryForm:{
                    name:'',
                    userName: ''
                },
                tableData: [],
                total: 0,
                pageNo: 1,
                pages: '',
                formData: {},
                addVisble: false,
                editVisble: false
            }
        },
        mounted(){
            this.list({"page":this.pageNo})
        },
        methods:{
            handleCurrentChange(val){
                let  param =this.queryForm
                this.pageNo=val
                param.page=this.pageNo
                this.list(param)
            },
            search(){
                let  param =this.queryForm
                this.pageNo =1
                param.page=this.pageNo
                this.list(param)
            },
            list(param){
                query(param).then(data=>{
                    console.log(data)
                    this.tableData=data.list
                    this.total=data.total
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
            del(row){
                this.$confirm('确定要删除'+row.title+'吗？','提示').then(()=>{
                    del (row.id).then(data=>{
                        let param= this.queryForm
                        param.page =this.pageNo
                        this.list(param)
                    }).catch(error=>{

                        this.$message.error(error)
                    })
                }).catch(error=>{

                })
            },
            add(){
                this.$router.push('/friend_add')
            },
            edit(row){
                this.queryForm=row
                this.$router.push({
                    path:'/friend_edit',
                    query:{
                        id: row.id
                    }

                })
            },
            addseach(){
                let  param =this.queryForm
                param.page=this.pageNo
                this.list(param)
                query(param).then(data=>{
                    this.pages=data.pages
                    this.handleCurrentChange(this.pages)
                }).catch(error=>{
                    this.$message.error(error)
                })
            },
        }
    }
</script>

<style scoped>

</style>