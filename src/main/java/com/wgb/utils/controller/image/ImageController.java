package com.wgb.utils.controller.image;

import com.wgb.utils.entity.oracle.Image;
import com.wgb.utils.entity.oracle.dto.ImageDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.service.image.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author INNERPEACE
 * @date 2019/4/23 15:07
 **/
@Slf4j
@Controller
public class ImageController {
	@Autowired
	ImageService imageService;

	@RequestMapping("/downloadAndUpload/getImage")
	public String downloadBookRecord(@Valid ImageDTO imageDTO, HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("获取图片信息的请求参数：{}", imageDTO.toString());
		Result<String> result = imageService.getImageAddress(imageDTO);
		if (result.isOk()) {
			String address = result.getData();
			model.addAttribute("imageAddress", address);
			if (address != null && address.length() > 100) {
				log.info("获取地址成功，地址内容的前100位为：{}", address.substring(0, 100));
			} else {
				log.info("获取地址成功：{}", address);
			}
		} else {
			log.info("获取地址失败!");
		}
		return "downloadAndUpload/downloadAndUpload";
	}
}
