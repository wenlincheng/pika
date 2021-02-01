package com.wenlincheng.pika.common.core.util.excel;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * Created by zhouyuxin on 2019/2/27.
 */
public interface ExcelBuildRow<T> {
    
    public  void buildRow(List<T> list, Sheet sheet);
}
