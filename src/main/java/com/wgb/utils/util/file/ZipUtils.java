package com.wgb.utils.util.file;

import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author INNERPEACE
 */
@Slf4j
public class ZipUtils {

    public static void unZipEight(File srcFile, String destDirPath) {
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "文件不存在");
        }
        try (ZipFile zipFile = new ZipFile(srcFile)) {
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                log.info("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    if (targetFile.createNewFile()) {
                        // 将压缩文件内容写入到这个文件中
                        try (InputStream is = zipFile.getInputStream(entry); FileOutputStream fos = new FileOutputStream(targetFile)) {
                            int len;
                            byte[] buf = new byte[1024];
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

            /**
             * zip解压
             *
             * @param srcFile zip源文件
             * @param destDirPath 解压后的目标文件夹
             * @throws RuntimeException 解压失败会抛出运行时异常
             */
            public static void unZipSeven (File srcFile, String destDirPath) throws RuntimeException {
                // 判断源文件是否存在
                if (!srcFile.exists()) {
                    throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
                }

                // 开始解压
                ZipFile zipFile = null;
                InputStream is = null;
                FileOutputStream fos = null;
                try {
                    zipFile = new ZipFile(srcFile);
                    Enumeration<?> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = (ZipEntry) entries.nextElement();
                        log.info("解压" + entry.getName());
                        // 如果是文件夹，就创建个文件夹
                        if (entry.isDirectory()) {
                            String dirPath = destDirPath + "/" + entry.getName();
                            File dir = new File(dirPath);
                            dir.mkdirs();
                        } else {
                            // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                            File targetFile = new File(destDirPath + "/" + entry.getName());
                            // 保证这个文件的父文件夹必须要存在
                            if (!targetFile.getParentFile().exists()) {
                                targetFile.getParentFile().mkdirs();
                            }
                            if (targetFile.createNewFile()) {
                                // 将压缩文件内容写入到这个文件中
                                is = zipFile.getInputStream(entry);
                                fos = new FileOutputStream(targetFile);
                                int len;
                                byte[] buf = new byte[1024];
                                while ((len = is.read(buf)) != -1) {
                                    fos.write(buf, 0, len);
                                }
                            }
                            // 关流顺序，先打开的后关闭
                            fos.close();
                            is.close();
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("unzip error from ZipUtils", e);
                } finally {
                    try {
                        if (zipFile != null) {
                            zipFile.close();
                        }
                        if (fos != null) {
                            zipFile.close();
                        }
                        if (is != null) {
                            zipFile.close();
                        }
                    } catch (IOException e) {
                        log.error("zip file error", e);
                    }
                }
            }

            public static void zip (File sourceFile, String zipFileName){
                ZipOutputStream out = null;
                FileInputStream fos = null;
                try {
                    byte[] buffer = new byte[1024];
                    // 创建zip输出流
                    out = new ZipOutputStream(new FileOutputStream(zipFileName));
                    String base = sourceFile.getName();

                    out.putNextEntry(new ZipEntry(base));
                    fos = new FileInputStream(sourceFile);

                    int tag;
                    // 将源文件写入到zip文件中
                    while ((tag = fos.read(buffer)) != -1) {
                        out.write(buffer, 0, tag);
                    }
                    out.flush();
                } catch (Exception e) {
                    throw new RuntimeException("zip error from ZipUtils", e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e2) {
                    }
                }

            }

            /**
             * 压缩目录下 所有文件
             *
             * @param srcPath
             * @param zipFileName
             */
            public static void zipDir (String srcPath, String zipFileName) throws Exception {
                ZipOutputStream out = null;
                FileInputStream fos = null;
                try {
                    byte[] buffer = new byte[1024];
                    // 创建zip输出流
                    out = new ZipOutputStream(new FileOutputStream(zipFileName));
                    File[] files = new File(srcPath).listFiles();
                    // 目标文件
                    File tarFile = new File(zipFileName);
                    for (File file : files) {
                        // 过滤压缩文件本身
                        if (tarFile.equals(file)) {
                            continue;
                        }
                        String base = file.getName();
                        out.putNextEntry(new ZipEntry(base));
                        fos = new FileInputStream(file);
                        int tag;
                        // 将源文件写入到zip文件中
                        while ((tag = fos.read(buffer)) != -1) {
                            out.write(buffer, 0, tag);
                        }
                        out.flush();
                    }
                } catch (Exception e) {
                    throw new RuntimeException("zip error from ZipUtils", e);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e2) {
                    }
                }
            }

        }
