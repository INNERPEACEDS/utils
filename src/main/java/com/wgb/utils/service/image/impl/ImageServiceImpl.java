package com.wgb.utils.service.image.impl;

import com.wgb.utils.dao.oracle.ImageMapper;
import com.wgb.utils.entity.oracle.Image;
import com.wgb.utils.entity.oracle.dto.ImageDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.image.ImageService;
import com.wgb.utils.util.file.FastDFSClient;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author INNERPEACE
 * @date 2019/4/23 15:50
 **/
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageMapper imageMapper;

	@Override
	public Result<String> saveImageInfo(ImageDTO imageDTO) {
		String id = UUID.randomUUID().toString();
		String serial = imageDTO.getSerial();
		String name = imageDTO.getName();
		String address = imageDTO.getAddress();
		String unionId = imageDTO.getUnionId();

		Image image = new Image();
		image.setId(id);
		image.setSerial(serial);
		image.setName(name);
		image.setAddress(address);
		image.setUnionId(unionId);
		try {
			imageMapper.insertSelective(image);
		} catch (Exception e) {
			log.error("存储图片异常", e);
			return new Result<>(ResultEnum.FAIL);
		}
		return new Result<>(ResultEnum.SUCCESS);
	}
	@Override
	public Result<String> getImageAddress(ImageDTO imageDTO) {

		Image image = imageMapper.selectByPrimaryKey(imageDTO.getId());
		if (image == null) {
			log.error("查询图片信息为空");
			return new Result<>(ResultEnum.FAIL);
		}
		String url = image.getAddress();
		String address = null;
		try {
			FastDFSClient fastDFSClient = new FastDFSClient();
			address = fastDFSClient.getUrl(url);
			log.info("url获取的地址：{}", address);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// address = FastDFSClient.getRealPath(url);
		if (address != null && address.length() > 100) {
			log.info("url:{},address的前100字符为：{}", url, address.substring(0, 100));
		} else {
			log.info("url:{},address:{}", url, address);
		}
		return new Result<>(ResultEnum.SUCCESS, address);
	}
}
