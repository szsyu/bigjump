package com.ect.bigjump.activiti.service.Impl;

import com.ect.bigjump.activiti.service.ExecutionService;
import com.ect.bigjump.domain.LookupValue;
import com.ect.bigjump.service.LookupValueService;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Activiti Execution Service实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-16
 */
@Service("executionService")
public class ExecutionServiceImpl implements ExecutionService {

    @Resource(name = "lookupValueService")
    private LookupValueService lookupValueService;


    @Override
    public void modifyVariableByLookup(DelegateExecution execution, String modifiedVar, String modifiedVarType, String lookupTypeCode, String lookupCode) {
        if (execution != null && !StringUtils.isBlank(modifiedVar) && !StringUtils.isBlank(modifiedVarType) &&
                !StringUtils.isBlank(lookupTypeCode) && !StringUtils.isBlank(lookupCode)) {
            LookupValue lookupValue = lookupValueService.getByTypeCodeAndCode(lookupTypeCode, lookupCode);
            if(lookupValue != null && !StringUtils.isBlank(lookupValue.getValue())) {
                switch (modifiedVarType) {
                    case "STRING": {
                        execution.setVariable(modifiedVar,lookupValue.getValue() );
                        break;
                    }
                    case "LONG":{
                        try{
                            Long varValue = Long.parseLong(lookupValue.getValue());
                            execution.setVariable(modifiedVar,varValue);
                        }catch (Exception e){
                            //TODO
                        }
                        break;
                    }
                    case "DOUBLE":{
                        try{
                            Double varValue = Double.parseDouble(lookupValue.getValue());
                            execution.setVariable(modifiedVar,varValue);
                        }catch (Exception e){
                            //TODO
                        }
                        break;
                    }
                    default:{
                        //TODO
                        break;
                    }
                }
            }
            else{
                //TODO
            }
        }else{
            //TODO
        }
    }
}
