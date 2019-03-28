package com.wgb.utils.controller.downloadandupload;

import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.download.DownloadService;
import com.wgb.utils.service.upload.UploadService;
import com.wgb.utils.util.file.FastDFSClient;
import com.wgb.utils.util.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : innerpeace
 * @date : 2018/11/29 16:07
 */
@Slf4j
@Controller
@RequestMapping("/downloadAndUpload")
public class DownloadAndUploadController {
	@Autowired
	DownloadService downloadService;

	@Autowired
	UploadService uploadService;

	@RequestMapping("/page")
	public String downloadAndUploadJumpPage() {
		return "downloadAndUpload/downloadAndUpload";
	}

	@RequestMapping("/download")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		log.info("开始下载");
		String fileName = request.getParameter("fileName");
		downloadService.downloadFile(fileName, request, response);
	}



	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("uploadFile") MultipartFile[]  uploadFiles, Model model) {
		String forward = "downloadAndUpload/downloadAndUpload";
		log.info("开始上传");
		Result<String> result = uploadService.uploadFile(uploadFiles);
		log.info("返回值：[{}]-[{}]", result.getCode(), result.getMsg());
		String uploadResult = result.getData();
		model.addAttribute("uploadResult",  uploadResult);
		if (result.isFail()) {
			log.error(uploadResult);
		}
		return forward;
	}

	@RequestMapping("/uploadByFastDFS")
	public Result<Map<String, String>> uploadFileBy(MultipartFile multipartFile) {
		log.info("开始上传");
		if (multipartFile == null || multipartFile.isEmpty()) {
			return new Result<>(ResultEnum.UPLOAD_EXCEPTION);
		}
		if (!FileUtil.isNeedFileType(multipartFile.getOriginalFilename())) {
			return new Result<>(ResultEnum.UPLOAD_TYPE_EXCEPTION);
		}
        try {
            FastDFSClient fastDFSClient = new FastDFSClient();
            String fileId = fastDFSClient.upload(multipartFile.getBytes(), fastDFSClient.getFileNamePrefix(multipartFile.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(2);
            map.put("fileId", fileId);
            map.put("url", fastDFSClient.getUrl(fileId));
            return new Result<>(ResultEnum.SUCCESS,map);
        } catch (IOException | MyException | NoSuchAlgorithmException e) {
            log.error("文件上传失败，文件名{}", multipartFile.getOriginalFilename(), e);
            return new Result<>(ResultEnum.UPLOAD_EXCEPTION);
        }
	}
}
