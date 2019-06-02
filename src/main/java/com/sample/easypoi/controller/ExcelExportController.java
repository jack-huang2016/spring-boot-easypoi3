package com.sample.easypoi.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sample.easypoi.entity.CourseEntity;
import com.sample.easypoi.entity.EasyDept;
import com.sample.easypoi.entity.EasyEmployee;
import com.sample.easypoi.entity.EasyEmployee2;
import com.sample.easypoi.entity.ExportView;
import com.sample.easypoi.entity.StudentEntity;
import com.sample.easypoi.entity.TeacherEntity;
import com.sample.easypoi.style.ExcelExportStyler1Impl;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;

/**
 * excel导出
 * @author hyj
 *
 */
@RestController
@RequestMapping("/excel")
public class ExcelExportController {
	/**
	 * 案例一：
     * 通过web方式导出
     */
    @GetMapping("/easyExport")
    public void easyExport(HttpServletResponse response) throws IOException {
    	 List<EasyEmployee> employees = new ArrayList<>();
		    EasyEmployee e1 = new EasyEmployee();
		    e1.setUsername("李四");
		    e1.setEmail("12@qq.com");
		    e1.setAge(22);
		    e1.setBornDate(new Date());
		    e1.setSex(true);
		 
		    EasyEmployee e2 = new EasyEmployee();
		    e2.setUsername("王耍耍");
		    e2.setAge(33);
		    e2.setBornDate(new Date());
		    e2.setSex(false);
		    e2.setEmail("22@qq.com");;
		    employees.add(e1);
		    employees.add(e2);
		    
		    // 告诉浏览器用什么软件可以打开此文件
	        response.setHeader("content-Type", "application/vnd.ms-excel");
	        // 下载文件的默认名称
	        response.setHeader("Content-Disposition","attachment;filename*=utf-8''" + URLEncoder.encode("员工信息表.xlsx", "UTF-8"));
	        //编码
	        response.setCharacterEncoding("UTF-8");
		    
	        /*
	        	第一个参数： easypoi的基本配置（导出excel的基本配置）
	        	第二个参数： 根据具体类型导出
	        	第三个参数： 导出的数据
	         */
	        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工列表信息","员工", ExcelType.XSSF), EasyEmployee.class, employees);
	        workbook.write(response.getOutputStream());
    }
    
    /**
     * 案例二：
     * @ExcelEntity的使用-关联对象，一并导出员工&部门
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     * @throws Exception
     */
    @Test
    public void testExport() throws Exception {
        List<EasyEmployee2> employees = new ArrayList<>();
        EasyEmployee2 e1 = new EasyEmployee2();
        e1.setUsername("李四");
        e1.setEmail("12@qq.com");
        e1.setAge(22);
        e1.setHeight("180");
        e1.setBornDate(new Date());
        e1.setSex(true);
        e1.setFromPlace("广东");
        e1.setSumTest1("小计");
        e1.setSumTest2("合计");
        EasyDept dept = new EasyDept();
        dept.setName("开发部");
        dept.setAddress("东莞");
        e1.setDept(dept);
        e1.setImgUrl("imgs/ad_nopic.png");
     
        EasyEmployee2 e2 = new EasyEmployee2();
        e2.setUsername("李四");
        e2.setAge(33);
        e2.setHeight("170");
        e2.setBornDate(new Date());
        e2.setSex(false);
        e2.setFromPlace("山东");
        e2.setEmail("22@qq.com");
        e2.setSumTest1("合计");
        e2.setSumTest2("合计");
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
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("员工列表信息2","员工", ExcelType.XSSF),
                EasyEmployee2 .class, employees);
     
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:" + File.separator + "employee2.xlsx"));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
    
    /**
     * 案例三：单独导出部门
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     */
    @Test
    public void testExport1() throws Exception {
        List<EasyDept> depts = new ArrayList<>();
        
        EasyDept dept = new EasyDept();
        dept.setName("公关部");
        dept.setAddress("东莞");
        depts.add(dept);
        
        dept = new EasyDept();
        dept.setName("销售部");
        dept.setAddress("北京");
        depts.add(dept);
        
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("部门列表信息","部门", ExcelType.XSSF),
                EasyDept.class, depts);
     
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:" + File.separator + "dept.xlsx"));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
    
    
    /**
     * 案例四：集合的使用@ExcelCollection
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     */
    @Test
    public void testExport2() throws Exception {
        List<CourseEntity> courseEntityList = new ArrayList<>();
        
        CourseEntity courseEntity1 = new CourseEntity();
        courseEntity1.setName("生物课");
        
        TeacherEntity teacherEntity1 = new TeacherEntity();
        teacherEntity1.setName("陈老师");
        
        List<StudentEntity> studentEntityList = new ArrayList<>();
        StudentEntity studentEntity1 = new StudentEntity();
        
        studentEntity1.setName("小明");
        studentEntity1.setSex(1);
        studentEntity1.setBirthday(new Date());
        
        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setName("小红");
        studentEntity2.setSex(2);
        studentEntity2.setBirthday(new Date());
        
        studentEntityList.add(studentEntity1);
        studentEntityList.add(studentEntity2);  
        
        courseEntity1.setMathTeacher(teacherEntity1);
        courseEntity1.setStudents(studentEntityList);
        
        courseEntityList.add(courseEntity1);
        
        CourseEntity courseEntity2 = new CourseEntity();
        courseEntity2.setName("物理课");
        
        TeacherEntity teacherEntity2 = new TeacherEntity();
        teacherEntity2.setName("孙老师");
        
        List<StudentEntity> studentEntityList2 = new ArrayList<>();
        StudentEntity studentEntity3 = new StudentEntity();
        
        studentEntity3.setName("小汪");
        studentEntity3.setSex(1);
        studentEntity3.setBirthday(new Date());
        
        StudentEntity studentEntity4 = new StudentEntity();
        studentEntity4.setName("小李");
        studentEntity4.setSex(2);
        studentEntity4.setBirthday(new Date());
        
        studentEntityList2.add(studentEntity3);
        studentEntityList2.add(studentEntity4);  
        
        courseEntity2.setMathTeacher(teacherEntity2);
        courseEntity2.setStudents(studentEntityList2);
        
        courseEntityList.add(courseEntity2);
        
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("课程信息","课程", ExcelType.XSSF),
        		CourseEntity .class, courseEntityList);
     
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:" + File.separator + "course.xlsx"));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
    
    /**
     * 案例五：更自由的导出-Map导出
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     */
    @Test
    public void testExport3() throws Exception {
    	 List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
    	
    	 //构造对象等同于@Excel
    	 ExcelExportEntity courseNameEntity = new ExcelExportEntity("课程名称", "courseName");
    	 courseNameEntity.setNeedMerge(true); //纵向合并单元格，值相同的合并
    	 entity.add(courseNameEntity);
    	 
    	 //此处可以通过前端传过来的参数进行动态改变列名，有点类似a_id的作用
    	 boolean flag = true;
    	 String columnName = flag ? "正式老师" : "代课老师";
    	 ExcelExportEntity teacherEntity = new ExcelExportEntity(columnName, "name");
    	 teacherEntity.setNeedMerge(true);	  //纵向合并单元格，值相同的合并
    	 entity.add(teacherEntity);
    	
    	 //此处应用于集合，因此此处设置ExcelExportEntity构造函数的第一个参数的作用相当于@ExcelCollection的name属性值的作用一样，会给集合的列加个表头
    	 //ExcelExportEntity studentEntity = new ExcelExportEntity("学生", "students");   
    	 //如果设置为null，则不会给集合的列加表头
    	 ExcelExportEntity studentEntity = new ExcelExportEntity(null, "students");
    	 
    	//构造List等同于@ExcelCollection 
         List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
         temp.add(new ExcelExportEntity("姓名", "name"));
         temp.add(new ExcelExportEntity("性别", "sex"));
         studentEntity.setList(temp);
         entity.add(studentEntity);
         
         //一般是底层查询出来的数据集合，此处造假数据
         List<Map<String, Object>> list = makeData();
         
         // 把我们构造好的bean对象放到params就可以了
         Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("map测试的标题", "map测试", ExcelType.XSSF), entity, list);
         FileOutputStream fos = new FileOutputStream(new File("d:" + File.separator + "map.xlsx"));
         workbook.write(fos);
         fos.close();
    }

    
    /**
     * 案例六：多Sheet导出
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     */
    @Test
    public void testExport4() throws Exception {
    	List<Map<String, Object>> exportParamList = Lists.newArrayList();
    	List<ExportView> list = Lists.newArrayList();
    	
    	//第一张sheet
    	ExportView exportView1 = new ExportView();
    	exportView1.setExportParams(new ExportParams("部门的导出", "部门", ExcelType.XSSF));
    	exportView1.setCls(EasyDept.class);
    	
    	List<EasyDept> deptsList = new ArrayList<>();
        
        EasyDept dept = new EasyDept();
        dept.setName("公关部");
        dept.setAddress("东莞");
        deptsList.add(dept);
        
        dept = new EasyDept();
        dept.setName("销售部");
        dept.setAddress("北京");
        deptsList.add(dept);
        exportView1.setDataList(deptsList);
    	
    	list.add(exportView1);
    	
    	//第二张sheet
    	ExportView exportView2 = new ExportView();
    	exportView2.setExportParams(new ExportParams("学生的导出", "学生", ExcelType.XSSF));
    	exportView2.setCls(StudentEntity.class);
    	
    	List<StudentEntity> studentEntityList = new ArrayList<>();
        
    	StudentEntity student = new StudentEntity();
    	student.setName("小明");
    	student.setSex(1);
    	student.setBirthday(new Date());
    	studentEntityList.add(student);
        
    	student = new StudentEntity();
    	student.setName("小红");
    	student.setSex(2);
    	student.setBirthday(new Date());
    	studentEntityList.add(student);
    	
    	exportView2.setDataList(studentEntityList);
    	
    	list.add(exportView2);
    	
    	Map<String, Object> valueMap;
    	
		for (ExportView view : list) {
			valueMap = Maps.newHashMap();
			valueMap.put("title", view.getExportParams());  //title值相对应以下ExcelExportUtil.exportExcel方法中从map取出的key
			valueMap.put(NormalExcelConstants.DATA_LIST, view.getDataList()); //data值相对应以下ExcelExportUtil.exportExcel方法中从map取出的key
			valueMap.put(NormalExcelConstants.CLASS, view.getCls()); //entity值相对应以下ExcelExportUtil.exportExcel方法中从map取出的key
			exportParamList.add(valueMap);
		}
    	
		Workbook workbook = ExcelExportUtil.exportExcel(exportParamList, ExcelType.XSSF);
        FileOutputStream fos = new FileOutputStream(new File("d:" + File.separator + "moreSheet.xlsx"));
        workbook.write(fos);
        fos.close();
    }
    
    
    /**
     * 案例七：Excel的样式自定义
     * 通过FileOutputStream导出
     * 会自动冻结标题和列名
     */
    @Test
    public void testExport5() throws Exception {
        List<EasyDept> depts = new ArrayList<>();
        
        EasyDept dept = new EasyDept();
        dept.setName("公关部");
        dept.setAddress("东莞");
        depts.add(dept);
        
        dept = new EasyDept();
        dept.setName("销售部");
        dept.setAddress("北京");
        depts.add(dept);
        
        ExportParams exportParams = new ExportParams("部门列表信息","部门", ExcelType.XSSF);
        
        //使用时把样式工具类当做参数传递进去即可
        exportParams.setStyle(ExcelExportStyler1Impl.class); 
        
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, EasyDept.class, depts);
     
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:" + File.separator + "style.xlsx"));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }
    
    
    /**
     * 构造假数据
     * @return
     */
	private List<Map<String, Object>> makeData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
         
         Map<String,Object> map1 = new HashMap<String,Object>();
         map1.put("courseName", "生物课");
         map1.put("name", "陈老师");
         
         List<Map<String, Object>> sList = new ArrayList<Map<String, Object>>();
         Map<String, Object> stu1 = new HashMap<String,Object>();
         stu1.put("name", "小红");
         stu1.put("sex", "女");
         sList.add(stu1);
         
         Map<String, Object> stu2 = new HashMap<String,Object>();
         stu2.put("name", "小明");
         stu2.put("sex", "男");
         sList.add(stu2);
         map1.put("students", sList);
         list.add(map1);
         
         Map<String,Object> map2 = new HashMap<String,Object>();
         map2.put("courseName", "物理课");
         map2.put("name", "古老师");
         
         List<Map<String, Object>> sList2 = new ArrayList<Map<String, Object>>();
         Map<String, Object> stu3 = new HashMap<String,Object>();
         stu3.put("name", "小李");
         stu3.put("sex", "女");
         sList2.add(stu3);
         
         Map<String, Object> stu4 = new HashMap<String,Object>();
         stu4.put("name", "小孙");
         stu4.put("sex", "男");
         sList2.add(stu4);
         map2.put("students", sList2);
         list.add(map2);
		return list;
	}

}
