package com.sample.easypoi.entity;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class StudentEntity implements Serializable {
	
   private String id;
  
   @Excel(name = "学生姓名", height = 20, width = 30)
   private String name;
  
   @Excel(name = "学生性别", replace = { "男_1", "女_2" }, suffix = "生")
   private int  sex;

   @Excel(name = "出生日期", format = "yyyy-MM-dd", width = 20)
   private Date birthday;

}
