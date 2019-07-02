package cn.angelbell.oa.serviceImpl;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.github.pagehelper.PageHelper;

import cn.angelbell.oa.entity.GoodsType;
import cn.angelbell.oa.mapper.GoodsTypeMapper;
import cn.angelbell.oa.service.GoodsTypeService;

/**
 * 
 * @ClassName: GoodsTypeServiceImpl
 * @Description: 商品类型业务逻辑处理
 * @author liziye
 * @date 2019.06.26
 */
@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class GoodsTypeServiceImpl implements GoodsTypeService {
    // 数据访问
    @Autowired
    private GoodsTypeMapper typeDao;

    /**
     * 
     * @Title: saveGoodsType
     * @Description: 添加一个商品类型
     * @param goodsType
     * @throws Exception
     */
    @Override
    public String saveGoodsType(GoodsType goodsType) throws Exception {
        // 检查商品类型名称是否重复
        if (!checkTypeName(goodsType.getTypeName())) {
            // 添加
            typeDao.saveGoodsType(goodsType);
            // 更新域对象
            updateServletContext();
            return "添加成功";
        } else {
            return "商品类型名称已存在,添加失败";
        }
    }

    /**
     * 
     * @Title: deleteGoodsType
     * @Description: 删除一个商品类型
     * @param typeId
     * @throws Exception
     */
    @Override
    public void deleteGoodsType(String typeId) throws Exception {

        // 删除
        typeDao.deleteGoodsType(typeId);
        // 更新域对象
        updateServletContext();

    }

    /**
     * 
     * @Title: updateGoodsType
     * @Description: 修改一个商品类型
     * @param goodsType
     * @throws Exception
     */
    @Override
    public String updateGoodsType(GoodsType goodsType) throws Exception {

        // 检查商品类型名称是否重复
        if (!checkTypeName(goodsType.getTypeName())) {
            // 修改
            typeDao.updateGoodsType(goodsType);
            // 更新域对象
            updateServletContext();
            return "更新成功";
        } else {
            return "商品类型名称已存在,更新失败";
        }

    }

    /**
     * 
     * @Title: getGoodsTypeList
     * @Description: 从域对象中获取所有商品类型列表
     * @return
     * @throws Exception
     */
    @Override
    public List<GoodsType> getGoodsTypeList() throws Exception {
    	List<GoodsType> list=typeDao.getList();
        return list;

    }

    /**
     * 
     * @Title: getList
     * @Description: 从数据库中获取所有商品类型列表
     * @param pageNum 当前页
     * @param pageSize 当前页面展示数目
     * @return
     * @throws Exception
     */
    public List<GoodsType> getList(int pageNum, int pageSize) throws Exception {
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(pageNum, pageSize);
        // 获取
        List<GoodsType> typeList = typeDao.getList();
        return typeList;
    }


    /**
     * 
     * @Title: checkTypeName
     * @Description: 检查商品类型名称是否重复
     * @param typeName
     */
    private boolean checkTypeName(String typeName) throws Exception {

        // 依据商品名称查询
        GoodsType goodsType = typeDao.getByTypeName(typeName);
        return goodsType == null ? false : true;

    }

    /**
     * 
     * @Title: updateServletContext
     * @Description: 更新域对象里的内容
     * @throws Exception
     */
    private void updateServletContext() throws Exception {
        // 通过Spring获取ServletContext域对象
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();

        List<GoodsType> typeList = getList(5, 5);
        servletContext.setAttribute("GOODS_TYPE_LIST", typeList);

    }

    /**
     * 
     * @Title: getByTypeName
     * @Description: 依据商品名称查询
     * @param typeName
     * @return
     * @throws Exception
     */
    public GoodsType getByTypeName(String typeName) throws Exception {
        return typeDao.getByTypeName(typeName);
    }

}