package com.open.capacity.es.entity;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * 商品Document -example
 * <p>
 */
@Document(indexName = LogDocument.INDEX, type = LogDocument.TYPE)
public class LogDocument {

    public static final String INDEX = "logs";
    public static final String TYPE = "log";


    public LogDocument() {
    }

    

    public LogDocument(Long id, String username, String module, String params, String remark, Boolean flag,
			Date createTime) {
		super();
		this.id = id;
		this.username = username;
		this.module = module;
		this.params = params;
		this.remark = remark;
		this.flag = flag;
		this.createTime = createTime;
	}



	@Override
	public String toString() {
		return "LogDocument [id=" + id + ", username=" + username + ", module=" + module + ", params=" + params
				+ ", remark=" + remark + ", flag=" + flag + ", createTime=" + createTime + "]";
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	/**
     * 日志唯一标识
     */
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

   
    /**
     * 用户名称
     */
    @Field(type = FieldType.Text, index = false)
    private String username;

    /**
     * 归属模块
     */
    @Field(type = FieldType.Text, index = false)
    private String module;

    
    /**
     * 方法参数
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String params;


    @Field(type = FieldType.Text, index = false)
    private String remark; 
    
    
    @Field(type = FieldType.Boolean, index = false)
    private Boolean flag; 
    
    @Field(type = FieldType.Date, index = false)
    private Date createTime; 
    
    
   
}