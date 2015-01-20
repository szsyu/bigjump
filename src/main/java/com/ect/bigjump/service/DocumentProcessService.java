package com.ect.bigjump.service;

import com.ect.bigjump.domain.DocumentProcess;
import com.ect.bigjump.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Document Process服务接口
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-12-06
 */
public interface DocumentProcessService extends BaseService<DocumentProcess, Long> {

    /**
     * 分页查询
     *
     * @param currentPage
     * @param documentId
     * @return
     */
    Page<DocumentProcess> queryForPage(int currentPage, Long documentId);

    /**
     * 将指定的Process设置为Document的当前Process
     *
     * @param documentProcessId
     * @throws Exception
     */
    void setCurrentProcess(Long documentProcessId) throws Exception;

    /**
     * 获取Diagram ZIP存放的相对路径
     *
     * @return
     */
    String getRelativePathForDiagramZipStore();

    /**
     * 获取Diagram ZIP的存放路径
     *
     * @param request
     * @return
     * @throws Exception
     */
    String getRealPathForDiagramZipStore(HttpServletRequest request) throws Exception;

    /**
     * 根据上传的ZIP文件部署流程
     *
     * @param zipFilePath
     * @param documentId
     * @throws Exception
     */
    void deployProcessByZip(String zipFilePath, Long documentId) throws Exception;

    /**
     * 获取相应Document的当前Process
     *
     * @param documentId
     * @return
     */
    DocumentProcess getCurrentProcess(Long documentId);
}
