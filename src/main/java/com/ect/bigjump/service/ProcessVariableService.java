package com.ect.bigjump.service;

import com.ect.bigjump.domain.ProcessVariable;

import java.util.List;

/**
 * 流程变量定义Service接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-07
 */
public interface ProcessVariableService extends BaseService<ProcessVariable, Long> {

    /**
     * 获取数据类型集合
     *
     * @return
     */
    List<String> getDataTypes();
}
