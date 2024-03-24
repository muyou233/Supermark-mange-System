import request from '@/utils/request'
export default {
    //获取商品列表
    getList(data) {
        return request({
            url: '/goods/list',
            method: 'post',
            data  //post请求用data属性传递参数
        })
    },
    //获取商品类型列表
    getTypeList() {
        return request({
            url: '/goodsType/list',
            method: 'get'
        })
    },
    //添加商品
    addGoods(data) {
        return request({
            url: '/goods/add',
            method: 'post',
            data
        })
    },
    //修改商品
    updateGoods(data){
        return request({
            url: '/goods/update',
            method: 'post',
            data
        })
    },
    //删除商品
    deleteGoods(id){
        return request({
            url: '/goods/deleteById',
            method: 'get',
            params: { id }
        })
    },
    //获取商品条码列表
    getCodeList(){
        return request({
            url: '/goods/goodsCodeList',
            method: 'get',
        })
    },
    //根据id获取商品信息
    getGoodsById(id){
        return request({
            url: '/goods/getGoods',
            method: 'get',
            params: { id }
        })
    },
    //根据id获取活动规则（判断商品是否参与优惠活动）
    getActivityRule(id){
        return request({
            url: '/sellManage/activityRule',
            method: 'get',
            params: { id }
        })
    },
    //添加销售记录
    addSellRecord(data){
        return request({
            url: '/sellManage/add',
            method: 'post',
            data
        })
    },
    //获取销售记录列表
    getSellRecords(){
        return request({
            url: '/sellManage/recordList',
            method: 'get',
        })
    },
    //获取活动规则列表
    getActivityList() {
        return request({
            url: '/activityrule/list',
            method: 'get'
        })
    },
    //添加活动规则
    addActivityRule(data){
        return request({
            url: '/activityrule/add',
            method: 'post',
            data
        })
    },
    //删除商品
    deleteActivityRule(id){
        return request({
            url: '/activityrule/deleteById',
            method: 'get',
            params: { id }
        })
    },
    //获取饼图数据
    getPieData(){
        return request({
            url: '/sellManage/pieData',
            method: 'get',
        })
    },
}