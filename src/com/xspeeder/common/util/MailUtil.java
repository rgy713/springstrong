
package com.xspeeder.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MailUtil {

    public static String FROM_ADDRESS 	= null;
    public static String PASSWORD		= null;
    public static String FROM_NAME		= null;
    public static String SMTP_HOST		= null;

	
	public static boolean sendSMS(String mobile, String message)
			throws MalformedURLException, UnsupportedEncodingException 
	{
		
		
		String send_time = ""; // DateTimeUtil.format( DateTimeUtil.utcNow(), "yyyyMMddHHmmss" );
		String inputLine = "";
		String Content= message;
		int value = -2;
		// DataOutputStream out = null;
		// InputStream in = null;

		String CorpID = "TCLKJ04288";// 账户名
		String Pwd = "849600@";// 密码
		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "GB2312");// 发送内容----GBK:GB2312

		String strUrl = "http://inolink.com/WS/BatchSend.aspx";
		String param = "CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + mobile
				+ "&Content=" + send_content + "&Cell=&SendTime=" + send_time;

		try {

			inputLine = sendPost(strUrl, param);
			// inputLine = "1";
			// System.out.println("开始发送短信手机号码为 ：" + mobile);

			value = new Integer(inputLine).intValue();

		} catch (Exception e) {

			// System.out.println("网络异常,发送短信失败！");
			value = -2;

		}

		// System.out.println(String.format("返回值：%d", value));

		if( value >= 0 )
			return true;
		else 
			return false;
		
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static boolean sendMail(String to, String subject, String message) {
		
		boolean ret = false;
		
		try {
			
			// convert CRLF to <BR> for HTML
			message = message.replaceAll("(\r\n|\n)", "<br />");
			
			
			MailThread mt = new MailThread(SMTP_HOST, FROM_ADDRESS, FROM_NAME, PASSWORD, to, subject, message);
			mt.start();
			
			ret = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}
	

}
