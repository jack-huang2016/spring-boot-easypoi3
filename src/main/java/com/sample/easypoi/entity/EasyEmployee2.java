package com.sample.easypoi.entity;

import java.util.Date;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget(value="emp") // 相当于给该实体类取一个名字
public class EasyEmployee2 {
	/*
	 * name:列名 width:列宽
	 */
	@Excel(name = "邮箱", width = 12, orderNum = "2")
	
	private String email;
	
	@Excel(name = "用户名", mergeVertical = true, orderNum = "1")
	private String username;
	
	@Excel(name = "年龄", type = 10, isStatistics=true)
	private Integer age;
	
	/* format:时间格式 */
	@Excel(name = "生日", format = "yyyy-MM-dd",width = 12, orderNum = "3")
	private Date bornDate = new Date();
	
	/* replace:值得替换 导出是{a_id,b_id} 导入反过来 就相当于如果值是true则显示男，如果值为false则显示女 */
	@Excel(name = "性别", replace = { "男_true", "女_false" }, orderNum = "4")
	private Boolean sex;
	
	// 头像的url地址 savePath:你导入的图片存放的路径(对导入有效) type:导出类型 1 是文本 2 是图片,3 是函数,10 是数字
	// 默认是文本
	@Excel(name = "头像", type = 2, height = 40, width = 30, savePath = "imgs/", orderNum = "5")
	private String imgUrl;
	
	@Excel(name="身高", suffix="cm", orderNum = "6")
	private String height;
	
	@Excel(name="户籍", isColumnHidden = true, orderNum = "7")
	private String fromPlace;
	
	@Excel(name="合计测试1", orderNum = "8")
	private String sumTest1;
	
	@ExcelIgnore
	@Excel(name="合计测试2", needMerge = true, orderNum = "9")
	private String sumTest2;

	@ExcelEntity(name="部门信息") //标记为实体类,一遍是一个内部属性类,标记是否继续穿透,（简单理解就是我导出关联对象中对应的属性）
	private EasyDept dept;
}
