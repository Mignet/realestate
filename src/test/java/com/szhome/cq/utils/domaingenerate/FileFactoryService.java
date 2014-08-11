package com.szhome.cq.utils.domaingenerate;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 生成java文件
 * 
 * @author Mignet
 */

@Scope("prototype")
@Component
public class FileFactoryService {

	private static final Log LOGGER = LogFactory
			.getLog(FileFactoryService.class);

	// 输出文件
	public void createFile(List<IFormatedTableBean> tableBean) {
		Iterator<IFormatedTableBean> ite = tableBean.iterator();

		URI uri = null;
		try {
			uri = new URI(FileFactoryService.getWebOutputDomainPath());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		File file;
		if (uri != null) {
			file = new File(uri);
			if (file != null && !file.exists()) {
				try {
					file.mkdir();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
			}
		}

		while (ite.hasNext()) {
			IFormatedTableBean bean = ite.next();
			FileOutputStream fos = null;

			try {
				uri = new URI(FileFactoryService.getWebOutputDomainPath()
						+ bean.getFileSavePath());
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
			file = new File(uri);

			byte[] by = null;
			try {
				fos = new FileOutputStream(file);
				by = bean.getOutPutString().getBytes();
				fos.write(by);
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getWebOutputDomainPath() {
		StringBuilder sb = new StringBuilder();
		String paths = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String[] path = paths.split("/");
		for (int i = 0; i < path.length - 2; i++) {
			sb.append(path[i] + "/");
		}

		return sb.append("output_domain/").toString();
	}
}

