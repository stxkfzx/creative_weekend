package xin.stxkfzx.weekend.upload.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xin.stxkfzx.weekend.upload.service.UploadService;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@RestController
public class UploadController {
	private final UploadService uploadService;

	public UploadController(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	/**
	 * 图片上传controller
	 * @param file file
	 * @return 图片的网络地址
	 * @author VicterTian
	 * @date 2019-04-11
	 */
	@PostMapping("image")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
		// TODO: 2019/4/11 修改返回类型
		return ResponseEntity.ok(uploadService.uploadImage(file));
	}
}
