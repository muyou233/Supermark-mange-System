<template>
  <div class="app-container">
    <h3>商品列表</h3>
    <!-- 商品查询输入 -->
    商品查询<el-input v-model="pager.keyWord" placeholder="请输入查询商品的关键词" style="width: 200px;"></el-input>
    <el-button type="success" @click="fetchData()">查询</el-button>
    <!-- 添加商品按钮 -->
    <el-button type="primary" @click="open()">添加商品</el-button>

    <!-- 添加对话框 -->
    <el-dialog title="添加新商品" :visible.sync="dialogFormVisible">
        <el-form :model="form">
            <el-form-item label="商品名称" :label-width="formLabelWidth">
            <el-input v-model="form.goodsName" autocomplete="off" placeholder="请输入商品名称"></el-input>
            </el-form-item>
            <el-form-item label="商品单价" :label-width="formLabelWidth">
            <el-input v-model="form.price" placeholder="请输入商品单价"></el-input>
            </el-form-item>
            <el-form-item label="商品类型" :label-width="formLabelWidth">
              <el-select v-model="form.typeId" placeholder="请选择商品类型">
                <el-option v-for="item in typeList" :key="item.id" :label="item.typeName" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="库存" :label-width="formLabelWidth">
            <el-input v-model="form.goodsStock" placeholder="请输入商品库存"></el-input>
            </el-form-item>
            <el-form-item label="商品描述" :label-width="formLabelWidth">
            <el-input type="textarea" :rows="3" v-model="form.goodsDesc" placeholder="请输入商品描述"></el-input>
            </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handlerAdd()">添 加</el-button>
        <el-button @click="dialogFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 编辑对话框 -->
    <el-dialog title="编辑商品" :visible.sync="dialogFormVisibleForEdit">
      <el-form :model="form">
        <el-form-item label="商品名称" :label-width="formLabelWidth">
          <el-input v-model="form.goodsName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品单价" :label-width="formLabelWidth">
          <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item label="商品类型" :label-width="formLabelWidth">
          <el-select v-model="form.typeId">
            <el-option v-for="item in typeList" :key="item.id" :label="item.typeName" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品描述" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="3" v-model="form.goodsDesc"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handlerUpdate()">保 存</el-button>
        <el-button @click="dialogFormVisibleForEdit = false,fetchData()">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 商品列表 -->
    <el-table ref="singleTable" :data="tableData" highlight-current-row current-change="handleCurrentChange"
      style="width: 100%">
      <el-table-column prop="id" label="商品ID" width="120"></el-table-column>
      <el-table-column prop="goodsName" label="商品名" width="120"></el-table-column>
      <el-table-column prop="price" label="商品单价"></el-table-column>
      <el-table-column prop="publishTime" label="更新日期"></el-table-column>
      <el-table-column prop="typeId" label="商品类型编号"></el-table-column>
      <el-table-column prop="goodsStock" label="库存"></el-table-column>
      <el-table-column prop="goodsDesc" label="商品描述"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handlerClick(scope.row)" type="primary" size="small">编辑</el-button>
          <el-button @click="handlerDelete(scope.row.id)" type="danger" size="small">删除</el-button>
       </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      
      @current-change="handleCurrentChange"
      :current-page.sync="pager.pageNum"
      :page-size="pager.pageSize"
      layout="total, prev, pager, next"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
// import { getList } from '@/api/table'
import goodsApi from '@/api/goods/goods';
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      //表格的数据来源
      tableData: [],
      dialogFormVisible: false,//false对话框不显示，true显示对话框
      form: {
        goodsName: "",
        price: 0,
        typeId: 1,
        goodsStock:0,
        goodsDesc: "",
      },
      formLabelWidth: '120px',
      //商品类型数组
      typeList: [],

      pager:{
        pageNum:1,  //页号
        pageSize:5,  //每页条数
        keyWord:''  //查询关键字
      },
      total:0,  //总记录数
      dialogFormVisibleForEdit:false,
    };
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      goodsApi.getList(this.pager).then((Response) => {
        //函数体里就能用result来表示后端接口响应回来的数据
        this.tableData = Response.data.records;  //获取数组
        this.total = Response.data.total;
        console.log(Response.data)
      });
      //调用获取商品类型列表的函数
      this.getTypeList();
    },
    //获取商品列表类型
    getTypeList() {
      goodsApi.getTypeList().then((result) => {
        this.typeList = result.data;
        // console.log(result.data);
      });
    },
    handlerAdd() {
      // console.log(this.form);
      //调用js函数
      this.dialogFormVisible = false;//关闭对话框
      goodsApi.addGoods(this.form).then((result) => {
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
    //清空数据
    clearData() {
      this.form.goodsName = "";
      this.form.price = "";
      this.form.goodsDesc = "";
      this.form.typeId = "";
      this.form.goodsStock = "";
    },
    //打开对话框
    open() {
      this.dialogFormVisible = true;
      this.clearData();
    },
    //页号改变时调用的函数
    handleCurrentChange(pageNum){
      this.pager.pageNum=pageNum;
      this.fetchData();
    },
    //点击“编辑”按钮时调用的函数
    handlerClick(obj){
      // console.log(obj)
      this.dialogFormVisibleForEdit=true; //显示编辑商品对话框
      //方法一：调用js函数，函数中请求后端根据id获取商品接口

      //方法二：直接把当前行的js对话obj献给form对象
      this.form=obj;
    },
    //编辑商品对话框的保存按钮
    handlerUpdate(){
      goodsApi.updateGoods(this.form).then((result)=>{
        this.dialogFormVisibleForEdit=false;//关闭对话框
        if (result.code == 20000) {
          //添加成功
          this.$message({
            message: "修改成功",
            type: 'success',
          });
          //刷新列表
          this.fetchData();//重新获取最新数据
        } else {
          //添加失败
          this.$message.error("修改失败");
        }
      })
    },
    //点击删除调用的函数
    handlerDelete(id){
      this.$confirm('确定删除此商品吗？', '删除提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //调用js中的函数，在函数中请求后端删除的接口
          goodsApi.deleteGoods(id).then((result)=>{
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
