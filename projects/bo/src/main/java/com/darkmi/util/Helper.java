package com.darkmi.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.common.tools.DateUtil;

public class Helper {
	
	private static final int BUFFER_SIZE = 16 * 1024;
	private static Logger logger = LoggerFactory.getLogger(Helper.class);

	public String getRunTime(Integer runTime) {

		return DateUtil.formatMs(runTime * 1000);
	}
	
	//将文件流存在后台的本地磁盘
	public static void uploadFile(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}
		} catch (Exception e) {
			logger.error("文件上传过程中失败", e);
			throw new ServiceException("文件上传过程中失败");
		}
	}
}
