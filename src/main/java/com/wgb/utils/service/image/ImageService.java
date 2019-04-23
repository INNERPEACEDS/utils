package com.wgb.utils.service.image;

import com.wgb.utils.entity.oracle.dto.ImageDTO;
import com.wgb.utils.entity.result.Result;

/**
 * @author INNERPEACE
 * @date 2019/4/23 15:45
 **/
public interface ImageService {
	Result<String> saveImageInfo(ImageDTO imageDTO);

	Result<String> getImageAddress(ImageDTO imageDTO);
}
