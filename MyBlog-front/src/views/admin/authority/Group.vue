<template>
  <div v-title data-title="LCX博客后台 | 用户管理">
    <div>
      <el-form :inline="true" ref="queryForm" :model="queryForm" label-width="80px">
        <el-form-item>
          <el-input v-model="queryForm.name" placeholder="名称查询"></el-input>
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
          label="Id"
          >
      </el-table-column>
      <el-table-column
          prop="name"
          label="用户组名称"
         >
      </el-table-column>

      <el-table-column
          align="center"
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
      <Add @after="list" @hideDialog="hidden"></Add>
    </el-dialog>

    <el-dialog :visible.sync="editVisble" v-if="editVisble" :close-on-click-modal="false">
      <Edit @after="list" :data="formData" @hideDialog="hidden"></Edit>
    </el-dialog>
  </div>
</template>

<script>
import {groupList, deleteGroup} from "@/api/authority";
import Add from './Add'
import Edit from './Edit'

export default {
  name: "Index",
  components:{
    Add,Edit
  },
  mounted() {
    groupList().then(data=>{
      this.tableData = data.data
    },error=>{
      this.$message.error("服务异常！")
    })
  },
  data() {
    return {
      queryForm:{
        id:'',
        name:''
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
  methods:{
    edit(row){
      this.editVisble = true
      this.formData=row
    },
    add(){
      this.addVisble=true
    },
    list(){
      groupList().then(data=>{
        this.tableData = data.data
      },error=>{
        this.$message.error("服务异常！")
      })
    },
    hidden(){
      this.addVisble=false
      this.editVisble=false
    },
    search(){

    },
    handleCurrentChange(){

    },
    del(row){
      deleteGroup(row.id).then(data=>{
        this.$message.success("删除成功!")
        this.list();
      },error=>{

      })
    }

  }
}
</script>

<style scoped>

</style>