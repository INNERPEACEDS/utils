package com.wgb.utils.util.qrcode;

import com.wgb.utils.util.file.FileUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/12/27 15:13
 */
@Slf4j
public class QrCodeTest {
	public static void main(String[] args) {
		// 存放在二维码中的内容,支持424个汉字
		String path = "E:\\project\\照片（开通商户使用）\\QRCodeData.txt";
//		String path = "E:\\project\\照片（开通商户使用）\\or.docx";
		String text = FileUtil.readFile(path);
		// 嵌入二维码的图片路径
		String imgPath = "E:\\project\\照片（开通商户使用）\\side-feature.jpg";
		// 生成的二维码的路径及名称
		String destPath = "E:\\project\\照片（开通商户使用）\\jam.png";
		encode(text, imgPath, destPath);
		decode(destPath);

	}


	public static void encode(String text, String imgPath, String generatePath) {
		//生成二维码
		try {
			QRCodeUtil.encode(text, imgPath, generatePath, true);
		} catch (Exception e) {
			log.info("生成二维码异常", e);
		}
	}

	public static void decode(String path) {
		// 解析二维码
		String str = null;
		try {
			str = QRCodeUtil.decode(path);
		} catch (Exception e) {
			log.info("解析二维码异常", e);
		}
		// 打印出解析出的内容
		log.info("以下为二维码信息：");
		System.out.println(str);
	}
}
