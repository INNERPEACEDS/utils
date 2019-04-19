package com.wgb.utils.controller.downloadandupload;

import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.util.file.FastDFSClient;
import com.wgb.utils.util.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异步上传控制器
 * @author INNERPEACE
 * @date 2019/4/19 10:35
 **/
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
	/**
	 * 上传参数注意，由于layui的上传参数name="file"(默认，可以增加field参数值修改默认值)，所以在上传中的参数要写成file
	 * @param multipartFile
	 * @return
	 */
	@RequestMapping
	public Result<Map<String, String>> upload(@RequestParam("file") MultipartFile multipartFile) {
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

