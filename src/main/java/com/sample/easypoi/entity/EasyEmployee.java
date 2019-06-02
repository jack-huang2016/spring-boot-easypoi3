package com.sample.easypoi.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class EasyEmployee {
	
	/*name:列名, width:列宽*/
    @Excel(name="邮箱",width = 12)
    private String email;
    
    @Excel(name="用户名")
    private String username;
    
    @Excel(name = "年龄")
    private Integer age;
    
    /*format:时间格式*/
    @Excel(name = "生日",format = "yyyy-MM-dd", width = 12)
    private Date bornDate = new Date();
    
   /* replace:值得替换  导出是{a_id,b_id}, 就相当于如果值是true则导出显示男，如果值为false则显示女, 导入则反过来*/
    @Excel(name="性别",replace = {"男_true","女_false"})
    private Boolean sex;
}
