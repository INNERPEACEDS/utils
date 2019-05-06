package com.wgb.utils.util.file;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;

/**
 * fastDFS 客户端工具类，将fastdfs-client.properties文件放到classes目录下。
 * <p>
 * maven私库地址
 * <p>
 * <dependency> <groupId>org.csource</groupId>
 * <artifactId>fastdfs-client-java</artifactId>
 * <version>1.27</version>
 * </dependency>
 *
 * @date 2017-08-16
 */
@Slf4j
public class FastDFSClient {
    private TrackerServer trackerServer = null;
    private StorageClient1 storageClient = null;

    public FastDFSClient() throws IOException, MyException {
        init();
    }

    private void init() throws IOException, MyException {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        // 创建一个TrackerClient对象。
        TrackerClient trackerClient = new TrackerClient();
        // 创建一个TrackerServer对象。
        trackerServer = trackerClient.getConnection();
        // 声明一个StorageServer对象，null。
        // 获得StorageClient对象。
        storageClient = new StorageClient1(trackerServer, null);
    }

    /**
     * 依据本地文件名上传文件
     *
     * @param localFilename 本地文件名
     * @return 文件系统中的路径
     */
    public String uploadLocalFile(String localFilename) throws IOException, MyException {
        String fileExtName = getFileNamePrefix(localFilename);
        return uploadLocalFile(localFilename, fileExtName, null);
    }

    /**
     * 自选分组上传本地文件 默认文件后缀即为文件类型
     *
     * @param groupName     组名称
     * @param localFilename 本地文件路径
     * @return 文件系统路径
     * @throws IOException 1
     * @throws MyException 1
     */
    public String uploadLocalFile(String groupName, String localFilename) throws IOException,
            MyException {
        String fileExtName = getFileNamePrefix(localFilename);
        return uploadLocalFile(groupName, fileExtName, localFilename, null);
    }

    /**
     * 上传文件
     *
     * @param localFilename 本地文件路径
     * @param fileExtName   文件扩展名，例如：txt,jpg，png等
     * @param metaList      元文件信息
     * @return fds文件系统路径
     * @throws IOException 1
     * @throws MyException 1
     */
    public String uploadLocalFile(String localFilename, String fileExtName,
                                  NameValuePair[] metaList) throws IOException, MyException {
        return storageClient.upload_file1(localFilename, fileExtName, metaList);
    }

    /**
     * 自选组名上传本地文件
     *
     * @param groupName     组名
     * @param localFilename 本地文件名称
     * @param fileExtName   本地文件拓展名
     * @param metaList      元文件信息
     * @return fds文件系统路径
     * @throws IOException 1
     * @throws MyException 1
     */
    public String uploadLocalFile(String groupName, String localFilename,
                                  String fileExtName, NameValuePair[] metaList) throws IOException,
            MyException {
        return storageClient.upload_file1(groupName, localFilename,
                fileExtName, metaList);
    }

    /**
     * 上传二进制文件
     *
     * @param fileBuff    文件内容
     * @param fileExtName 文件扩展名，例如：txt,jpg，png等
     * @return 文件名
     */
    public String upload(byte[] fileBuff, String fileExtName) throws IOException, MyException {
        return upload(fileBuff, fileExtName, null);
    }

    /**
     * 上传二进制文件 自定义分组
     *
     * @param groupName   组名：group1，group2....
     * @param fileBuff    文件内容
     * @param fileExtName 文件扩展名，例如：txt,jpg，png等
     */
    public String upload(String groupName, byte[] fileBuff, String fileExtName) throws IOException, MyException {
        return upload(groupName, fileBuff, fileExtName, null);
    }

    /**
     * 上传文件
     *
     * @param groupName   组名：group1，group2....
     * @param fileBuff    文件内容
     * @param fileExtName 文件扩展名，例如：txt,jpg，png等
     * @param metaList    元文件信息
     */
    public String upload(String groupName, byte[] fileBuff, String fileExtName,
                         NameValuePair[] metaList) throws IOException, MyException {
        return storageClient.upload_file1(groupName, fileBuff, fileExtName,
                metaList);
    }

    /**
     * 上传文件
     *
     * @param fileBuff    文件内容
     * @param fileExtName 文件扩展名，例如：txt,jpg，png等
     * @param metaList    元文件信息
     * @return 文件名
     */
    private String upload(byte[] fileBuff, String fileExtName,
                          NameValuePair[] metaList) throws IOException, MyException {
        return storageClient.upload_file1(fileBuff, fileExtName, metaList);
    }

    /**
     * @param fileId 文件编号：group1/M00/00/00/wKgABVmT1TaAQIqnAACE6Tcpens217.jpg
     * @return http访问url
     */
    public String getUrl(String fileId) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, MyException {
        int ts;
        String token;
        String fileUrl;
        InetSocketAddress inetSockAddr;
        inetSockAddr = trackerServer.getInetSocketAddress();
        fileUrl = "http://" + inetSockAddr.getAddress().getHostAddress();
        if (ClientGlobal.g_tracker_http_port != 80) {
            fileUrl += ":" + ClientGlobal.g_tracker_http_port;
        }
        fileUrl += "/" + fileId;
        fileId = fileId.substring(fileId.indexOf("/") + 1);
        if (ClientGlobal.g_anti_steal_token) {
            ts = (int) (System.currentTimeMillis() / 1000);
            token = ProtoCommon.getToken(fileId, ts, ClientGlobal.g_secret_key);
            fileUrl += "?token=" + token + "&ts=" + ts;
        }
        return fileUrl;
    }

    /**
     * 获取图片实际地址
     * @param fileId 文件编号：group1/M00/00/00/wKgABVmT1TaAQIqnAACE6Tcpens217
     * @return 图片转换的base64地址为
     */
    public static String getRealPath(String fileId){
        String realPath="";
        if(fileId!=null && !"".equals(fileId)){
            try {
                FastDFSClient client = new FastDFSClient();
                byte[] rp=client.downloadFile(fileId);
                if (rp != null && rp.length > 0) {
                    BASE64Encoder encoder = new BASE64Encoder();
                    realPath="data:image/jpg/png/gif/bmp/jpeg;base64,"+encoder.encode(rp);
                }
            } catch (Exception e) {
                log.error("[图片下载失败]", e);
            }
        }
        return realPath;
    }


    /**
     * 用户可获取实例后，自行调用需要的方法
     */
    public StorageClient1 getStorageClient() {
        return this.storageClient;
    }

    public String getFileNamePrefix(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public byte[] downloadFile(String filePath) throws IOException, MyException {
        if (StringUtil.isEmpty(filePath)) {
            throw new RuntimeException("下载文件不存在");
        }
        return storageClient.download_file(getGroupName(filePath),
                getFileName(filePath));
    }

    /**
     * 获取文件组名称
     *
     * @param fileId 文件路径
     * @return 文件组名称
     */
    private String getGroupName(String fileId) {
        if (StringUtil.isEmpty(fileId)) {
            return "";
        }
        Integer index = getGroupAndFileNameSplit(fileId);
        return fileId.substring(0, index);
    }

    /**
     * 获取文件名称
     *
     * @param fileId 文件路径
     * @return 文件名称
     */
    private String getFileName(String fileId) {
        if (StringUtil.isEmpty(fileId)) {
            throw new NullPointerException("文件名路径不存在");
        }
        Integer index = getGroupAndFileNameSplit(fileId);
        if (index < 1) {
            return "";
        }
        return fileId.substring(index + 1);
    }

    /**
     * 切割文件中组路径
     *
     * @param fileId 文件路径
     * @return 文件组路径
     */
    private Integer getGroupAndFileNameSplit(String fileId) {
        if (fileId == null || fileId.trim().length() == 0) {
            throw new NullPointerException("文件名路径不存在");
        }
        return fileId
                .indexOf(StorageClient1.SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR);
    }

    /**
     * 上传文件
     *
     * @param inStream    输入流
     * @param fileExtName 文件后缀名
     * @return 文件地址
     * @date 2017-8-18
     */
    public String upload(InputStream inStream, String fileExtName)
            throws IOException, MyException {
        byte[] b = input2byte(inStream);
        return upload(b, fileExtName, null);
    }

    private byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

}
