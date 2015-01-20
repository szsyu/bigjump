package com.ect.bigjump.service;

import com.ect.bigjump.domain.Document;
import com.ect.bigjump.domain.Page;

import java.util.List;

/**
 * Document服务层接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-04
 */
public interface DocumentService extends BaseService<Document, Long> {

    /**
     * 获取Active的Document
     *
     * @return
     */
    List<Document> getActiveDocuments();

    /**
     * 根据Document Code获取Document唯一对象
     *
     * @param documentCode
     * @return
     */
    Document getByCode(String documentCode);

    /**
     * 分页查询
     *
     * @param currentPage
     * @param documentCodeLike
     * @return
     */
    Page<Document> queryForPage(int currentPage, String documentCodeLike);

    /**
     * 检查documentCode对应的Document是否可用
     *
     * @param documentCode
     * @return
     */
    boolean checkAvaiableByCode(String documentCode);
}
