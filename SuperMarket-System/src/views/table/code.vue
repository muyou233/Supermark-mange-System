<template>
    <div class="app-container">
        <el-table :data="tableData" style="width: 100%;">
            <el-table-column prop="gid" label="商品ID" width="100"></el-table-column>
            <el-table-column prop="gcode" label="商品条形码" width="300"></el-table-column>
        </el-table>
    </div>
</template>
<script>
import goodsApi from '@/api/goods/goods';
export default {
  data() {
    return {
       tableData: [],  //商品条码数组
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
     //获取列表
    fetchData() {
        goodsApi.getCodeList().then((result) => {
            this.tableData = result.data;
        })
    },
  }
}
</script>