<template>
  <div>
    <div class="inner_div" @mouseover="mouseOver">
      <el-select
          v-model="selValue"
          placeholder="请选择向此用户组添加的菜单"
          filterable
          multiple
          collapse-tags
          ref="refElSelect"
      >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        >
        </el-option>
        <div class="selec_footer">
          <el-button size="small" @click="checkAll">全选</el-button>
          <el-button size="small" @click="cancel">取消</el-button>
        </div>
      </el-select>
    </div>
    <el-button size="big"  @click="back">返回</el-button>
    <el-button size="big" type="primary" @click="add">确认添加</el-button>
  </div>
</template>

<script>
import {getUnMenuList,addGroupRelMenu} from "@/api/authority"
export default {
  name: "",
  components: {},
  props: {},
  data() {
    return {
      selValue: [],
      options: [
      ],
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    getUnMenuList(this.$route.query.groupId).then(data=>{
      console.log(data.data);
      for (let i = 0; i < data.data.length; i++) {
        this.options.push({label:data.data[i].name,value:data.data[i].id})
      }
    },error=>{

    })
  },
  methods: {
    back(){
      this.$router.push("/group_menu")
    },
    // 鼠标滑入 自动展示下拉框
    mouseOver() {
      this.$refs.refElSelect.handleFocus();
    },
    add(){
      addGroupRelMenu({groupId:this.$route.query.groupId,id:this.selValue})
      // this.$router.go(0)
      this.mounted()
    },
    // 全选
    checkAll() {
      if (this.selValue.length < this.options.length) {
        // 全选
        let valueList = this.options.map((x) => x.value);
        this.selValue = valueList;
      } else if (this.selValue.length === this.options.length) {
        // 全不选
        this.selValue = [];
      }
    },

    // 关闭下拉框
    cancel() {
      this.$refs.refElSelect.handleClose();
    },

    // 请求数据并关闭下拉框
    determine() {
      this.$refs.refElSelect.handleClose();
    },
  },
};
</script>

<style scoped>
inner_div {
  width: 80%;
}
.el-select {
  width: 80%;
}
.el-button {
  margin-top: 20px;
}
</style>
