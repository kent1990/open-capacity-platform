package com.owen.oss.service;

import java.util.Map;

import com.owen.common.web.PageResult;
import com.owen.oss.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 作者 owen E-mail: 624191343@qq.com
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件service 目前仅支持阿里云oss,七牛云
*/
public interface FileService {

	FileInfo upload(MultipartFile file ) throws Exception;

	void delete(FileInfo fileInfo);
	
	FileInfo getById(String id);
	
	PageResult<FileInfo> findList(Map<String, Object> params);

	void unZip(String filePath, String descDir) throws RuntimeException ;
}
