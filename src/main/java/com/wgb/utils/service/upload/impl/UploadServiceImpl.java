package com.wgb.utils.service.upload.impl;

import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.upload.UploadService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2018/12/19 17:30
 **/
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    /**
     * 转存到固定目录方式上传
     * @param uploadfiles
     * @return
     */
    @Override
    public Result<String> uploadFile(MultipartFile[] uploadfiles) {
        if (uploadfiles == null || uploadfiles.length <= 0) {
            return new Result(ResultEnum.FAIL, "上传为空，请选择文件后再上传");
        }

        for (MultipartFile file : uploadfiles) {

            boolean result = saveFile(file);
            if (!result) {
                return new Result(ResultEnum.EXCEPTION, "上传时出现系统异常");
            }
        }
        return new Result(ResultEnum.SUCCESS, "上传成功");
    }

    /**
     * 转存文件
     * @param file 上传文件
     * @return 上传文件转存是否成功，true=成功，false=失败
     */
    private boolean saveFile(MultipartFile file) {
        String path = "D:\\wgb\\upload";
        String realpath = path + "\\";
        // 判断文件是否为空
        if (file.isEmpty()) {
            return false;
        }
        String filePath = realpath + file.getOriginalFilename();
        log.info("上传文件的地址（包含文件名）：{}", filePath);
        File uploadFile = new File(filePath);
        try {
            // 判断上传文件的父路径是否存在，不存在则创建
            /*
            boolean mkdir()
            创建此抽象路径名指定的目录。
            boolean mkdirs()
            创建此抽象路径名指定的目录，包括创建必需但不存在的父目录。
            也就是说，mkdir只能创建一层目录，而mkdirs可以创建多层。
            */
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }
            //  转存文件
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.info("上传文件[存储文件时]异常", e);
            return false;
        }
        return true;
    }

    /**
     * 改方式测试不成功，修正中......
     * @param request
     * @param response
     * @return
     */
    public Result uploadFile1(HttpServletRequest request, HttpServletResponse response) {

        //获得上传目录的绝对路径
        String path = "D:\\wgb\\upload";
        String realpath = path + "\\";
        log.info("上传路径：{}", realpath);
        String a = "测试输出";
        try {
            //构造一个文件上传处理对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upLoad = new ServletFileUpload(factory);
            //获得表单中提交内容
            List<FileItem> list = upLoad.parseRequest(request);
            log.info("获取到的表单内容list：{}", list);
            for (FileItem fileItem : list) {
                log.info("进入list里执行");
                //fileItem.isFormField()返回true表示是普通的表单组件
                //fileItem.isFormField()返回false表示是上传输入框
                if (fileItem.isFormField()) {
                    log.info("不同的表单组件");
                    //getFieldName()方法获得普通表单组件的参数名
                    String FieldName = fileItem.getFieldName();
                    //获得该参数所对应的值并指定编码
                    String Content = fileItem.getString("UTF-8");
                    //为了后面可以把普通参数从request中拿出来
                    request.setAttribute(FieldName, Content);
                } else {
                    log.info("上传输入框");
                    //getName()取得上传文件的名字
                    String fileName = fileItem.getName();
                    //避免文件名字重复
                    fileName = fileName + System.currentTimeMillis();
                    File file = new File(realpath, fileName);
                    //把上传文件进行指定目录
                    fileItem.write(file);
                }
            }
            //因为上面解析request中是数据后,把普通表单组件中的key和value放到了request中,所以这里可以拿出来
            log.info("request.getAttribute(\"username\") = {}", request.getAttribute("uploadFile"));

        } catch (Exception e) {
            log.error("上传异常", e);
            return new Result(ResultEnum.EXCEPTION);
        }
        RuntimeException runtimeException = new RuntimeException();
        log.info("测试异常输出：", runtimeException, a);
        return new Result(ResultEnum.SUCCESS);
    }
}
