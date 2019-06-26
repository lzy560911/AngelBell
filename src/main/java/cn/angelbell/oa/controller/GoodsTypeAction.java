package cn.angelbell.oa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.angelbell.oa.entity.GoodsType;
import cn.angelbell.oa.service.GoodsTypeService;

/**
 * 
 * @ClassName: GoodsTypeAction
 * @Description: 商品类型控制层
 * @author liziye
 * @date 2019.06.26
 */
@RestController // 等价于@Controller+@ResponseBody
public class GoodsTypeAction {
    // 业务逻辑
    @Autowired
    private GoodsTypeService typeService;

    /**
     * 
     * @Title: getGoodsTypeList
     * @Description: 获取商品类型列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getGoodsTypeList")
    public List<GoodsType> getGoodsTypeList(int pageNum, int pageSize) throws Exception {
        // 调用业务逻辑,返回数据
        return typeService.getList(pageNum,pageSize);
    }

    @RequestMapping(value="/getByTypeName")
    public GoodsType getByTypeName(String typeName) throws Exception{
        typeName = "生活用品";
        return typeService.getByTypeName(typeName);
    }

}
