package cn.gd.lucas.API;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.solr.common.util.ContentStreamBase;

public class MyContentStream extends ContentStreamBase {
	InputStream inputStream = null;

	public MyContentStream(String input) throws UnsupportedEncodingException {
		inputStream = new ByteArrayInputStream(input.getBytes(DEFAULT_CHARSET));
	}

	public MyContentStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getStream() throws IOException {
		// TODO Auto-generated method stub
		return inputStream;
	}

}
