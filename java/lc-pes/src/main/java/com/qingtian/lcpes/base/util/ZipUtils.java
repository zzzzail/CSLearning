package com.qingtian.lcpes.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public class ZipUtils {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    private static final int readSize = 1048576;

    public static void zip(ZipOutputStream out, File f, String base, boolean first) throws Exception {
        if (first) {
            if (f.isDirectory()) {
                out.putNextEntry(new ZipEntry("/"));
                base = base + f.getName();
                first = false;
            }
            else {
                base = f.getName();
            }
        }

        int i;
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            base = base + "/";

            for (i = 0; i < fl.length; ++i) {
                zip(out, fl[i], base + fl[i].getName(), first);
            }
        }
        else {
            out.putNextEntry(new ZipEntry(base));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
            byte[] data = new byte[1048576];

            while ((i = in.read(data, 0, 1048576)) != -1) {
                out.write(data, 0, i);
            }

            in.close();
        }

    }

    public static void zipFile(String zipFileName, String inputFileName) throws Exception {
        long time1 = System.currentTimeMillis();
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        File inputFile = new File(inputFileName);
        zip(out, inputFile, "", true);
        out.close();
        long time2 = System.currentTimeMillis();
        logger.info("压缩导出文件耗时：" + (time2 - time1) + "毫秒");
    }
}
