package com.sample.easypoi.entity;

import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("major")
public class TeacherEntity implements Serializable {
	 private String id;

	 //注意needMerge=true属性的作用，会将TeacherEntity的name成员变量纵向合并内容相同的值
	 @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", needMerge = true)
	 private String name;
}
