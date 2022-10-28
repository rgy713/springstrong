/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.xspeeder.common.exception.XException;


public class FileUtil {
	
	private static final int BUFFER_SIZE = 4096;	// 4KB
	
	
	public static String ERR_FILE_UPLOAD			= "ERR_FILE_UPLOAD";
	
	public static String ERR_FILE_CREATE			= "ERR_FILE_CREATE";
	
	public static String generateFileName(String org_name) {
		String has_name = org_name;
		
		return has_name;
	}

	public static void saveStreamToFile(String		path, 
										InputStream	file) 
	throws XException {
		
		try {
			if (file == null) {
				throw new XException(ERR_FILE_UPLOAD);
			}
			
			int		read	= 0;
			byte[]	bytes	= new byte[BUFFER_SIZE];

			OutputStream out = new FileOutputStream(new File(path));
			while ((read = file.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			
		} catch (IOException ex) {
			deleteFile(path);
			ex.printStackTrace();
			throw new XException(ERR_FILE_CREATE);
		}
	}
	
	public static void deleteFile(String path) {
		File file = new File(path);
		
		if( file.exists() )
			file.delete();
	}
	
	public static boolean  makeDir(String strPath) 
	throws XException {

		try {

			File folder = new File(strPath);
			if (!folder.isDirectory()) {
				return folder.mkdirs();
			}
			else
				return true;
			
		} catch (SecurityException ex) {
			ex.printStackTrace();
			throw new XException(ERR_FILE_CREATE);
		}
	}

	public static boolean deleteDir(String strPath) {

		boolean result = true;
		File	folder = new File(strPath);

		try {
			File[] childs = null;

			if (folder.exists()) {

				if (folder.isDirectory()) {
					childs = folder.listFiles();

					for (File file : childs) {
						deleteDir(file.getAbsolutePath());
					}
				}

				result = folder.delete();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}

	public static Long getFolderSize(String srcPath) throws Exception {

		Long size = new Long(0);
		
		File srcFolder	= new File(srcPath);
		
		try {
			if (srcFolder.isDirectory()) {

				String[] children = srcFolder.list();
				for (int i = 0; i < children.length; i++) {
					size = size + getFolderSize(srcPath + File.separator + children[i]);
				}
			} 
			else {
				size = size + srcFolder.length();
			}
			
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
			
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} catch (XException e) {
			e.printStackTrace();
		}
		
		return size;
	}

	public static boolean copyDir(	String srcPath, 
									String destPath) {

		boolean result	= true;
		File srcFolder	= new File(srcPath);
		File destFolder	= new File(destPath);
		
		try {
			if (srcFolder.isDirectory()) {
				if (!destFolder.exists()) {
					FileUtil.makeDir(destPath);
				}

				String[] children = srcFolder.list();
				for (int i = 0; i < children.length; i++) {
					copyDir(srcPath + File.separator + children[i], 
							destPath + File.separator + children[i]);
				}
			}
			else {
				InputStream		in	= new FileInputStream(srcFolder);
				OutputStream	out	= new FileOutputStream(destFolder);

				// Copy the bits from instream to outstream
				byte[]	buf = new byte[BUFFER_SIZE];
				int		len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
			
		} catch(FileNotFoundException ex) {
			result = false;
			ex.printStackTrace();
		} catch (IOException ex) {
			result = false;
			ex.printStackTrace();
		} catch (XException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}

	public static boolean copyFile(	String srcFile, 
									String destFile) {

		boolean result	= true;
		
		if (!existFile(srcFile)) {
			return false;
		}
		
		File s_file	= new File(srcFile);
		File d_file	= new File(destFile);
		
		try {
			String destFolder = d_file.getParent();
			File d_folder = new File(destFolder);
			if (!d_folder.isDirectory()) {
				makeDir(destFolder);
			}
			
			InputStream		in	= new FileInputStream(s_file);
			OutputStream	out	= new FileOutputStream(d_file);

			// Copy the bits from instream to outstream
			byte[]	buf = new byte[BUFFER_SIZE];
			int		len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			
		} catch(FileNotFoundException ex) {
			result = false;
			ex.printStackTrace();
		} catch (IOException ex) {
			result = false;
			ex.printStackTrace();
		} catch (XException ex) {
			result = false;
			ex.printStackTrace();
		}
		
		return result;
	}

	public static boolean existFile(String file_path) {
		
		File file = new File(file_path);

		if (!file.exists()) {
			return false;
		}

		if (file.isDirectory()) {
			return false;
		}

		return true;
	}
	
	public static boolean existDirectory(String dir_path)
	{
		File file = new File(dir_path);

		if ( file.exists() && file.isDirectory()) {
			return true;
		}

		return false;
	}
	
	public static boolean verifyCheckSum(String file_path, 
										 String chk_sum) {
		
		boolean ret = false;
		
		if (StringUtil.isEmpty(chk_sum)) {
			return false;
		}
		
		String real_sum = generateCheckSum(file_path);
		
		if (real_sum.equals(chk_sum)) {
			ret = true;
		}

		return ret;
	}
	
	public static String generateCheckSum(String file_path) {
		
		String			chkSum	= null;
		MessageDigest	md		= null;
		FileInputStream	fis		= null;
		
		try {
			md	= MessageDigest.getInstance("MD5");
		    fis	= new FileInputStream(file_path);
	
		    byte[] dataBytes = new byte[BUFFER_SIZE];
	
		    int nread = 0;
		    while ((nread = fis.read(dataBytes)) != -1) {
		        md.update(dataBytes, 0, nread);
		    }
		    
		    byte[]			hash= md.digest();
		    StringBuffer	sb	= new StringBuffer();
		    for (int i = 0 ; i < hash.length ; i++) {
				sb.append(String.format("%02x", hash[i]));
			}
		    
		    chkSum = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return chkSum;
	}
	
	public static String savePart2File( String part_name, String file_path, String file_name ) throws Exception
	{
		HttpServletRequest req = ContextUtils.request();
		
		Part part = req.getPart(part_name);
		
		if( part == null )
			return null;
		
		String submit_filename = part.getSubmittedFileName();
		
		String ext = submit_filename.substring(submit_filename.lastIndexOf("."));
		file_name = file_name.concat(ext);
		
		FileUtils.copyInputStreamToFile(part.getInputStream(), new File(file_path + file_name));
		
		return file_name;
	}
	
	public static String getExtension( String filename ) 
	{
		if( StringUtil.isEmpty(filename) )
			return "";
		
		Integer pos = filename.lastIndexOf(".");
		
		if( pos > 0 )
			return filename.substring(pos).toLowerCase();
		else
			return "";
	}

	public static String getFileNameOnly( String filename ) 
	{
		Integer pos = filename.lastIndexOf(".");
		return filename.substring(0, pos);
	}
	
	public static boolean moveFile( String src, String dest )
	{
		File fsrc = new File(src);
		
		if( !fsrc.exists() )
			return false;
		
		File fdest = new File(dest);
		
		if( !fdest.exists() )
			return false;
		
		try {
			FileUtils.moveFile(fsrc, fdest);
			
		} catch (IOException e) {

			return false;
		}
		
		return true;
	}
	
	public static boolean renameFile( String src, String dest )
	{
		File oldfile =new File(src);
		File newfile =new File(dest);
		
		return oldfile.renameTo(newfile);
	}
	
	public static void saveImageFromUrl(String imageUrl, String destinationFile) throws IOException 
	{
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
}
