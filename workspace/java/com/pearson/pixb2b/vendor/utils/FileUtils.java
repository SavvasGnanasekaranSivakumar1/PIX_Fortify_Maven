/**
 * Copyright 2009 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	FileUtils.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/**
 * FileUtils is a utility class that provides the utility
 * methods for directories & files handling.
 * 
 * @author Yogesh Tyagi
 */
public class FileUtils {
	/**
	 * This method parses and returns the file name only from 
	 * fully qualified path+file name
	 * 
	 * @param fileNameWithDirPath
	 * @return String
	 */
	public String getFileName(String fileNameWithDirPath) {
		String fileNameStr	= null;
		try {
			int pos1 = fileNameWithDirPath.lastIndexOf("/");
			int pos2 = fileNameWithDirPath.lastIndexOf("\\");
			
			if (pos2 > pos1) 
				pos1 = pos2;
			pos2 = fileNameWithDirPath.lastIndexOf(":");
			if (pos2 > pos1) 
				pos1 = pos2;
			fileNameStr = fileNameWithDirPath.substring(pos1+1);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		}		
		return fileNameStr;
	}
   /**
    * This method remove the file extension from given file name
    * 
    * @param fileNameWithExtn Incoming file name
    * @return <code>String</code> representing the filename without its extension.
    */  
   public String removeFileExtension(String fileNameWithExtn) {
       String fileNameStr = null;       
       try {
		   int index = fileNameWithExtn.lastIndexOf('.');
		   if (index > 0 &&  index < fileNameWithExtn.length() - 1)
		       fileNameStr = fileNameWithExtn.substring(0,index);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		}
       return fileNameStr;
   }  
   /**
    * Used to extract and return the extension of a given file.
    * @param fileNameStr Incoming file to get the extension of
    * @return <code>String</code> representing the extension of the incoming file.
    */  
   public String getFileExtension(String fileNameStr) {
       	String fileExtnStr = null;
		try {
		   int index = fileNameStr.lastIndexOf('.');
		   if (index > 0 &&  index < fileNameStr.length() - 1)
			   fileExtnStr = fileNameStr.substring(index,fileNameStr.length());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		}
		return fileExtnStr;       
   }
   /**
    * This method will return the file size in KB of given file Name with complete directory path.
    * @param absoluteFileName The file name with directory path.
    * @return long file size.
    */
   public String getFileSizeKB(String absoluteFileName){
	   File file = null;
	   String fileSizeKB = null;
	   try {
		   file = new File(absoluteFileName);
		   //String fileSizeKB = ((file.length()/1024)+1) + " KB";
		   fileSizeKB = ((file.length()/1024)+1)+"";
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
		}
		return fileSizeKB;
   }
   /**
    * This method return the file modified date time.
    * @param absoluteFileName The file name with directory path.
    * @return String file modified date time.
    */
   public String getFileModifiedDateTime(String absoluteFileName,String dateTimeFormat){
	   File file = null;
	   Timestamp fTimeStamp = null;
	   String fModifiedDateTime = null;
		try {
			file = new File(absoluteFileName);
			fTimeStamp = new Timestamp(file.lastModified());
			fModifiedDateTime = DateUtils.formatTimestamp(fTimeStamp,dateTimeFormat);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
			fTimeStamp = null;
		}
		return fModifiedDateTime;
   }
   /** 
	 * This Method delete all files & subdirectories from given directory & finally
	 * delete this directory. If a deletion fails, the method stops attempting 
	 * to delete and returns false.
	 * @return true if all deletions were successful.
	 */
	public boolean deleteDir(File dir) {
		String[] children = null;
		try {
			if(dir.isDirectory()) {
				children = dir.list();
				for(int i=0; i<children.length; i++) {
					boolean success = deleteDir(new File(dir,children[i]));
					if (!success) 
						return false;
				}
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			children = null;
		}
		//The directory is now empty so can be deleted now
		return dir.delete();
	}
	/**
	 * Deletes a file if it exists.
	 * @param fileNameWithDirPath
	 */
	public boolean deleteFile (String fileNameWithDirPath) {
		File file = null;
		boolean status = false;
		try {
			file = new File(fileNameWithDirPath);
			status = file.delete();
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
		}
		return status;
	}
	/**
	 * Move a file from one directory to another
	 * @param srcFileWithDir
	 * @param destDir
	 * @return boolean
	 */
	public boolean moveFile (String srcFileWithDir, String destDir) {
		File srcFile = null;
		File destLoc = null;
		String destFileWithDir = null;
		boolean status = false;
		
		try {
			srcFile = new File(srcFileWithDir);
			destLoc = new File(destDir);

			destFileWithDir = destDir+File.separatorChar+getFileName(srcFileWithDir);
			if(new File(destFileWithDir).exists())
				deleteFile(destFileWithDir);
			
			status = srcFile.renameTo(new File(destLoc, srcFile.getName()));
			if(!status)
				B2BLogger.info("FileUtils.moveFile() - File Not Moved from "+srcFileWithDir+" to "+destDir);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			srcFile = null;
			destLoc = null;
			destFileWithDir = null;
		}
		return status;
	}
    /**
     * This method copy source file to destination file
	 * @param srcFileWithDir
	 * @param destFileWithDir
	 */
	public void copyFile(String srcFileWithDir, String destFileWithDir) {
		File sSrcFile 			= null;
		File sDestFile 			= null;
		InputStream inStream 	= null;
		OutputStream outStream 	= null;
		
		try {
		   sSrcFile = new File(srcFileWithDir);
		   sDestFile = new File(destFileWithDir);
		   inStream = new FileInputStream(sSrcFile);
		   outStream = new FileOutputStream(sDestFile);
		   
		   byte[] buf = new byte[1024];
		   int len;
		   while ((len = inStream.read(buf)) > 0) {
			   outStream.write(buf, 0, len);
		   }
		   
		   outStream.flush();		   
		   inStream.close();
		   outStream.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			sSrcFile 	= null;
			sDestFile 	= null;
			inStream 	= null;
			outStream 	= null;
		}
   	}	
	/**
	 * @param originalName
	 * @param newName
	 */
	public void renameFile(String originalName, String newName){
		File file = null;
	    try {
			file = new File(originalName);
			file.renameTo(new File(newName));
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
		}
	}	
	/**
     * This method return the list of file names from given directory & file extension.
	 * @param dirName
	 * @param filePrefixSuffix
	 * @param fileExt
	 * @return ArrayList file list
	 */
	public ArrayList getDirFileNameList(String dirName, String filePrefixSuffix, String fileExt){
		ArrayList filesList 	= null;
		File fileDir 			= null;
		String[] filesInDir 	= null;
		String sFileName 		= null;
		String fileNameWithPath = null;
		String sFileExt 		= null;
		
		try {					   
			fileDir = new File(dirName);
			filesInDir = fileDir.list();			   
			if(filesInDir != null && filesInDir.length > 0) {
				filesList = new ArrayList();
				for(int i=0; i<filesInDir.length; i++){
				   sFileName = filesInDir[i];//Get the name of file or directory
				   if(!new File(sFileName).isDirectory()){				   
					   fileNameWithPath = dirName+File.separatorChar+sFileName;
					   sFileExt = getFileExtension(sFileName);				   
					   if(sFileExt.equalsIgnoreCase(fileExt)){					   
						   if(!filesList.contains(fileNameWithPath)){
							   if(null != filePrefixSuffix && !"".equals(filePrefixSuffix)){
								   if(fileNameWithPath.indexOf(filePrefixSuffix) != -1)
									   filesList.add(fileNameWithPath);
							   }else{
								   filesList.add(fileNameWithPath);
							   }
						   }
						}/*else{
							filesList.add(fileNameWithPath);
						}*/
					}
				}
			}else{
				B2BLogger.info("FileUtils.getDirFileNameList() - No file found in directory "+dirName);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			fileDir 	= null;
			filesInDir 	= null;
			sFileName 	= null;
			fileNameWithPath = null;
			sFileExt 	= null;
		}
		return filesList;
	}
	/**
	 * This method read files data & returns a String object with file data.
	 * @param fileNameWithPath
	 * @return String
	 */
	public String readFile(String fileNameWithPath) {		
		File file = null;
		FileInputStream fileInputStream = null;
		DataInputStream dataInputStream = null;
		InputStreamReader inStreamReader= null;
		BufferedReader bufferReader 	= null; 
		StringBuffer sbFileData = null;
		
		try {
			file = new File(fileNameWithPath);
			fileInputStream = new FileInputStream(file);
			dataInputStream = new DataInputStream(fileInputStream);		
			inStreamReader = new InputStreamReader(dataInputStream);		
			bufferReader = new BufferedReader(inStreamReader);
			
			sbFileData = new StringBuffer();
			String line = null;
			while((line = bufferReader.readLine()) != null)
				sbFileData.append(line.trim());

			bufferReader.close();
			inStreamReader.close();			
			dataInputStream.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
			fileInputStream = null;
			dataInputStream = null;
			inStreamReader	= null;
			bufferReader 	= null; 
		}
		
		return sbFileData.toString();
	}
	/**
	 * This method read files data & returns a String object with file data.
	 * @param fileNameWithPath
	 * @param fileDir
	 * @param fileName
	 * @return String
	 */
	public String readFile(String fileNameWithPath, String fileDir, String fileName) {
		FileReader fileReader 		= null;
		BufferedReader bufferReader = null;
		StringBuffer sbFileData 	= null;
		
		try {					
			if(fileNameWithPath != null & new File(fileNameWithPath).isFile())
				fileReader = new FileReader(fileNameWithPath);
			else
				fileReader = new FileReader(fileDir+fileName);
			bufferReader = new BufferedReader(fileReader);
			
			sbFileData = new StringBuffer();
			String line = null;
			while((line = bufferReader.readLine()) != null) 
				sbFileData.append(line.trim());
			
			bufferReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			fileReader 	= null;
			bufferReader= null;
		}		
		return sbFileData.toString();
	}
	/**
	 * This method write string data to a file in specified directory.
	 * @param fileNameWithPath
	 * @param strFileData
	 */
	public void writeFile(String fileNameWithPath, String strFileData) {
		File file = null;
		FileOutputStream fileOutputStream = null;
		DataOutputStream dataOutputStream = null;
		OutputStreamWriter outStreamWriter= null;
		BufferedWriter bufferWriter 	  = null; 
			
		try {
			file = new File(fileNameWithPath);
			fileOutputStream = new FileOutputStream(file);
			dataOutputStream = new DataOutputStream(fileOutputStream);
			outStreamWriter = new OutputStreamWriter(dataOutputStream,"UTF-8");            
			bufferWriter = new BufferedWriter(outStreamWriter);
			bufferWriter.write(strFileData);
			
			bufferWriter.flush();
			outStreamWriter.flush();
			dataOutputStream.flush();
			fileOutputStream.flush();
			
			bufferWriter.close();		
			outStreamWriter.close();	
			dataOutputStream.close();		
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			file = null;
			fileOutputStream = null;
			dataOutputStream = null;
			outStreamWriter  = null;
			bufferWriter 	 = null; 
		}
     }
	/**
	 * This method write string data to a file in specified directory.
	 * @param feedDirPath
	 * @param fileName
	 * @param strFileData
	 */
	public void writeFile(String feedDirPath, String fileName, String strFileData) {
		File fileDir = null;
		String fileNameWithPath = null;
			
		try {
			fileDir = new File(feedDirPath);
			if(!fileDir.exists())
				fileDir.mkdirs();		
			fileNameWithPath = feedDirPath + File.separatorChar + fileName;
			writeFile(fileNameWithPath, strFileData);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			fileDir = null;
			fileNameWithPath = null;
		}
    }
	/**
	 * This method make the zip(.zip) file with given name zipFileName
	 * @param filesList
	 * @param zipFileName
	 * @param needFilesAtRoot
	 * @return boolean status
	 */
	public boolean makeZIP(ArrayList filesList,String zipFileName, boolean needFilesAtRoot) {
		boolean success = false;
		ZipOutputStream gipOutputStream = null;
		String inFileName 				= null;
		FileInputStream fileInputStream = null;

		try {			
			if(filesList != null && filesList.size() > 0){
				byte[] buf = new byte[1024];				     			
				gipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
				gipOutputStream.setLevel(9);
				gipOutputStream.setMethod(ZipOutputStream.DEFLATED);
				for(int i=0; i < filesList.size(); i++){
					inFileName = (String)filesList.get(i);//file name
					fileInputStream = new FileInputStream(new File(inFileName));        			
					if(needFilesAtRoot){
						int idx = inFileName.lastIndexOf(File.separatorChar);
						inFileName = inFileName.substring(idx+1,inFileName.length());
					}        			
					gipOutputStream.putNextEntry(new ZipEntry(inFileName));
					int len;
					while((len = fileInputStream.read(buf)) > 0) {
						gipOutputStream.write(buf, 0, len);
					}
					gipOutputStream.closeEntry();
				}
				gipOutputStream.flush();
				
				fileInputStream.close();
				gipOutputStream.close();
				success = true;
			}
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			gipOutputStream = null;
			inFileName 		= null;
			fileInputStream = null;
		}
        return success;
    }
	/**
	 * This method make the gzip(.gz) files of all files in given filesList.
	 * @param filesList
	 */
	public void makeGZIP(ArrayList filesList) {
		String inFileName 				= null;
		GZIPOutputStream gzipOutStream 	= null;
		FileInputStream fileInputStream	= null;
        try {
			if(filesList != null && filesList.size() > 0){	
				for(int i=0; i < filesList.size(); i++){
					inFileName = (String)filesList.get(i);//file name	  
					gzipOutStream = new GZIPOutputStream(new FileOutputStream(inFileName+".gz"));
					fileInputStream = new FileInputStream(inFileName);	        		
					
					byte[] buf = new byte[1024];
			        int len;
			        while((len = fileInputStream.read(buf)) > 0) {
			        	gzipOutStream.write(buf,0,len);
			        }
			        gzipOutStream.flush();
			        gzipOutStream.flush();
			        
			        fileInputStream.close();    	        
			        gzipOutStream.close();    	       
			        gzipOutStream.close();
				}
			}
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			inFileName 		= null;
			gzipOutStream 	= null;
			fileInputStream	= null;
		}
    }
}