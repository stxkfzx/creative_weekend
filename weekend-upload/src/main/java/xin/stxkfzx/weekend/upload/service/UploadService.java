package xin.stxkfzx.weekend.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xin.stxkfzx.weekend.common.enums.ExceptionEnum;
import xin.stxkfzx.weekend.common.exception.CheckException;
import xin.stxkfzx.weekend.upload.config.UploadProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
	private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
	private final UploadProperties uploadProperties;
	private final FastFileStorageClient fastFileStorageClient;

	public UploadService(UploadProperties uploadProperties, FastFileStorageClient fastFileStorageClient) {
		this.uploadProperties = uploadProperties;
		this.fastFileStorageClient = fastFileStorageClient;
	}

	/**
	 * 上传图片服务
	 *
	 * @param file file
	 * @return 图片的网络地址
	 * @author VicterTian
	 * @date 2019-04-11
	 */
	public String uploadImage(MultipartFile file) {
		// 获取文件后缀名
		String contentType = file.getContentType();

		// 判断文件是否是可支持上传类型
		if (!uploadProperties.getAllowTypes().contains(contentType)) {
		    logger.error("上传文件类型错误");
			throw new CheckException(ExceptionEnum.INVALID_FILE_TYPE);
		}

		try {
			BufferedImage read = ImageIO.read(file.getInputStream());
			if (read == null) {
                logger.error("上传文件类型错误");
                throw new CheckException(ExceptionEnum.INVALID_FILE_TYPE);
			}
			String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
			// 将文件上传至FastDFS服务器
			StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
			// 返回正常路径
			String path = uploadProperties.getBaseUrl() + storePath.getFullPath();
			System.out.println(path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件上传失败", e);
            throw new CheckException(ExceptionEnum.UPLOAD_FILE_ERROR);
		}
	}
}
