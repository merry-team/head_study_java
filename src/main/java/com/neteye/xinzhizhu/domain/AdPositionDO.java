package com.neteye.xinzhizhu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yinxj
 * @email net_eye@sohu.com
 * @date 2019-08-19 12:25:16
 */
public class AdPositionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//位置名称
	private String name;
	//宽度
	private Integer width;
	//高度
	private Integer height;
	//描述
	private String content;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：位置名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：位置名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：宽度
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * 获取：宽度
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * 设置：高度
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	/**
	 * 获取：高度
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * 设置：描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：描述
	 */
	public String getContent() {
		return content;
	}
}
