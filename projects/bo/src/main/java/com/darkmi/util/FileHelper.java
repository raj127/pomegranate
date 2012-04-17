package com.darkmi.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileHelper {
	
	/**
	 * 读取文件内容.
	 * @param fileName
	 * @param encoding
	 * @return
	 */
	public static String read(String fileName, String encoding) {
		StringBuffer fileContent = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis, encoding);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				fileContent.append(line);
				fileContent.append(System.getProperty("line.separator"));
			}
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileContent.toString();
	}

	/**
	 * 写入文件内容.
	 * @param fileContent
	 * @param fileName
	 * @param encoding
	 */
	public static void write(String fileContent, String fileName, String encoding) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
			osw.write(fileContent);
			osw.flush();
			osw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileHelper.write("Hello, World!", "D:\\test.txt", "UTF-8");

	}
}
