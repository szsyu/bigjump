package com.ect.bigjump.dao.hibernate;

import com.ect.bigjump.dao.DocDataInterfaceDao;
import com.ect.bigjump.domain.DocDataInterface;
import org.springframework.stereotype.Repository;

/**
 * Document Data导入接口持久层类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-10
 */
@Repository("docDataInterfaceDao")
public class DocDataInterfaceDaoImpl extends BaseDaoImpl<DocDataInterface, Long> implements DocDataInterfaceDao {
}
