package com.wgb.utils.controller.image;

import com.github.pagehelper.PageInfo;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.dto.ImageDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.image.ImageService;
import com.wgb.utils.util.binding.result.BindingResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	public void getImageMethod(ImageService imageService, ImageDTO imageDTO, Model model) {
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
	}

	@RequestMapping("/downloadAndUpload/getImage")
	public String getImage(@Valid ImageDTO imageDTO, HttpServletRequest request, HttpServletResponse response, Model model) {
		getImageMethod(imageService, imageDTO, model);
		return "downloadAndUpload/downloadAndUpload";
	}

	@RequestMapping("/downloadAndUpload/getImage1")
	public String getImage1(@Valid ImageDTO imageDTO, HttpServletRequest request, HttpServletResponse response, Model model) {
		getImageMethod(imageService, imageDTO, model);
		return "downloadAndUpload/imageIndex";
	}

	@RequestMapping("/downloadAndUpload/imageIndex")
	public String imageIndex() {
		return "downloadAndUpload/imageIndex";
	}

	@RequestMapping("/downloadAndUpload/queryImages")
	public @ResponseBody Result queryImages(@Valid ImageDTO imageDTO, BindingResult bindingResult, Model model) {
		log.debug("绑定参数：{}", bindingResult);
		// 参数校验
		if (bindingResult.hasErrors()) {
			return BindingResultUtil.bindingResultHasError(bindingResult, model);
		}
		String uploadInfo = "上传信息";
		log.info("开始查询{}：", uploadInfo);
		log.info("查询参数信息：[{}]", imageDTO);
		String type = "0";
		Result<PageInfo<BookRecord>> result = (Result<PageInfo<BookRecord>>)imageService.listImageByDTO(imageDTO, type);
		if (result.isFail()) {
			return new Result(ResultEnum.FAIL);
		}
		// 上传信息
		// PageInfo<BookRecord> data = result.getData();
		return new Result<>(ResultEnum.SUCCESS, result.getData());
	}

	@RequestMapping("/downloadAndUpload/alterImageInfo")
	public String alterImageInfo(@Valid ImageDTO imageDTO, BindingResult bindingResult, Model model) {
		return null;
	}
}
