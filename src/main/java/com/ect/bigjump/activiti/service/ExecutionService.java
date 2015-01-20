package com.ect.bigjump.activiti.service;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * Activiti Execution Service接口,用于Gateway执行
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
public interface ExecutionService {

    /**
     * 根据流程定义里设置,使用Lookup匹配进行流程变量的更改
     *
     * @param execution
     * @param modifiedVar
     * @param modifiedVarType
     * @param lookupTypeCode
     * @param lookupCode
     */
    void modifyVariableByLookup(DelegateExecution execution, String modifiedVar, String modifiedVarType, String lookupTypeCode, String lookupCode);
}
