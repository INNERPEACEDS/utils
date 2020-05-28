package com.wgb.utils.controller.qrcode;

import com.google.zxing.WriterException;
import com.wgb.utils.util.qrcode.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author INNERPEACE
 * @date 2019/12/27 16:25
 */
@Controller
@RequestMapping("/qrCode")
@Slf4j
public class QRCodeController {
	@RequestMapping("/page")
	public String qrcode() {
		log.info("进入二维码页面");
		return "RQCode/qrcode";
	}

	@RequestMapping(value="/qrimage")
	public ResponseEntity<byte[]> getQRImage(@RequestParam("insertImage") MultipartFile multipartFile, HttpServletRequest request, Model model) {
		String qrImageData = request.getParameter("qrImageData");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] qrcode = null;
		try {
			BufferedImage image = QRCodeUtil.encode(qrImageData, multipartFile.getInputStream(), true);
			ImageIO.write(image, "PNG", outputStream);
			qrcode = outputStream.toByteArray();
			// qrcode = QRCodeGenerator.getQRCodeImage(qrImageData, 360, 360);
		} catch (WriterException e) {
			log.error("Could not generate QR Code, WriterException : {}" ,e.getMessage());
		} catch (IOException e) {
			log.error("Could not generate QR Code, IOException : {}", e.getMessage());
		} catch (Exception e) {
			log.error("Could not generate QR Code, Exception : {}", e.getMessage());
		}
		// Set headers
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		model.addAttribute("result", "0000");
		model.addAttribute("allResult", "生产二维码成功");
		return new ResponseEntity<> (qrcode, headers, HttpStatus.CREATED);
	}

	@RequestMapping("/parse")
	public String parse(@RequestParam("file") MultipartFile multipartFile,  Model model) {
		log.info("二维码解析");
		String qrCodeData = null;
		try {
			qrCodeData = QRCodeUtil.decodeMultipartFile(multipartFile);
		} catch (Exception e) {
			log.info("解析二维码异常", e);
		}
		model.addAttribute("parseQRCodeData", qrCodeData);
		model.addAttribute("allResult", "解析二维码成功");
		return "RQCode/qrcode";
	}
}
