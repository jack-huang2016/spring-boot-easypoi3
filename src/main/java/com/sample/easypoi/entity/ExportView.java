package com.sample.easypoi.entity;

import java.util.List;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.Data;

@Data
public class ExportView {
	/**
	 * 导出的参数设置
	 */
    private ExportParams exportParams;
    
    /**
     * 导出的数据集合
     */
    private List<?> dataList;
    
    /**
     * 导出的数据类型
     */
    private Class<?> cls;
}
