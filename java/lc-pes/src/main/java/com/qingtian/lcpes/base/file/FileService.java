package com.qingtian.lcpes.base.file;

import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.file.vo.FileInfoVO;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public interface FileService {

    String uploadFile(@NotNull String groupName, @NotNull InputStream inputStream, String fileName, String fileExtName) throws QTBusinessException;

    String uploadFile(@NotNull String groupName, @NotNull byte[] contents, String fileName, String fileExtName) throws QTBusinessException;

    FileInfoVO downloadFile(String fileId) throws QTBusinessException;

    FileInfoVO downloadFileWithString(String fileId, boolean useBufferReader) throws QTBusinessException;

    boolean exist(String fileId) throws QTBusinessException;

    boolean delete(String fileId) throws QTBusinessException;

}
