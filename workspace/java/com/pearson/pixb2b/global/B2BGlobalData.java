package com.pearson.pixb2b.global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class B2BGlobalData {

	static B2BGlobalData globalData;
	Long run_Id;
	List threadStatus;
	Vector ftpInfoList;
	java.util.Hashtable<String, HashMap<String, String>> fileContext;
	static{
		globalData = new B2BGlobalData();
	}

	/**
	 * constructor made private so that no thread can instantiate this
	 */
	private B2BGlobalData() {
		threadStatus = Collections.synchronizedList(new ArrayList<Integer>());
		ftpInfoList = new Vector();
		fileContext = new java.util.Hashtable<String, HashMap<String, String>>();
	}
	
	public static synchronized B2BGlobalData getGlobalDataObject()
	{
		return globalData;
	}

	/**
	 * @return the run_Id
	 */
	public Long getRun_Id() {
		return run_Id;
	}

	/**
	 * @param run_Id the run_Id to set
	 */
	public void setRun_Id(Long run_Id) {
		this.run_Id = run_Id;
	}

	/**
	 * each thread will append its status to this list
	 * @param status
	 */
	
	public synchronized void updateStatus(Integer status)
	{
		threadStatus.add(status);
	}

	/**
	 * @return the threadStatus
	 */
	public List getThreadStatus() {
		return threadStatus;
	}
	
	public void addFtpInfo(String ftpInfo) {
		ftpInfoList.add(ftpInfo);
	}
	
	public boolean containsFtpInfo(String ftpInfo) {
			
		return ftpInfoList.contains(ftpInfo);
	}
	
	public void addToFileContext(String key, HashMap fileInfo) {
		
		synchronized (this) {
			fileContext.put(key, fileInfo);	
		}
		
	}
	
	public synchronized void addInfoToFileContext(String contextKey, String fileKey, String info) {
		HashMap<String, String> hm = new HashMap<String, String>();
		String tempInfo = "";
		hm =fileContext.get(contextKey);
		
		if(hm.containsKey(fileKey))
		{
			tempInfo = hm.get(fileKey);
			hm.put(fileKey, info +  "," + tempInfo);
		}
		else{
		hm.put(fileKey,info);
		}
	}
	
	public String getInfoFromContext(String contextKey, String fileKey){
//		return fileContext.get(contextKey).get(fileKey);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = fileContext.get(contextKey);
		if(null!=hm)
			return hm.get(fileKey);
		else
			return null;
	}

}
