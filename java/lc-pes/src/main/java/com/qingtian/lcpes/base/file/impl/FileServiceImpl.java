package com.qingtian.lcpes.base.file.impl;

import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.file.FileService;
import com.qingtian.lcpes.base.file.vo.FileInfoVO;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(@NotNull String groupName, @NotNull InputStream inputStream, String fileName, String fileExtName) throws QTBusinessException {
        return null;
    }

    @Override
    public String uploadFile(String var1, @NotNull byte[] var2, String var3, String var4) throws QTBusinessException {
        return null;
    }

    @Override
    public FileInfoVO downloadFile(String var1) throws QTBusinessException {
        return null;
    }

    @Override
    public FileInfoVO downloadFileWithString(String var1, boolean var2) throws QTBusinessException {
        return null;
    }

    @Override
    public boolean exist(String var1) throws QTBusinessException {
        return false;
    }

    @Override
    public boolean delete(String var1) throws QTBusinessException {
        return false;
    }
}
