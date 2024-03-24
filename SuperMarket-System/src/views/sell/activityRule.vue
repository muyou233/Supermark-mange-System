<template>
  <div class="app-container">
    <h2>活动促销列表</h2>
    <el-button type="primary" @click="open()">添加活动商品</el-button>
    <el-table :data="tableData" style="width: 100%;">
      <el-table-column prop="goodsId" label="商品ID" width="100"></el-table-column>
      <el-table-column prop="goodsName" label="商品名称" width="100"></el-table-column>
      <el-table-column prop="baseMoney" label="满减金额" width="120"></el-table-column>
      <el-table-column prop="reduceMoney" label="优惠金额" width="120"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <!-- <el-button @click="handleClick(scope.row)" type="primary" size="small">编辑</el-button> -->
          <el-button @click="handlerDelete(scope.row.goodsId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加活动商品对话框 -->
    <el-dialog title="满减规则添加" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="商品ID" :label-width="formLabelWidth">
          <el-select v-model="form.goodsId">
            <el-option v-for="item in idList" :key="item.gid" :label="item.gid" :value="item.gid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="满减金额" :label-width="formLabelWidth">
          <el-input v-model="form.baseMoney" autocomplete="off" placeholder="输入满减金额"></el-input>
        </el-form-item>
        <el-form-item label="优惠金额" :label-width="formLabelWidth">
          <el-input v-model="form.reduceMoney" placeholder="输入优惠金额"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handlerAdd()">添 加</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import goodsApi from '@/api/goods/goods';
export default {
  data() {
    return {
      idList:[], //商品ID列表
      tableData: [],  //商品条码数组
      formLabelWidth: '120px',
      dialogFormVisible: false,//false对话框不显示，true显示对话框
      form: {
        goodsname:"",
        baseMoney: 0,
        reduceMoney: 0,
      },
      // typeList: [],
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    //获取列表
    fetchData() {
      goodsApi.getActivityList().then((result) => {
        this.tableData = result.data;
        // console.log(result.data);
        this.getIDList();
      })
    },
    //获取商品ID
    getIDList() {
      goodsApi.getCodeList().then((result) => {
        this.idList = result.data;
        // console.log(result.data)
      })
    },
    //打开添加窗口
    open() {
      this.dialogFormVisible = true;
      this.clearData();
    },
    handlerAdd() {
      //调用js函数
      this.dialogFormVisible = false;//关闭对话框
      goodsApi.addActivityRule(this.form).then((result) => {
        if (result.code == 20000) {
          //添加成功
          this.$message({
            message: result.message,
            type: 'success',
          });
          //刷新列表
          this.fetchData();
        } else {
          //添加失败
          this.$message.error(result.message);
        }
      });
    },
    created() {
      this.fetchData()
    },
    //清空数据
    clearData() {
      this.form.baseMoney = "";
      this.form.reduceMoney = "";
    },
    // 删除功能
    handlerDelete(id) {
      this.$confirm('确定删除此项吗？', '删除提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //调用js中的函数，在函数中请求后端删除的接口
        goodsApi.deleteActivityRule(id).then((result) => {
          if (result.code == 20000) {
            //删除成功
            this.$message({
              message: "删除成功",
              type: 'success',
            });
            //刷新列表
            this.fetchData();//重新获取最新数据
          } else {
            //删除失败
            this.$message.error("删除失败");
          }
        })
      })
    },
  }
}
</script>