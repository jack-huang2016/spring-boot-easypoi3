package com.sample.easypoi.entity;

import java.io.Serializable;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

@Data
@ExcelTarget("courseEntity")
public class CourseEntity implements Serializable {

    private String id;

    //注意needMerge=true属性的作用，会将CourseEntity的name成员变量纵向合并内容相同的值
    @Excel(name = "课程名称", orderNum = "1", width = 25, needMerge = true)
    private String name;

    @ExcelEntity(id = "absent")
    private TeacherEntity mathTeacher;

    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;
}
