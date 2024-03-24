<template>
  <div class="app-container">
    <h2>销售记录列表</h2>
    <el-button type="primary" @click="open()">添加销售记录</el-button>
    <!-- 添加销售记录对话框 -->
    <el-dialog title="录入新销售记录" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="商品条码" :label-width="formLabelWidth">
          <el-select v-model="form.goodsId" placeholder="请选择" @change="changeGoodscode">
            <el-option v-for="item in codeList" :key="item.gid" :label="item.gcode" :value="item.gid">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="商品名称" :label-width="formLabelWidth">
          <el-input v-model="form.goodsName" autocomplete="off" readonly></el-input>
        </el-form-item>
        <el-form-item label="数量" :label-width="formLabelWidth">
          <el-input v-model="form.num" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" :label-width="formLabelWidth">
          <el-input v-model="form.price" readonly></el-input>
        </el-form-item>

        <el-form-item label="应收金额" :label-width="formLabelWidth">
          <!-- <el-input v-model="form.receivable"></el-input> -->
          {{ getReceivable }}
        </el-form-item>
        <el-form-item label="实收金额" :label-width="formLabelWidth">
          <el-input v-model="form.receipts"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAdd()">保 存</el-button>
      </div>
    </el-dialog>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="id" label="编号" width="120"> </el-table-column>
      <el-table-column prop="sellTime" label="售出时间" width="190">
      </el-table-column>
      <el-table-column prop="goodsName" label="商品名称" width="100">
      </el-table-column>
      <el-table-column prop="num" label="售出数量" width="80">
      </el-table-column>
      <el-table-column prop="price" label="商品单价" width="90">
      </el-table-column>
      <el-table-column prop="receivable" label="应收金额" width="120">
      </el-table-column>
      <el-table-column prop="receipts" label="实收金额" width="120">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import goodsApi from "@/api/goods/goods";
export default {
  data() {
    return {
      codeList: [], //商品条码列表
      form: {
        goodsId: "",
        goodsName: "",
        num: "",
        price: 0, //价格
        receivable: 0, //应收金额
        receipts: 0, //实收金额
      }, //销售记录的json对象
      tableData: [],
      formLabelWidth: "120px",
      dialogFormVisible: false,
      acitivityRule: {}, //活动规则对象
    };
  },
  created() {
    //获取商品条码列表
    this.getCodeList();
    //获取销售记录列表
    this.fetchData();
  },
  //计算属性
  computed: {
    //函数,返回需要的表达式的值
    getReceivable() {
      //计算应收金额
      this.form.receivable = this.form.price * this.form.num;
      if (this.acitivityRule == null) {
        //当前商品没有参与活动的，计算实收金额
        this.form.receipts = this.form.receivable.toFixed(1);
      } else {
        //当前商品参与了活动的，计算实收金额
        if (this.acitivityRule.baseMoney <= this.form.receivable) {
          this.form.receipts = (this.form.receivable - this.acitivityRule.reduceMoney).toFixed(1);
        } else {
          this.form.receipts = this.form.receivable.toFixed(1);
        }
      }
      //计算金额保留一位小数
      return this.form.receivable.toFixed(1);
    },
  },
  methods: {
    //获取销售记录列表
    fetchData() {
      goodsApi.getSellRecords().then(result => {
        this.tableData = result.data;
      })
    },
    getCodeList() {
      goodsApi.getCodeList().then((result) => {
        this.codeList = result.data;
      })
    },
    open() {
      this.dialogFormVisible = true;
    },
    changeGoodscode(id) {
      //  alert("商品编号:" + id);
      //调用js函数，请求后端接口获取商品信息（名称，价格，编号）
      goodsApi.getGoodsById(id).then(result=>{
        //console.log(result.data);
        this.form.goodsName = result.data.goodsName;
        this.form.price = result.data.price;
      });
      //调用js函数，请求后端接口获取商品是否参与活动
      goodsApi.getActivityRule(id).then(result=>{
          //把后端接口返回的活动规则赋给acitivityRule
          this.acitivityRule = result.data;
          //console.log(this.acitivityRule);
      })
    
    },
    //点击添加对话框保存按钮时,执行的函数
    handleAdd() {
      goodsApi.addSellRecord(this.form).then((result) => {
        if (result.code == 20000) {
          this.$message({
            message: "录入销售记录成功",
            type: 'success',
          });
          this.fetchData();
          this.dialogFormVisible = false;
        }
      })
    },
  },
};
</script>