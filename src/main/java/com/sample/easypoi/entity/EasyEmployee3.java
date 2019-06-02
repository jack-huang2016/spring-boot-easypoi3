package com.sample.easypoi.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("emp")//相当于给该实体类取一个名字
public class EasyEmployee3 {
	@Excel(name = "邮箱", width = 25)
	private String email;
	
	@Excel(name = "用户名")
	private String username;
	
	@Excel(name = "年龄")
	private Integer age;
	
	/* format:时间格式 */
	@Excel(name = "生日", format = "yyyy-MM-dd")
	private Date bornDate = new Date();
	
	/* replace:值得替换 导出是{a_id,b_id} 导入反过来 就相当于如果值是true则显示男，如果值为false则显示女 */
	@Excel(name = "性别", replace = { "男_true", "女_false" })
	private Boolean sex;
	
	// 你导入的图片存放的路径(对导入有效) type:导出类型 1 是文本 2 是图片,3 是函数,10 是数字, 默认是文本
	@Excel(name = "头像", type = 2, height = 40, width = 30, savePath = "imgs")
	private String imgUrl;

	@ExcelEntity // 标记为实体类,一遍是一个内部属性类,标记是否继续穿透,（简单理解就是我导出关联对象中对应的属性）
	private EasyDept dept;
}
