package cn.angelbell.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: GoodsType
 * @Description: 商品类型实体类
 * @author liziye
 * @date 2019.06.26
 */
public class GoodsType implements Serializable {
    private static final long serialVersionUID = -4039634130866820668L;

    private String typeId;// 类型id
    private String typeName;// 类型名称
    private Date createTime;// 创建时间
    private Date updateTime;// 更新时间
    
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     * 重写tostring
     */
    @Override
    public String toString() {
        return "GoodsType [typeId=" + typeId + ", typeName=" + typeName + ", createTime=" + createTime + ", updateTime="
                + updateTime + "]";
    }

    /**
     * 无参构造函数
     */
    public GoodsType() {
        super();
    }

    /**
     * 有参构造函数
     * 
     * @param typeId
     * @param typeName
     * @param createTime
     * @param updateTime
     */
    public GoodsType(String typeId, String typeName, Date createTime, Date updateTime) {
        super();
        this.typeId = typeId;
        this.typeName = typeName;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
