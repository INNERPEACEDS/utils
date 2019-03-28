package com.wgb.utils.tools.file;

import java.io.*;

/**
 * 快速文件输入
 *
 * @author INNERPEACE
 * @date 2019/2/2 10:15
 **/
/*public class InFile extends DataInputStream {
    public InFile(String fileName) throws FileNotFoundException {
        super(new BufferedInputStream(new FileInputStream(fileName)));
    }

    public InFile(File file) throws FileNotFoundException {
        this(file.getPath());
    }

}*/

public class InFile extends BufferedReader {
	public InFile(String fileName) throws FileNotFoundException {
		super(new InputStreamReader(new FileInputStream(fileName)));
	}

	public InFile(File file) throws FileNotFoundException {
		this(file.getPath());
	}

}
