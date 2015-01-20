package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.ProcessVariableDao;
import com.ect.bigjump.domain.ProcessVariable;
import org.springframework.stereotype.Repository;

/**
 * 流程变量定义持久化类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-07
 */
@Repository("processVariableDao")
public class ProcessVariableDaoImpl extends BaseDaoImpl<ProcessVariable, Long> implements ProcessVariableDao {
}
