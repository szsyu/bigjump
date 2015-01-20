package com.ect.bigjump.dao.hibernate;


import com.ect.bigjump.dao.DocAssigneeCandidatesDao;
import com.ect.bigjump.domain.DocAssigneeCandidates;
import org.springframework.stereotype.Repository;

/**
 * Document Assignee Or Candicates 持久层实现类
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-20
 */
@Repository("docAssigneeCandidatesDao")
public class DocAssigneeCandidatesDaoImpl extends BaseDaoImpl<DocAssigneeCandidates, Long> implements DocAssigneeCandidatesDao {
}
