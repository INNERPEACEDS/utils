package com.wgb.utils.service.image.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgb.utils.dao.oracle.ImageMapper;
import com.wgb.utils.entity.oracle.Image;
import com.wgb.utils.entity.oracle.dto.ImageDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.image.ImageService;
import com.wgb.utils.util.constants.controller.ControllerConstant;
import com.wgb.utils.util.date.DateUtil;
import com.wgb.utils.util.file.FastDFSClient;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
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

		Date date = new Date();
		Image image = new Image();
		image.setId(id);
		image.setSerial(serial);
		image.setName(name);
		image.setAddress(address);
		image.setUnionId(unionId);
		image.setCreateTime(date);
		image.setUpdateTime(date);
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


	@Override
	public Result<?> listImageByDTO(ImageDTO imageDTO, String type) {
		// 处理空数据（获取的值可能为"",数据库中查询无结果，应该处理数据）
		if (StringUtil.isBlankParam(imageDTO.getId())) {
			imageDTO.setId(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getSerial())) {
			imageDTO.setSerial(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getName())) {
			imageDTO.setName(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getAddress())) {
			imageDTO.setAddress(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getUnionId())) {
			imageDTO.setUnionId(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getStartCreateDate())) {
			imageDTO.setStartCreateDate(DateUtil.toStr(new Date(), DateUtil.DATE_FORMAT));
			// bookRecordDTO.setStartCreateDate(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getEndCreateDate())) {
			imageDTO.setEndCreateDate(DateUtil.toStr(new Date(), DateUtil.DATE_FORMAT));
			// bookRecordDTO.setEndCreateDate(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getStartCreateTime())) {
			imageDTO.setStartCreateTime(DateUtil.TIME_START);
			// bookRecordDTO.setStartCreateTime(null);
		}
		if (StringUtil.isBlankParam(imageDTO.getEndCreateTime())) {
			imageDTO.setEndCreateTime(DateUtil.TIME_END);
			// bookRecordDTO.setEndCreateTime(null);
		}
		log.info("startCreateDate-startCreateTime:[{}]-[{}];endCreateDate-endCreateTime[{}]-[{}]", imageDTO.getStartCreateDate(), imageDTO.getStartCreateTime(), imageDTO.getEndCreateDate(), imageDTO.getEndCreateTime());
		try {
			if (!StringUtil.isBlankParam(imageDTO.getStartCreateDate()) && !StringUtil.isBlankParam(imageDTO.getStartCreateTime())) {
				Date startTime = DateUtil.toDate(imageDTO.getStartCreateDate() + " " + imageDTO.getStartCreateTime(), DateUtil.FULL_FORMAT);
				imageDTO.setStartTime(startTime);
				// 通过查询范围查询，则不需要分段开始时间值
				imageDTO.setStartCreateDate(null);
				imageDTO.setStartCreateTime(null);
			} else {
				imageDTO.setStartTime(null);
			}

			if (!StringUtil.isBlankParam(imageDTO.getEndCreateDate()) && !StringUtil.isBlankParam(imageDTO.getEndCreateTime())) {
				Date endTime = DateUtil.toDate(imageDTO.getEndCreateDate() + " " + imageDTO.getEndCreateTime(), DateUtil.FULL_FORMAT);
				imageDTO.setEndTime(endTime);
				// 通过查询范围查询，则不需要分段结束时间值
				imageDTO.setEndCreateDate(null);
				imageDTO.setEndCreateTime(null);
			} else {
				imageDTO.setEndTime(null);
			}
			log.info("当前页：{}；每页大小：{}", imageDTO.getPageNum(), imageDTO.getPageSize());
			log.info("[开始时间范围：{}]-[结束时间范围：{}]", imageDTO.getStartTime(), imageDTO.getEndTime());
			// log.info("对应值比较[{}]-[{}]", bookRecordDTO.getStartTime(), bookRecordDTO.getEndTime() == "");
			// 分页
			if (ControllerConstant.SIGN_QUERY.equals(type)) {
				PageHelper.startPage(imageDTO.getPageNum(), imageDTO.getPageSize());
			}
			List<Image> list = imageMapper.listImageByDTO(imageDTO);
			int i = 0;
			for (Image image : list) {
				log.info("数据{}：{}", ++i, image);
			}
			if (ControllerConstant.SIGN_QUERY.equals(type)) {
				return new Result<>(ResultEnum.SUCCESS, new PageInfo<>(list));
			}
			return new Result<>(ResultEnum.SUCCESS, list);
		} catch (Exception e) {
			log.error("上传信息查询异常，查询条件{}", imageDTO, e);
		}
		return new Result<>(ResultEnum.FAIL);
	}
}
