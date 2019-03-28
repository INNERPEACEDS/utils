package com.wgb.utils.util.file;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;

/**
 * @author INNERPEACE
 * @date 2018/12/25 13:13
 **/
@Slf4j
public class FileUtil {
    private static List<String> defaultNeedFileType = new ArrayList<String>(
            Arrays.asList(
                    ".txt", ".html", ".jpg", "bmp", ".png", ".zip"
            )
    );

    public static boolean isNeedFileType(String fileName) {
        return isNeedFileType(fileName, defaultNeedFileType);
    }

    public static boolean isNeedFileType(String fileName, List<String> needFileType) {
        String fileSuffix = getFileSuffix(fileName);
        fileSuffix = fileSuffix.toLowerCase();
        for (String fileType : needFileType) {
            if (fileType.equals(fileSuffix)) {
                log.info("文件名后缀：{}", fileSuffix);
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名前缀（获取文件名）
     *
     * @param fileName 文件全称（包含文件扩展名，例如：file.txt）
     * @return 文件名称
     */
    public static String getFilePrefix(String fileName) {
        if (StringUtil.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 获取文件名后缀，即文件扩展名（包含“.”，例如：.txt）
     * @param fileName 文件全称
     * @return 文件名称后缀（文件格式类型，即文件扩展名）
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtil.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取文件名后缀，即文件扩展名（不包含“.”,例如：tex）
     * @param fileName 文件全称
     * @return 文件名称后缀（文件格式类型，即文件扩展名）
     */
    public static String getFileSuffixNoSign(String fileName) {
        if (StringUtil.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 读取提供路径下的文件和文件夹（不进行递归子目录查询）
     * 注意，提供的目录最后未加“/”,例如:E:/deploy/manage
     * @param projectFileNameDirectory 提供路径
     * @return
     */
    public static List<String> readFileDirectory(String projectFileNameDirectory) {
        List<String> projectFileName = new ArrayList<>();
        File file = new File(projectFileNameDirectory + "/");
        File[] tempList = file.listFiles();
        if (tempList != null && tempList.length > 0) {
            log.info("{}目录下文件个数：{}", projectFileNameDirectory, tempList.length);
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    log.info("[{}:文件]{}",i, tempList[i]);
                }
                if (tempList[i].isDirectory()) {
                    // tempList[i] = E:\deploy\manage
                    log.info("[{}:文件夹]{}",i, tempList[i]);
                    // tempList[i].getName()=manage
                }
                projectFileName.add(tempList[i].getName());
            }
            return projectFileName;
        }
        log.info("[{}]目录下未存放文件和文件夹，请存放以后在操作", projectFileNameDirectory);
        return null;
    }

    /**
     * 获取提供目录下的所有文件和文件目录
     * map("files":list)
     * map("directories":list)
     * @param directory 提供目录（结尾不提供“/”）
     * @return
     */
    public static Map<String, List<String>> obtainAllFilesAndDirectories(String directory) {
        List<String> files = new ArrayList<>();
        List<String> directories = new ArrayList<>();
        File file = new File(directory + "/");
        obtainFileAndDirectory(files, directories, file);
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("files", files);
        map.put("directories", directories);
        return map;
    }

    /**
     * 递归获取文件（包含文件夹下的文件）和文件夹
     * @param files
     * @param directories
     * @param file
     */
    public static void obtainFileAndDirectory(List<String> files, List<String> directories, File file) {
        File[] allFile = file.listFiles();
        for (File f : allFile) {
            if (f.isFile()) {
                log.info("[{}:文件]{}", f);
                files.add(f.getPath());
            }
            if (f.isDirectory()) {
                // tempList[i] = E:\deploy\manage
                log.info("[{}:文件夹]{}", f);
                // tempList[i].getName()=manage
                directories.add(f.getPath());
                obtainFileAndDirectory(files, directories, f);
            }
        }
    }


    public static void main(String[] args) {
        // String fileName = "你好.txt";
        // boolean neeaFileType = isNeedFileType(fileName);
        // String fileSuffix = getFileSuffix(fileName);
        // String filePrefix = getFilePrefix(fileName);
        // log.info("结果：[文件名称]-[{}],[文件格式类型]-[{}]", filePrefix, fileSuffix);
        Map<String, List<String>> map = obtainAllFilesAndDirectories("E:/deploy");
        List<String> files = map.get("files");
        List<String> directories = map.get("directories");
        for (String filePath : files) {
            log.info("文件路径：{}", filePath);
        }
        for (String directoryPath : directories) {
            log.info("文件夹路径：{}", directoryPath);
        }
    }

}
