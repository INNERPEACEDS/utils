package com.wgb.utils.tools.file;

import java.io.*;

/**
 * 快速输出数据文件
 *
 * @author INNERPEACE
 * @date 2019/2/2 10:49
 **/
public class OutFile extends DataOutputStream {
    public OutFile(String fileName) throws IOException {
        super(new BufferedOutputStream(new FileOutputStream(fileName)));
    }

    public OutFile(File file) throws  IOException{
        this(file.getPath());
    }

}
