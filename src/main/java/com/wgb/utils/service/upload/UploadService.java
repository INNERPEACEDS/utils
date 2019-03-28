package com.wgb.utils.service.upload;
import com.wgb.utils.entity.result.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author INNERPEACE
 * @date 2018/12/19 17:28
 **/
public interface UploadService {
    Result<String> uploadFile(MultipartFile[] uploadFiles);
}
