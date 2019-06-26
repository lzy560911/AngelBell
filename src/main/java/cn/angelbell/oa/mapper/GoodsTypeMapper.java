package cn.angelbell.oa.mapper;


import java.util.List;

import cn.angelbell.oa.entity.GoodsType;

/**
 * 
 * @ClassName: GoodsTypeDao
 * @Description: 商品类型数据访问接口
 * @author liziye
 * @date 2019.06.26
 */
public interface GoodsTypeMapper {

    /**
     * 
     * @Title: saveGoodsType
     * @Description: 添加一个商品类型
     * @param goodsType
     * @throws Exception
     */
    void saveGoodsType(GoodsType goodsType) throws Exception;

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
    void updateGoodsType(GoodsType goodsType) throws Exception;

    /**
     * 
     * @Title: getList
     * @Description: 获取所有商品类型列表
     * @return
     * @throws Exception
     */
    List<GoodsType> getList() throws Exception;

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