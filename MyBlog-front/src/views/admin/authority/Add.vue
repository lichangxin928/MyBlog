<template>
    <div v-title data-title="LCX博客后台 | 用户新增">
        <el-row :gutter="15">
            <el-form ref="formData" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-col :span="12">
                    <el-form-item label="账号" prop="name">
                        <el-input v-model="formData.name"  placeholder="用户组名称" clearable :style="{width: '100%'}"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="15">
                    <el-form-item>
                        <el-button @click="close">取消</el-button>
                        <el-button type="primary" :loading="loading" @click="submitForm">保存</el-button>
                    </el-form-item>
                </el-col>
            </el-form>
        </el-row>
    </div>
</template>

<script>
import {saveGroup} from "@/api/authority";

    export default {
        name: "Add",
        data(){
            return{
                formData: {
                    name: '',
                },
                loading: false,
                rules: {
                    name: [{required: true, message: '请输入用户组名称', trigger: 'blur'}],
                }
            }
        },
        methods:{
            submitForm(){
              saveGroup(this.formData).then(data=>{
                this.$message.success("添加成功！")
                this.$emit("after")
                this.$emit("hideDialog")
              },error=>{

              })
            },
            close(){
                this.$emit("hideDialog")
            }
        }
    }
</script>

<style scoped>

</style>