package cn.angelbell.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.angelbell.oa.entity.GoodsType;

/**
 * 
 * @ClassName: GoodsTypeService
 * @Description: 商品类型业务逻辑接口
 * @author liziye
 * @date 2019.06.26
 */
@Service
public interface GoodsTypeService {

    /**
     * 
     * @Title: saveGoodsType
     * @Description: 添加一个商品类型
     * @param goodsType
     * @throws Exception
     */
    String saveGoodsType(GoodsType goodsType) throws Exception;

    /**
     * 
     * @Title: deleteGoodsType
     * @Description: 删除一个商品类型
     * @param typeId
     * @throws Exception
     */
    void deleteGoodsType(String typeId) throws Exception;

    /**
     * 
     * @Title: updateGoodsType
     * @Description: 修改一个商品类型
     * @param goodsType
     * @throws Exception
     */
    String updateGoodsType(GoodsType goodsType) throws Exception;

    /**
     * 
     * @Title: getList
     * @Description: 从域对象中获取所有商品类型列表
     * @return
     * @throws Exception
     */
    List<GoodsType> getGoodsTypeList() throws Exception;

    /**
     * 
     * @Title: getList
     * @Description: 从数据库中获取所有商品类型列表
     * @return
     * @throws Exception
     */
    List<GoodsType> getList(int pageNum, int pageSize) throws Exception;

    /**
     * 
     * @Title: getByTypeName
     * @Description: 依据商品名称查询
     * @param typeName
     * @return
     * @throws Exception
     */
    GoodsType getByTypeName(String typeName) throws Exception;
}