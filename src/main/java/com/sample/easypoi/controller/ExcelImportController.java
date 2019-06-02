package com.sample.easypoi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import com.sample.easypoi.entity.EasyDept;
import com.sample.easypoi.entity.EasyEmployee3;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * excel导入
 * @author hyj
 *
 */
public class ExcelImportController {

	/**
	 * excel导入
	 * @throws Exception
	 */
	@Test
	public void testImport() throws Exception{
	    //导入的基本信息
	    ImportParams params = new ImportParams();
	    
	    //是否需要保存上传的Excel,上传的文件都是以临时文件的命名方式保存,这段代码在有需要保存上传的excel才使用上这段代码，一般不需要使用
	    params.setNeedSave(true);
	    //保存上传的Excel目录，这段代码在有需要保存上传的excel才使用上这段代码，一般不需要使用
	    params.setSaveUrl("upload/excelUpload");
	    
	    //导入excel标题占一行
	    params.setHeadRows(1);
	    
	    //导入列名占一行
	    params.setTitleRows(1);
	    
	    //导入起始时间
	    long start = new Date().getTime();
	    
	    List<EasyEmployee3> list = ExcelImportUtil.importExcel(
	            new FileInputStream("d:" + File.separator + "employee3.xlsx"),//导入指定的文件
	            EasyEmployee3.class,//导入的数据转为指定的类型
	            params);//导入的基本信息的设置
	    
	    //导入结束时间
	    System.out.println(new Date().getTime() - start);
	    
	    for (EasyEmployee3 easyEmployee : list) {
	        System.out.println(easyEmployee);
	    }
	}
	
	/**
	 * 仅仅为了制造一个文件，供导入的时候使用，可忽略不看。
	 * @throws Exception
	 */
	@Test
    public void testExport5() throws Exception {
        List<EasyEmployee3> employees = new ArrayList<>();
        EasyEmployee3 e1 = new EasyEmployee3();
        e1.setUsername("李四");
        e1.setEmail("12@qq.com");
        e1.setAge(22);
        e1.setBornDate(new Date());
        e1.setSex(true);
        EasyDept dept = new EasyDept();
        dept.setName("开发部");
        dept.setAddress("东莞");
        e1.setDept(dept);
        e1.setImgUrl("imgs/ad_nopic.png");
     
        EasyEmployee3 e2 = new EasyEmployee3();
        e2.setUsername("李四");
        e2.setAge(33);
        e2.setBornDate(new Date());
        e2.setSex(false);
        e2.setEmail("22@qq.com");
        dept = new EasyDept();
        dept.setName("销售部");
        dept.setAddress("北京");
        e2.setDept(dept);
        e2.setImgUrl("imgs/ad_nopic.png");
        employees.add(e1);
        employees.add(e2);
     
        /*
                              第一个参数： easypoi的基本配置（导出excel的基本配置）
                              第二个参数： 根据具体类型导出
                             第三个参数： 导出的数据
         */
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工列表信息3","员工", ExcelType.XSSF),
                EasyEmployee3 .class, employees);
     
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:" + File.separator + "employee3.xlsx"));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
}
