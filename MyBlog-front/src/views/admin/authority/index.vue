<template>
  <div v-title data-title="个人中心 | 用户">

    <el-dropdown  @command="groupCommand" trigger="click">
  <span class="el-dropdown-link">{{checkedGroup.name}}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item v-for="(value,key) in userGroup" :key="key" :command=value>{{value.name}}</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <div>
      <el-form v-if="checkedGroup.name != '选择用户组'" :inline="true" label-width="80px" style="margin-top: 30px">
        <el-form-item>
          <el-input  placeholder="根据用户名查询" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-search" @click="search" type="primary" >查询</el-button>
          <el-button icon="el-icon-plus" @click="add" type="success" >新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
        :data="tableData"
        style="width: 100%;">
      <el-table-column
          prop="id"
          label="用户ID"
          >
      </el-table-column>
      <el-table-column
          prop="userName"
          label="用户名"
          >
      </el-table-column>
      <el-table-column
          prop="email"
          label="用户邮箱">
      </el-table-column>
      <el-table-column
          align="center"
          width="150"
          label="操作">
        <template slot-scope="scope">
          <el-button @click="del(scope.row)"  size="small" type="danger" icon="el-icon-delete"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="total,prev, pager, next,jumper"
        @current-change="handleCurrentChange"
        :current-page="1"
        :page-size="7"
        :total="14">
    </el-pagination>
  </div>
</template>

<script>

import {getUserGroup,getUserByGroupId,deleteUserGroup} from "../../../api/authority"
import {del, query} from "@/api/channel";
export default {
  name: "Index",
  components:{
  },
  data() {
    return {
      userGroup:[{name:"用户组"}],
      checkedGroup:{name:"选择用户组",id:"1"},
      count: 1,
      tableData:[]
    };
  },
  methods: {

    groupCommand(command) {
      console.log(command)
      this.checkedGroup=command
      getUserByGroupId(command.id).then(data=>{
        console.log(data)
        this.tableData = data.data
      },error=>{

      })

    },
    search(){

    },
    del(row){
      this.$confirm('确定要将 '+row.userName+' 从此用户组删除吗？','提示').then(()=>{
        deleteUserGroup(this.checkedGroup.id,row.id).then(data=>{
          this.$message("删除成功！")
        },error=>{
          this.$message.error("删除失败！")
        })
      }).catch(error=>{

      })
    },
    add(){
      this.$router.push({path:'/userGroup_add',query:{groupId:this.checkedGroup.id}})
    },
    edit(row){

    },
    addseach(){

    },
    handleCurrentChange(){

    }
  },
  mounted(){
    getUserGroup().then(data=>{
      console.log("userGroup:",data)
      this.userGroup = data.data
    },error=>{
      this.$message.error(error);
    })
  }
}
</script>

<style scoped>

</style>