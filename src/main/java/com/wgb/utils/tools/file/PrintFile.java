package com.wgb.utils.tools.file;

import java.io.*;

/**
 * 快速输出格式化文件
 *
 * @author INNERPEACE
 * @date 2019/2/2 10:32
 **/
public class PrintFile extends PrintStream {
    public PrintFile(String fileName) throws IOException {
        super(new BufferedOutputStream(new FileOutputStream(fileName)));
    }

    public PrintFile(File file) throws IOException {
        this(file.getPath());
    }
}
