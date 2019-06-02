package com.sample.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("dept")
public class EasyDept {
	/*意思是：如果我导出EasyEmployee的时候一并把关联对象EasyDept导出来，那么我EasyDept中最终导出的name列名就为部门
	如果我单独只导出EasyDept那列明就叫做名字*/
	    @Excel(name = "部门_emp,名字_dept")
	    private String name;
	    
	    @Excel(name = "部门地址_emp,地址_dept")
	    private String address;
}
