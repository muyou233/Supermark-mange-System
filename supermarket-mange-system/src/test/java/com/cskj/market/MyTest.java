package com.cskj.market;

import com.cskj.market.pojo.GoodsCode;
import com.cskj.market.service.GoodsCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: muyou
 * @Date: 2023/11/30 15:33
 */
@SpringBootTest
public class MyTest {
    @Resource
    private GoodsCodeService goodsCodeService;
    //测试插入商品条码
    @Test
    public void testAdd(){
        int[] ids={2,3,6,7,8,9,10};
        for (int id : ids) {
            GoodsCode goodsCode = new GoodsCode();
            goodsCode.setGid(id);
            String code = UUID.randomUUID().toString().replaceAll("-","");
            goodsCode.setGcode(code);
            goodsCodeService.addGoodsCode(goodsCode);
            System.out.println("条码添加成功");
        }

    }
}
