
package com.xspeeder.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.xspeeder.common.exception.XException;

public class ZipUtil {
	
	private static final int BUFFER_SIZE = 4096;	// 4KB

	public static void compressFiles(List<File>	list_files, 
									String		zipFile_path)
	throws FileNotFoundException, IOException {

		if (list_files == null || list_files.size() < 1) {
			return;
		}
		
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile_path));

		for (File file : list_files) {
			if (file.isDirectory()) {
				addFolderToZip(file, file.getName(), zos);
			} else {
				addFileToZip(file, zos);
			}
		}

		zos.flush();
		zos.close();
	}

	private static void addFolderToZip(	File			folder, 
										String			parentFolder,
										ZipOutputStream	zos) 
	throws FileNotFoundException, IOException {
		
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				addFolderToZip(file, parentFolder + File.separator + file.getName(), zos);
				continue;
			}

			zos.putNextEntry(new ZipEntry(parentFolder + File.separator + file.getName()));

			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

			byte[]	bytes	= new byte[BUFFER_SIZE];
			int		read	= 0;
			while ((read = bis.read(bytes)) != -1) {
				zos.write(bytes, 0, read);
			}
			bis.close();

			zos.closeEntry();
		}
	}

	private static void addFileToZip(File			file, 
									ZipOutputStream	zos)
	throws FileNotFoundException, IOException {
		
		zos.putNextEntry(new ZipEntry(file.getName()));

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

		byte[] bytes = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = bis.read(bytes)) != -1) {
			zos.write(bytes, 0, read);
		}
		bis.close();

		zos.closeEntry();
	}

	public static boolean decompressResourceFile(String				zipFile_path, 
												String				dest_dir, 
												Map<String, String>	res_list) {
		
		boolean			ret		= false;
		ZipInputStream	zip_in	= null;
		
		try {
			File destDir = new File(dest_dir);
			if (!destDir.exists()) {
				FileUtil.makeDir(dest_dir);
			}

			zip_in = new ZipInputStream(new FileInputStream(zipFile_path));
			ZipEntry entry = zip_in.getNextEntry();

			if (entry == null) {
				return ret;
			}
			
			String file_path		= null;
			String res_name			= null;
			String save_name		= null;
			String save_path		= null;
			int 	pos				= 0;
			while (entry != null) {

				if (entry.isDirectory()) {
					file_path = dest_dir + File.separator + entry.getName();
					FileUtil.makeDir(file_path);
				} else {
					pos			= entry.getName().lastIndexOf("/");
					res_name	= entry.getName().substring(pos+1);				
					save_name	= res_list.get(res_name);						
					
					
					if (save_name == null) {
						zip_in.closeEntry();
						entry = zip_in.getNextEntry();
						continue;
					}
					
					save_path	= entry.getName().substring(0, pos+1) + save_name;	// 상대적인 보관경로를 얻는다.
					
					file_path	= dest_dir + File.separator + save_path;			// 절대보관경로를 얻는다.

					extractFile(zip_in, file_path);
				}

				zip_in.closeEntry();
				entry = zip_in.getNextEntry();
			}
			zip_in.close();
			
			ret = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XException e) {
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			
			if (!ret) {
				FileUtil.deleteDir(dest_dir);
			}
		}
		
		return ret;
	}

	public static String decompressScreenshotFile(	String	prefix, 
													String	zipFile_path, 
													String	dest_dir) {
		
		String			file_list	= "";
		ZipInputStream	zip_in		= null;
		
		try {
			File destDir = new File(dest_dir);
			if (!destDir.exists()) {
				FileUtil.makeDir(dest_dir);
			}

			zip_in = new ZipInputStream(new FileInputStream(zipFile_path));
			ZipEntry entry = zip_in.getNextEntry();

			if (entry == null) {
				return file_list;
			}
			
			String file_name	= null;
			String file_ext		= null;
			int		i			= 0;
			int		pos			= 0;
			while (entry != null) {

				pos = entry.getName().lastIndexOf(".");
				file_ext = "";
				if (pos > -1) {
					file_ext = entry.getName().substring(pos + 1);
				}
				
				file_name = FileUtil.generateFileName(prefix + "_" + i) + "." + file_ext;
				extractFile(zip_in, dest_dir + File.separator + file_name);

				zip_in.closeEntry();
				entry = zip_in.getNextEntry();
				i++;
				
				file_list += "," + file_name;
			}
			zip_in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XException e) {
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return file_list;
	}

	public static ArrayList<String> decompressScreenshotFile(	String	prefix, 
													InputStream	zip_stream, 
													String	dest_dir,
													String small_dir,
													Integer small_w,
													Integer small_h) {
		
		ArrayList<String> file_list = new ArrayList<String>();
		ZipInputStream	zip_in		= null;
		
		try {
			File destDir = new File(dest_dir);
			if (!destDir.exists()) {
				FileUtil.makeDir(dest_dir);
			}
			
			File smallDir = new File(small_dir);
			if (!smallDir.exists()) {
				FileUtil.makeDir(small_dir);
			}

			zip_in = new ZipInputStream(zip_stream);
			ZipEntry entry = zip_in.getNextEntry();

			if (entry == null) {
				return null;
			}
			
			String file_name	= null;
			String file_ext		= null;
			int		i			= 0;
			int		pos			= 0;
			while (entry != null) {

				pos = entry.getName().lastIndexOf(".");
				file_ext = "";
				if (pos > -1) {
					file_ext = entry.getName().substring(pos + 1);
				}
				
				file_name = FileUtil.generateFileName(prefix + "_" + i) + "." + file_ext;
				file_name = file_name.toLowerCase();
				
				
				if (FileUtil.existFile(dest_dir + File.separator + file_name))
				{
					FileUtil.renameFile(dest_dir + File.separator + file_name, dest_dir + File.separator + file_name + "_old");
					FileUtil.renameFile(small_dir + file_name, small_dir + file_name + "_old");
				}
				
				extractFile(zip_in, dest_dir + File.separator + file_name);
				
				ImageUtil.resizeImage(dest_dir + File.separator + file_name, small_dir + file_name, small_w, small_h);

				zip_in.closeEntry();
				entry = zip_in.getNextEntry();
				i++;
				
				file_list.add( file_name );
			}
			zip_in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XException e) {
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return file_list;
	}
	
	public static Long getDecompressedSize( InputStream zip_stream )
	{
		Long decompress_size = new Long(0);
		
		ZipInputStream	zip_in	= null;
		
		try {
			zip_in = new ZipInputStream(zip_stream);
			ZipEntry entry = zip_in.getNextEntry();

			if (entry == null) {
				return decompress_size;
			}
			
			while (entry != null) {
				
				decompress_size = decompress_size + entry.getSize();
				
				zip_in.closeEntry();
				entry = zip_in.getNextEntry();
			}
			zip_in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return decompress_size;
	}

	public static Long decompressFile(InputStream	zip_stream, 
										String	dest_dir) {
		
		boolean ret = false;
		
		Long decompress_size = new Long(0);
		
		ZipInputStream	zip_in	= null;
		
		try {
			File destDir = new File(dest_dir);
			if (!destDir.exists()) {
				FileUtil.makeDir(dest_dir);
			}

			zip_in = new ZipInputStream(zip_stream);
			ZipEntry entry = zip_in.getNextEntry();

			if (entry == null) {
				return decompress_size;
			}
			
			String entry_path = null;
			while (entry != null) {
				entry_path = dest_dir + File.separator + entry.getName();
				
				if (entry.isDirectory()) {
					FileUtil.makeDir(entry_path);
				} else {
					extractFile(zip_in, entry_path);
				}
				
				File tmp_file = new File(entry_path);
				decompress_size = decompress_size + tmp_file.length();
				
				zip_in.closeEntry();
				
				entry = zip_in.getNextEntry();
			}
			zip_in.close();
			
			ret = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XException e) {
			e.printStackTrace();
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			if (!ret) {
				FileUtil.deleteDir(dest_dir);
			}
		}
		
		return decompress_size;
	}

	public static boolean decompressFile(String	zipFile_path, 
										String	dest_dir) throws Exception {
		
		boolean			ret		= false;
		ZipInputStream	zip_in	= null;
		
		try {
			File destDir = new File(dest_dir);
			if (!destDir.exists()) {
				FileUtil.makeDir(dest_dir);
			}

			zip_in = new ZipInputStream(new FileInputStream(zipFile_path));
			ZipEntry entry = zip_in.getNextEntry();


			if (entry == null) {
				return ret;
			}
			
			String entry_path = null;
			while (entry != null) {
				entry_path = dest_dir + File.separator + entry.getName();

				if (entry.isDirectory()) {
					FileUtil.makeDir(entry_path);
				} else {
					extractFile(zip_in, entry_path);
				}
				zip_in.closeEntry();
				entry = zip_in.getNextEntry();
			}
			zip_in.close();
			
			ret = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (XException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				zip_in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		
			if (!ret) {
				FileUtil.deleteDir(dest_dir);
			}
		}
		
		return ret;
	}

	private static void extractFile(ZipInputStream	zip_in, 
									String			file_path)
	throws IOException {
		
		BufferedOutputStream bos = null;
		
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file_path));
			
			byte[] bytesIn = new byte[BUFFER_SIZE];
			int read = 0;
			
			while ((read = zip_in.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
			bos.close();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
