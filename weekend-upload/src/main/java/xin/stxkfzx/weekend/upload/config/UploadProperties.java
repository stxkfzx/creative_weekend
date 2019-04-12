package xin.stxkfzx.weekend.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 上传属性相关
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/11
 */
@Component
@ConfigurationProperties(prefix = "weekend.upload")
public class UploadProperties {
	private String baseUrl;
	private List<String> allowTypes;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<String> getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(List<String> allowTypes) {
		this.allowTypes = allowTypes;
	}
}
