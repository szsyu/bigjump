package com.ect.bigjump.service.impl;

import com.ect.bigjump.dao.BaseDao;
import com.ect.bigjump.dao.ProcessVariableDao;
import com.ect.bigjump.domain.ProcessVariable;
import com.ect.bigjump.service.ProcessVariableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 流程变量定义Service类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-07
 */
@Service("processVariableService")
public class ProcessVariableServiceImpl extends BaseServiceImpl<ProcessVariable,Long> implements ProcessVariableService {

    @Resource(name = "processVariableDao")
    private ProcessVariableDao processVariableDao;

    @Override
    public BaseDao<ProcessVariable, Long> getDao() {
        return processVariableDao;
    }

    @Override
    public List<String> getDataTypes() {
        return Arrays.asList(DATA_TYPES);
    }
}
