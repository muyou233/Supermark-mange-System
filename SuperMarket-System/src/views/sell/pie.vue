<template>
  <div class="app-container">
    <!-- 这个 div 就会被解析为 echarts图 -->
    <div class="pieChart" ref="pieChart"></div>
  </div>
</template>
<script>

import goodsApi from "@/api/goods/goods";
export default {
  data() {
    return {
      
    };
  },
  mounted() {
    //初始化 echarts
    this.initBarChart();
  },
  methods: {
    initBarChart() {
      // console.log("初始化");

      //console.log(this.$echarts);
      //讲div和echarts实例绑定,让echarts饼图能加载到div里面
      let myChart = this.$echarts.init(this.$refs.pieChart);

      // console.log(myChart);
      goodsApi.getPieData().then((result)=>{
        // console.log(result.data)
        //选项的配置
      let option = {
        title: {
          text: "商品销售金额统计图",
          subtext: "统计结果",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          //图例的布局方式(vertical表示垂直对齐)
          orient: "vertical",
          left: "left",
        },
        series: [
          //数据系列
          {
            name: "销售金额",
            type: "pie",
            radius: "60%",
            data: result.data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };

      //为myChart配置上option
      myChart.setOption(option);
      })
      
    },
  },
};
</script>

<style scoped>
.pieChart {
  width: 90%;
  height: 500px;
  /* div在水平方向居中 */
  margin: 10px auto;
}
</style>
