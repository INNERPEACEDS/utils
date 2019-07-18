package com.wgb.utils.util.file;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author INNERPEACE
 * @date 2019/1/23 17:11
 * 增量部署文件获取
 **/
@Slf4j
public class FileObtain {

    /**
     * 默认从已打包的jar文件中获取部署文件
     */
    private static int type = 1;

    /**
     * 复制文件成功个数
     */
    private static int successCount = 0;

    /**
     * 复制文件失败个数
     */
    private static int errorCount = 0;

    /**
     * 部署文件的基础目录
     */
    private static final String DEPLOY_BASE_PATH = "E:/deploy/";

    /**
     * 部署文件(或者想要根据路径获取文件目录结构文件的项目名)名称(例如：manage)
     */
    private static final String PROJECT_NAME;

    /**
     * 根据提供文件打包目录获取项目文件名称
     */
    private static final String OBTAIN_PROJECT_NAME = obtainProjectNameBySourceFileDirectory();

    static {
        PROJECT_NAME = obtainFileDirectoryName();
    }

    /**
     * 动态项目名
     */
    private static String projectName = PROJECT_NAME;

    /**
     * 读取需要获取的文件路径所存放的配置文件
     */
    private static final String DEPLOY_READ_PATH_CONFIGURATION_FILE = DEPLOY_BASE_PATH + "path.txt";

    /**
     * 打包后手动把项目文件存放到固定目录的地址
     */
    private static final String DEPLOY_SOURCE_FILE_DIRECTORY = DEPLOY_BASE_PATH + PROJECT_NAME ;

    /**
     * 从打包文件中拿出需要增量部署的文件地址
     */
    private static final String DEPLOY_INCREMENT_FILE_DIRECTORY = DEPLOY_BASE_PATH + "increment/" + PROJECT_NAME;

    /**
     * 从打包文件中动态拿出需要增量部署的文件地址（复制成功后项目存放地址）
     */
    private static String deployIncrementFileDirectory = DEPLOY_INCREMENT_FILE_DIRECTORY;

    private static final String INCREMENT_FILE_DIRECTORY = "increment";

    private static final String DEPLOY_TARGET_PROJECT_PATH = "D:/Java/eclipse4.6.0ys/workspace/git/manage";

    /**
     * 项目名称存放位置
     */
    // private static final String DEPLOY_PROJECT_NAME_CONFIGURATION_FILE = "E:/deploy/projectName.txt";
    /**
     * 易势内管地址
     */
    private static final String SOURCE_FILE_DIRECTORY = "D:/Java/eclipse4.6.0master/workspace/manage/target/manage";

    /**
     * 信汇内管地址
     */
    private static final String SOURCE_FILE_DIRECTORY1 = "D:/Java/eclipse4.6.0/workspace/xhepay/manage/target/manage";

    /**
     * 易势商户系统地址
     */
    private static final String SOURCE_FILE_DIRECTORY2 = "D:/Java/eclipse4.6.0master/workspace/yspay/target/ROOT";

	/**
     * 信汇商户系统地址
     */
	private static final String SOURCE_FILE_DIRECTORY3 = "D:/Java/eclipse4.6.0/workspace/xhepay/yspay/target/ROOT";

    /**
     * 设置动态数据
     * @param path 打包项目所在地址
     */
    private static void setDynamicData(String path) {
        String projectName1 = obtainProjectNameBySourceFileDirectory(path);
        projectName = projectName1;
        deployIncrementFileDirectory = DEPLOY_BASE_PATH + "increment/" + projectName;
    }


    /**
     * 从默认项目地址目录获取名称
     * @return
     */
    private static String obtainProjectNameBySourceFileDirectory() {
       /* String str = SOURCE_FILE_DIRECTORY.substring(SOURCE_FILE_DIRECTORY.lastIndexOf("/") + 1);
        log.info("从项目地址获取目录名称为:{}", str);
        return str;*/
        return obtainProjectNameBySourceFileDirectory("");
    }

    /**
     * 动态获取项目名
     * @param path
     * @return
     */
    private static String obtainProjectNameBySourceFileDirectory(String path) {
        if (StringUtil.isBlank(path)) {
            path = SOURCE_FILE_DIRECTORY;
        }
        String str = path.substring(path.lastIndexOf("/") + 1);
        return str;
    }

    /**
     * “/src/main/java”转换成“/WEB-INF/classes”
     * “/src/main/webapp”转换成“”
     * “.java”转换成“.class”
     */
    private static Map<String, String> replaceMap = new HashMap<String, String>(){{
        put("java", "WEB-INF/classes");
        put("webapp", "");
        put(".java", ".class");
    }};

    public static String obtainFileDirectoryName() {
        String projectName = obtainFileDirectoryNameByInherentDirectory(DEPLOY_BASE_PATH);
        // log.info("projectName:{}", projectName);
        if (projectName == null) {
            projectName = OBTAIN_PROJECT_NAME;
        }
        // log.info("projectName:{}", projectName);
        return projectName;
    }

    /**
     * 读取E:/deploy/文件下的目录，获取部署文件的名称
     * @param projectFileNameDirectory
     * @return
     */
    public static String obtainFileDirectoryNameByInherentDirectory(String projectFileNameDirectory) {
        List<String> projectFileName = new ArrayList<>();
        File file=new File(projectFileNameDirectory);
        File[] tempList = file.listFiles();
        if (tempList != null && tempList.length > 0) {
            log.info("{}目录下文件个数：{}", projectFileNameDirectory,tempList.length);
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    log.info("[{}:文件]{}",i, tempList[i]);
                }
                if (tempList[i].isDirectory()) {
                    // tempList[i] = E:\deploy\manage
                    log.info("[{}:文件夹]{}",i, tempList[i]);
                    // tempList[i].getName()=manage
                    if (!INCREMENT_FILE_DIRECTORY.equals(tempList[i].getName())) {
                        projectFileName.add(tempList[i].getName());
                    }
                }
            }
            if (projectFileName != null && projectFileName.size() == 1) {
                return projectFileName.get(0);
            }
            log.info("[{}]目录下未存放打包好的文件或者存放多个文件，请存放一个进行操作，或者采用提供目录方式操作", DEPLOY_BASE_PATH);
            return null;
        }
        log.info("[{}]目录下未存放打包好的文件，请存放以后在操作", DEPLOY_BASE_PATH);
        return null;
    }

    /**
     * 读取部署文件(或者想要根据路径获取文件目录结构文件的项目名)名称
     * @param projectFileNameDirectory
     * @return
     */
    public static String readProjectName(String projectFileNameDirectory) {
        BufferedReader br = null;
        List<String> projectName = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(projectFileNameDirectory));
            String line;
            while ((line = br.readLine()) != null) {
                if (!StringUtil.isBlank(line)) {
                    projectName.add(line);
                }
            }
        } catch (Exception e) {
            log.info("读取部署文件名的路径写数据异常！异常原因：", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                log.info("关闭文件读取流异常：", e);
            }
        }
        if (projectName != null && projectName.size() == 1) {
            return projectName.get(0);
        }
        log.info("部署文件名必须写而且只能写一个");
        return null;
    }

    /**
     *  取配置文件里的文件路径写到数组中
     * @param propertiesFileNameDirectory
     * @param parseLineWay 解析行的方式
     * @return
     */
    public static  String[] readFile(String propertiesFileNameDirectory, String parseLineWay) {
        long st = System.currentTimeMillis();
        List<String> fileDirectory = new ArrayList<>();
        BufferedReader br = null;
        try {
            //封装
            br = new BufferedReader(new FileReader(propertiesFileNameDirectory));
            String line;
            while ((line = br.readLine()) != null) {
                // 读取配置文件里的路径写到list中去
                if (!StringUtil.isBlank(line)) {
                    switch (parseLineWay) {
                        // 方式1：使用解析json格式数据转换
                        case "jsonData":
                            String parseLine = parseLine(line);
                            if (parseLine != null) {
                                fileDirectory.add(parseLine);
                            }
                            break;
                        // 方式2：人工直接在文件夹里输入路径，不转化方式
                        case "address":
                            fileDirectory.add(line);
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            log.info("读取配置文件里的路径写数据异常！异常原因：", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                log.info("关闭文件读取流异常：", e);
            }
        }
        log.info("读取[{}]文件耗时：{}毫秒", propertiesFileNameDirectory, System.currentTimeMillis() - st);
        return fileDirectory.toArray(new String[fileDirectory.size()]);
    }

    public static String parseLine(String line) {
        JSONObject pathJson = null;
        // JSONArray ipType = null;
        String path = null;
        try {
            // 要进行解析的行信息
            // log.info("line：{}", line);
            pathJson = new JSONObject(line);
            // ipType = (JSONArray) ips.get("login");
            path = (String) pathJson.get("text");
        } catch (JSONException e) {
            log.error("初始化Json数据异常,输入格式不是json格式", e);
        }
        if (path == null) {
            log.info("解析行数据路径为空，请检查！行数据为：[{}]", line);
        }
        return path;
    }

    /**
     * 通过提供的路径获取真实的文件路径(不包含：基础路径+项目名称)
     * 其中包含“/”,例如：/src/main/java/com/buybal/epay/model/sql/HD_MAG_TRANS_SqlMap.xml
     * @param fileNames
     * @return
     */
    public static String[] fileNamesAnalysis(String[] fileNames) {
        String[] obtainFileNames = fileNames;
        if (fileNames != null && fileNames.length > 0) {
            log.info("要转换的文件有{}个", fileNames.length);
            for (int i=0; i < fileNames.length; i++) {
                /*log.info("开始要转换的路径为->{}", fileNames[i]);*/
                obtainFileNames[i] = obtainRealFileNameDirectory(fileNames[i]);
                if (obtainFileNames[i] == null) {
                    throw new RuntimeException("文件路径填写错误，请检查,填写内容为：" + fileNames[i]);
                }
            }
            return obtainFileNames;
        }
        return null;
    }

    /**
     * 获取单个文件名目录
     * @param offerFileNameDirectory
     * @return
     */
    public static String obtainRealFileNameDirectory(String offerFileNameDirectory) {
        String realFileNameDirectory = "";
        // 对提供的文件目录进行分割
        String[] fragmentation = offerFileNameDirectory.split("/");
        if (fragmentation != null && fragmentation.length > 3) {

            /*log.info("对文件目录进行分割后的数据：{},长度为：{}", fragmentation, fragmentation.length);*/
            if (replaceMap.get(fragmentation[2]) != null) {
                /*log.info("需要转换的文件碎目录为：[{}->{}]", fragmentation[2], replaceMap.get(fragmentation[2]));*/
                if (replaceMap.get(fragmentation[2]) != null && !"".equals(replaceMap.get(fragmentation[2]))) {
                    realFileNameDirectory = "/" + replaceMap.get(fragmentation[2]);
                }
                for (int i = 3; i < fragmentation.length; i++) {
                    realFileNameDirectory += "/" +fragmentation[i];
                }
                /*log.info("转换后的文件目录为(未将.java文件转换成.class文件)：{}", realFileNameDirectory);*/
                /*如果为.java文件路径，改为.class文件路径*/
                String[] splitRealFileNameDirectory = realFileNameDirectory.split("\\.");
                if (splitRealFileNameDirectory != null && splitRealFileNameDirectory.length == 2) {
                    /* log.info("使用.分割符分割后的数据为：\\{{},{}\\}", splitRealFileNameDirectory[0], splitRealFileNameDirectory[1]);*/
                    /*判断是否为.java结尾，如果是，进行替换*/
                    if (replaceMap.get("." + splitRealFileNameDirectory[1]) != null) {
                        realFileNameDirectory = splitRealFileNameDirectory[0] + replaceMap.get("." + splitRealFileNameDirectory[1]);
                    }
                    /*log.info("转换后的文件目录为：{}", realFileNameDirectory);*/
                    return realFileNameDirectory;
                }
                log.error("文件路径格式不正确请检查!" );
            }
        }
        log.info("对文件目录进行分割后的数据为空");
        return null;
    }

    /**
     * 增量添加文件复制
     * @param fileNames
     * @param deployIncrementFileDirectory 复制成功后项目的存放地址
     */
    public static void incrementCopyFile(String[] fileNames, String sourceFileDirectory, String deployIncrementFileDirectory) {
        if (fileNames != null && fileNames.length > 0) {
            for (int i = 0; i < fileNames.length; i++) {
                writeFile(fileNames[i], sourceFileDirectory, deployIncrementFileDirectory);
            }
        } else {
            log.info("增量的文件数据为空");
        }
    }

    /**
     * 对要获取的文件名及其目录copy（增量部署使用）
     * 目录E:\view.sql 拷贝到 F:\test\view.sql
     * @param fileName
     * @param sourceFileDirectory 打包的文件目录(例如：D:\Java\eclipse4.6.0master\workspace\manage\target\manage)
     * @param deployIncrementFileDirectory 复制成功后项目存放地址
     */
    public static  void writeFile(String fileName, String sourceFileDirectory, String deployIncrementFileDirectory) {
        // File file = new File("F:\\view.sql");
        File file = new File(sourceFileDirectory + fileName);
        if (file.exists()) {
            if (!file.isFile()) {
                log.info("[{}]不是一个文件", sourceFileDirectory + fileName);
            }
        } else {
            log.info("[{}]不存在", sourceFileDirectory + fileName);
            errorCount ++;
            return;
        }
        File copyFile = new File(deployIncrementFileDirectory + fileName);
        File filePath = copyFile.getParentFile();
        if(!filePath.exists()) {
            filePath.mkdirs();
        }
        try {
            copyFile.createNewFile();
        } catch (IOException e) {
            log.error("复制文件异常（io异常），异常信息：", e);
        }
        copyFile(file,copyFile);
    }

    /**
     * 复制文件
     * @param file     源文件
     * @param copyFile copy文件
     */
    public static void copyFile(File file, File copyFile) {
        FileInputStream fis=null;
        FileOutputStream fos=null;
        FileChannel in=null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(copyFile);
            // 得到对应的文件通道
            in = fis.getChannel();
            // 得到对应的文件通道
            out= fos.getChannel();
            long start = System.currentTimeMillis();
            // 连接两个通道，并且从in通道读取，然后写入out通道
            in.transferTo(0, in.size(), out);
            long end = System.currentTimeMillis();
            log.info("文件[{}]：复制成功，运行时间：{}毫秒", file.getPath(), end-start);
            successCount++;
        } catch (Exception e) {
            log.error("[复制文件-copyFile]-读写异常", e);
        } finally {
            try {
                fis.close();
                in.close();
                fos.close();
                out.close();
            } catch (IOException e) {
                log.info("[复制文件-copyFile]-关闭流异常", e);
            }
        }
    }

    /**
     * 增量部署文件
     * @param sourceFileDirectory 打包项目后的文件路径
     * @param deployIncrementFileDirectory 复制成功后的项目存放地址
     */
    public static void incrementDeployProject(String sourceFileDirectory, String deployIncrementFileDirectory, String parseLineWay) {
        // 1.读取配置文件里的文件路径
        String[] fileNameDirectories = readFile(DEPLOY_READ_PATH_CONFIGURATION_FILE, parseLineWay);
        if (fileNameDirectories != null && fileNameDirectories.length > 0) {
            String[] realFileNameDirectories = fileNamesAnalysis(fileNameDirectories);
            int i = 0;
            for (String realFileNameDirectory : realFileNameDirectories) {
                log.info("文件{}:{}", ++i, realFileNameDirectory);
            }
            log.info("start：增量文件复制：");
            incrementCopyFile(realFileNameDirectories, sourceFileDirectory, deployIncrementFileDirectory);
            log.info("end：增量文件复制！");
            log.info("复制文件结果：[成功个数={};失败个数={}]", successCount, errorCount);
            return ;
        }
        log.info("获取文件路径为空，请先填写路径再执行！");
    }

    /**
     * 获取真实文件路径（在提供路径中添加前缀“/”）
     * 例如：“a/b.xml”改为：“/a/b.xml”
     * @return
     */
    public static String[] obtainRealFilePath(String[] fileDirectories) {
        if (fileDirectories != null && fileDirectories.length > 0) {
            for (int i = 0; i < fileDirectories.length; i++) {
                fileDirectories[i] = "/" + fileDirectories[i];
            }
        }
        return fileDirectories;
    }

    /**
     * 根据路径获取文件
     * @param path 路径
     * @param sourceFileDirectory 打包项目后的文件路径
     * @param deployIncrementFileDirectory 复制成功后的项目存放地址
     */
    public static void obtainFileByPath(String path, String sourceFileDirectory, String deployIncrementFileDirectory, String parseLineWay) {
        String[] fileNameDirectories = readFile(path, parseLineWay);
        String[] realFileNameDirectories = obtainRealFilePath(fileNameDirectories);
        if (realFileNameDirectories != null && realFileNameDirectories.length > 0) {
            int i = 0;
            for (String realFileNameDirectory : realFileNameDirectories) {
                log.info("文件{}:{}", ++i, realFileNameDirectory);
            }
            log.info("start：根据路径获取文件：");
            incrementCopyFile(fileNameDirectories, sourceFileDirectory, deployIncrementFileDirectory);
            log.info("end：根据路径获取文件！");
            log.info("复制文件结果：[成功个数={};失败个数={}]", successCount, errorCount);
            return ;
        }
        log.info("获取文件路径为空，请先填写路径再执行！");
    }

    /**
     * 根据tomcat编译的target文件路径获取
     * @param path
     */
    public static void obtainFileByTarget(String path) {
        // .class文件在提供的目录下的target目录中寻找；其他类型文件在提供的src目录下寻找
        // 找到以后拼接目录结构

    }




    /**
     * 执行根据路径获取文件操作
     */
    /**
     * 执行类型，0=无操作
     * 1=打包文件中获取增量部署文件
     * 2=一般项目文件
     * 3=从target中获取部署文件（未完成，该方式在思考后不是最优选择，故放弃编写，使用4方式）
     * 3=直接从D:\Java\eclipse4.6.0ys\workspace\git\manage\target目录下获取部署文件
     * 4=直接使用项目打包后的目录，进行增量部署文件获取(易势内管地址)
     * 5=直接使用项目打包后的目录，进行增量部署文件获取(信汇内管地址)
     * 6=直接使用项目打包后的目录，进行增量部署文件获取(易势商户系统地址)
     * @param type 执行类型
     * @param path 打包的项目存放地址
     */
    public static void execute(int type, String path, String parseLineWay) {
        setDynamicData(path);
        // 如果不是直接使用项目打包后的目录，采用自动部署获取方式
        if (type != 4 && type != 5 && type != 6 && type != 7) {
            List<String> list = FileUtil.readFileDirectory(DEPLOY_SOURCE_FILE_DIRECTORY);
            String[] directories = list.toArray(new String[list.size()]);
            //
            for (String directory : directories) {
                // 发现在E:\deploy\下的项目为未打包的项目，转换成未打包方式获取目录文件
                if ("src".equals(directory)) {
                    type = 2;
                    break;
                }
            }
        }
        switch (type) {
            // 在打包好的项目放到E:\deploy下进行文件获取
            case 1:
                log.info("===采用将打包好的项目放到E:\\deploy下进行文件获取方式操作===");
                incrementDeployProject(DEPLOY_SOURCE_FILE_DIRECTORY, DEPLOY_INCREMENT_FILE_DIRECTORY, parseLineWay);
                break;
            case 2:
                obtainFileByPath(DEPLOY_READ_PATH_CONFIGURATION_FILE, DEPLOY_SOURCE_FILE_DIRECTORY, DEPLOY_INCREMENT_FILE_DIRECTORY, parseLineWay);
                break;
            case 3:
                obtainFileByTarget(DEPLOY_TARGET_PROJECT_PATH);
                break;
            case 4:
                log.info("===采用提供打包文件目录方式操作（易势内管）===");
                incrementDeployProject(SOURCE_FILE_DIRECTORY, deployIncrementFileDirectory, parseLineWay);
                break;
            case 5:
                log.info("===采用提供打包文件目录方式操作（信汇内管）===");
                incrementDeployProject(SOURCE_FILE_DIRECTORY1, deployIncrementFileDirectory, parseLineWay);
                break;
            case 6:
                log.info("===采用提供打包文件目录方式操作（易势商户系统）===");
                incrementDeployProject(SOURCE_FILE_DIRECTORY2, deployIncrementFileDirectory, parseLineWay);
                break;
	        case 7:
		        log.info("===采用提供打包文件目录方式操作（信汇商户系统）===");
		        incrementDeployProject(SOURCE_FILE_DIRECTORY3, deployIncrementFileDirectory, parseLineWay);
		        break;
            default:
        }
    }

    public static void main(String[] args) {
        // String[] fileNameDirectories = {"src/main/webapp/M3gr/MobilePayMag/Regesitor.jsp", "src/main/java/com/buybal/epay/dao/MobilePayApplyDao.java", "src/main/java/com/buybal/epay/dao/MobilePayDAO.java"};
        // String[] realFileNameDirectories = fileNamesAnalysis(fileNameDirectories);
        // 执行任务
        type = 6;
        String path = SOURCE_FILE_DIRECTORY;
        if (type == 4) {
            path = SOURCE_FILE_DIRECTORY;
        }
        if (type == 5) {
            path = SOURCE_FILE_DIRECTORY1;
        }
        if (type == 6) {
            path = SOURCE_FILE_DIRECTORY2;
        }
	    if (type == 7) {
		    path = SOURCE_FILE_DIRECTORY3;
	    }

        // 解析行方式：【0：jsonData=解析json数据获取文件路径，例如：{"text":"src/main/webapp/WEB-INF/struts-config.xml","gfm":"`src/main/webapp/WEB-INF/struts-config.xml`"}；1：address=直接人工手动添加地址，例如：src/main/java/com/buybal/epay/dao/TRegionInfoDAO.java】
        String[] parseLineWays = {"jsonData", "address"};
        String parseLineWay = parseLineWays[1];
        execute(type, path, parseLineWay);
    }

}
