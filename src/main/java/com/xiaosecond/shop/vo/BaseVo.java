package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -178961460850948214L;

	@TableId(type= IdType.AUTO)
	@ApiModelProperty("主键id")
	private Integer id;           //主键id

	@TableField(value ="creater" , fill = FieldFill.INSERT)
	@ApiModelProperty("创建人")
	private Integer creater;      //创建人

	@TableField(value ="create_date" , fill = FieldFill.INSERT)
	@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private Date createDate;  //创建时间

	@TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty("修改人")
	private Integer updater;      //修改人

	@TableField(value ="update_date" , fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("修改时间")
	private Date updateDate;  //修改时间

	

}
