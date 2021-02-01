package com.wenlincheng.pika.common.core.util;

import com.wenlincheng.pika.common.core.enums.EncodingEnum;
import com.wenlincheng.pika.common.core.enums.TimeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.LRUMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 网络以及file 以及流相关的都放在这里
 * @author zhuliming
 *
 */
@Slf4j
public class IoUtil {
	
	private static final int BUFFER_SIZE = 1024;
	/**
	 * 本机ip
	 */
	private static String localIP = null;

	/**
	 * 每个IP的图片下载次数监控 每小时的下载次数的控制
	 */
	private static final LRUMap imgDownloadTimeCachePerHour = new LRUMap(10000);

	/**
	 * 读取流  转成string
	 *
	 * @param inputStream 输入流
	 * @param encoding	  编码
	 * @throws Exception
	 * @return java.lang.String
	 */
	public static final String readTextFromHttpURL(final InputStream inputStream, final EncodingEnum encoding) throws Exception{
		ByteArrayOutputStream baos = null;
		try{
			baos = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			return new String(baos.toByteArray(), encoding.type);
		}finally{
			IoUtil.closeStreamWithoutException(inputStream, baos);
		}
	}

	/**
	 * 读取图片二进制内容
	 *
	 * @param url		链接
	 * @param tryTimes	尝试次数
	 * @throws
	 * @return byte[]
	 */
	public static final byte[] readFromHttpURL(final String url, final int tryTimes)  {
		int times = 0;
		do{
			times ++;
			final CloseableHttpClient client = HttpClients.createDefault();
			try{
				final HttpGet get = new HttpGet(url);
				final HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == 200)
				{
					//得到实体
					final HttpEntity entity = response.getEntity();
					return EntityUtils.toByteArray(entity);
				}
			}catch(Exception ex){
				log.error("Failed read http url, url=" + url + " , ex=" + ex.getMessage(), ex);
			}finally{
				try {
					client.close();
				} catch (IOException ex) {
					log.error("Failed to close httpClient when read http url, url="+url+" , ex=" + ex.getMessage() , ex);
				}
			}
			ThreadUtil.sleepNoException(TimeConstant.Miliseconds.TwoSeconds.miliseconds * times);
		}while(tryTimes > times);
		return null;
	}

	/**
	 * 关闭流
	 *
	 * @param in	输入流
	 * @param out	输出流
	 */
	public static final void closeStreamWithoutException(final InputStream in, final OutputStream out){
		if(in != null){
			try {
				in.close();
			} catch (IOException ex) {
				log.error("Fail to close InputStream: ex="+ex.getMessage(), ex);
			}
		}
		if(out != null){
			try {
				out.close();
			} catch (IOException ex) {
				log.error("Fail to close OutputStream: ex="+ex.getMessage(), ex);
			}
		}
	}
}