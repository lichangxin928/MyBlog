<template>
    <div v-title data-title="个人中心 | 用户组编辑">
        <el-row :gutter="15">
            <el-form ref="formData" :model="formData" :rules="rules" size="medium" label-width="100px">
                <el-col :span="12">
                    <el-form-item label="用户组名称" prop="name">
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
    import {saveOrUpdate} from "@/api/authority";

    export default {
        name: "Edit",
        props:['data'],
        data(){
            return{
                formData: {
                    id: '',
                    name: ''
                },
                loading: false,
                rules: {
                    name: [{required: true, message: '请输入用户组名称', trigger: 'blur'}]
                }
            }
        },
        created(){
           this.formData =JSON.parse(JSON.stringify(this.data))
        },
        methods:{
            close(){
                this.$emit("hideDialog")
            },
          submitForm(){
              console.log("submitForm")
              saveOrUpdate(this.formData).then(data=>{
                this.$message.success("修改成功!")
                this.$emit("after")
                this.$emit("hideDialog")
              },error=>{
                this.$message.error("服务异常!")
              })
            }
        }
    }
</script>

<style scoped>

</style>