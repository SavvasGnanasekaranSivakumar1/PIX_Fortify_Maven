package com.pearson.pixrelease.webservice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.*;
import oracle.sql.*;

import com.pearson.pix.dto.reports.ReportPixHistory;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.dto.ces.CustomOrderHistoryCESDTO;
import com.pearson.pix.dto.ces.CustomBookSpecReleaseContainerDTO;
import com.pearson.pix.dto.ces.CustomPOReleaseContainerDTO;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.dto.releasetopix.CustomBookSpecReleaseDTO;
import com.pearson.pix.dto.releasetopix.CustomPOReleaseDTO;
import com.pearson.pix.exception.AppException;

import com.pearson.pix.presentation.releasetopix.delegate.ReleaseToPIXDelegate;

//Generated by MyEclipse

public class PixReleaseServiceImpl implements IPixReleaseService {
	
	public PixEtlPoHeaderCes[] insertPOInfoToPIX(CustomPOReleaseContainerDTO[] poData) {
		//Clob c = CLOB.createTemporary(con, true, CLOB.DURATION_SESSION);
		PixEtlPoHeaderCes[] updatedPoHeaderCes=null;
		if(poData!=null&&poData.length>0)
		{
			  List list = Arrays.asList(poData);
			  Map bookSpecMap=new LinkedHashMap();
			  Vector customDTOList=new Vector();
			  Map dataMap=new LinkedHashMap();
			  customDTOList.addAll(list);
			  if(customDTOList.size()>0)
				{
				  ReleaseToPIXDelegate releaseToPIXDelegate=new ReleaseToPIXDelegate();
				  for(int i=0;i<customDTOList.size();i++){
					  CustomPOReleaseContainerDTO  customPOReleaseContainerObj=(CustomPOReleaseContainerDTO)customDTOList.get(i);
					  PixEtlBookSpecCes[] bookSpecStatus=null;
					  if(customPOReleaseContainerObj.getBookSpecReleaseContainer()!=null)
					  {
					   bookSpecStatus=insertBookSpecInfoToPIX(customPOReleaseContainerObj.getBookSpecReleaseContainer());
					   bookSpecMap.put(customPOReleaseContainerObj.getPixPoHeaderCes()[0].getPoNumber(), bookSpecStatus);
					  }
					  
					  CustomPOReleaseDTO customPOReleaseOBJ=new CustomPOReleaseDTO();
					 
					  if(customPOReleaseContainerObj.getPixPoHeaderCes()!=null)
					  {
						  Vector  pixEltPoHeaderVector=new Vector( Arrays.asList(customPOReleaseContainerObj.getPixPoHeaderCes()));
						  customPOReleaseOBJ.setPoHeaderCes(pixEltPoHeaderVector);
					  }
					 
					  if(customPOReleaseContainerObj.getPixPoHeaderNotesCes()!=null)
					  {
						  //obj = (PixEtlPoHeaderNotesCes)customPOReleaseContainerObj.getPixPoHeaderNotesCes();
						  Vector pixPoHeaderNotesVector=new Vector(Arrays.asList(customPOReleaseContainerObj.getPixPoHeaderNotesCes()));
						  customPOReleaseOBJ.setPoHeaderNotesCes(pixPoHeaderNotesVector);
					  }
					 
					  if(customPOReleaseContainerObj.getPixPoLineCes()!=null)
					  {
						  Vector pixPoLineVector=new Vector(Arrays.asList(customPOReleaseContainerObj.getPixPoLineCes()));
						  customPOReleaseOBJ.setPoLineCes(pixPoLineVector);
					  }
					 
					  if(customPOReleaseContainerObj.getPixPoPriceDetailCes()!=null)
					  {
						  Vector pixPoPriceDetailVector=new Vector(Arrays.asList(customPOReleaseContainerObj.getPixPoPriceDetailCes()));
						  customPOReleaseOBJ.setPoPriceDetailCes(pixPoPriceDetailVector);
					  }
					  
					if(customPOReleaseContainerObj.getPixPoSuppCompCes()!=null)
					  {
						Vector pixPoSuppCompVector=new Vector(Arrays.asList(customPOReleaseContainerObj.getPixPoSuppCompCes()));
						customPOReleaseOBJ.setPoSuppCompCes(pixPoSuppCompVector);
					  }
					
					//Added by Sujeet on 09 Oct 2009
					if(customPOReleaseContainerObj.getPixPoHeaderExt()!=null)
					  {
						Vector pixPoHeaderExtVector=new Vector(Arrays.asList(customPOReleaseContainerObj.getPixPoHeaderExt()));
						customPOReleaseOBJ.setPoHeaderExt(pixPoHeaderExtVector);
					  }
					   dataMap.put(String.valueOf(customPOReleaseContainerObj.getTransactionNumber()), customPOReleaseOBJ);System.out.println("String.valueOf(customPOReleaseContainerObj.getTransactionNumber())-----"+String.valueOf(customPOReleaseContainerObj.getTransactionNumber()+"---"+customPOReleaseContainerObj.getTransactionNumber()));
				  }
				    
				    try{
				    	Boolean   insertResult=releaseToPIXDelegate.insertPOInfoToPIX(dataMap);
				    	if(insertResult!=null&&insertResult.booleanValue()==true){
				    	  updatedPoHeaderCes=releaseToPIXDelegate.validatePOInfoToPIXFromStaging(dataMap);
				    	  if(updatedPoHeaderCes!=null&&bookSpecMap.size()>0){
				    	  for(int h=0;h<updatedPoHeaderCes.length;h++)
				    	  {
				    		  PixEtlBookSpecCes[] bookSpecStatus=(PixEtlBookSpecCes[])bookSpecMap.get(updatedPoHeaderCes[h].getPoNumber());
				    		 if(bookSpecStatus!=null)
				    		  {
				    		   updatedPoHeaderCes[h].setCustomBookSpecMain(bookSpecStatus);
				    		  }
				    	  }
				    	  }
				    	}
				      }
				    catch(Exception ex)
				    {
				    	System.out.println("ex......."+ex);
				    }
				  
				  
				  
				}
		}
		return updatedPoHeaderCes;
	}
	
	
	public PixEtlBookSpecCes[] insertBookSpecInfoToPIX(CustomBookSpecReleaseContainerDTO[] bookSpecData)
	{
		PixEtlBookSpecCes[] updatedBookSpec=null;
		System.out.println(" in......insertBookSpecInfoToPIX.......");
		if(bookSpecData!=null&&bookSpecData.length>0)
		{
			 List list = Arrays.asList(bookSpecData);
			  Vector customDTOList=new Vector();
			  Map dataMap=new HashMap();
			  customDTOList.addAll(list);
			  ReleaseToPIXDelegate releaseToPIXDelegate=new ReleaseToPIXDelegate();
			  for(int i=0;i<customDTOList.size();i++)
			  {
				  CustomBookSpecReleaseContainerDTO  customBookSpecReleaseContainerObj=(CustomBookSpecReleaseContainerDTO)customDTOList.get(i);
				  CustomBookSpecReleaseDTO customBookSpecReleaseOBJ=new CustomBookSpecReleaseDTO();
				  
				  if(customBookSpecReleaseContainerObj.getPixBookSpecCes()!=null)
				  {
					  Vector  objVec=new Vector( Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecCes()));
					  customBookSpecReleaseOBJ.setPixBookSpecCes(objVec);
				  }
				 
				  if(customBookSpecReleaseContainerObj.getPixBookSpecLineCes()!=null)
				  {
					  Vector objVec=new Vector(Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecLineCes()));
					  customBookSpecReleaseOBJ.setPixBookSpecLineCes(objVec);
				  }
				 
				  if(customBookSpecReleaseContainerObj.getPixBookSpecPressCes()!=null)
				  {
					  Vector objVec=new Vector(Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecPressCes()));
					  customBookSpecReleaseOBJ.setPixBookSpecPressCes(objVec);
				  }
				 
				  if(customBookSpecReleaseContainerObj.getPixBookSpecBindingCes()!=null)
				  {
					  Vector objVec=new Vector(Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecBindingCes()));
					  customBookSpecReleaseOBJ.setPixBookSpecBindingCes(objVec);
				  }
				  
				if(customBookSpecReleaseContainerObj.getPixBookSpecNonpressCes()!=null)
				  {
					Vector objVec=new Vector(Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecNonpressCes()));
					customBookSpecReleaseOBJ.setPixBookSpecNonpressCes(objVec);
				  }
				
				if(customBookSpecReleaseContainerObj.getPixBookSpecMiscCes()!=null)
				  {
					Vector objVec=new Vector(Arrays.asList(customBookSpecReleaseContainerObj.getPixBookSpecMiscCes()));
					customBookSpecReleaseOBJ.setPixBookSpecMiscCes(objVec);
				  }
				
				   dataMap.put(String.valueOf(customBookSpecReleaseContainerObj.getTransactionNumberBookSpec()), customBookSpecReleaseOBJ);
				
			  }

			  try{
			    	Boolean   insertResult=releaseToPIXDelegate.insertBookSpecInfoToPIX(dataMap);
			    	if(insertResult!=null&&insertResult.booleanValue()==true){
			    		updatedBookSpec=releaseToPIXDelegate.validateBookSpecInfoToPIXFromStaging(dataMap);
			    	}
			      }
			    catch(Exception ex)
			    {
			    	ex.printStackTrace();
			    	System.out.println("ex......."+ex);
			    }

			  
			  
			  
		}
		
		return updatedBookSpec;
	}
	
	
	public Report[] getOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)
	{
		Report[] resultReport=null;
		
		 try {
			ReleaseToPIXDelegate releaseToPIXDelegate=new ReleaseToPIXDelegate();
			 resultReport= releaseToPIXDelegate.getOrderHistoryInCES(orderInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	   
		return resultReport;
	}
	
	//Added by Sujeet Kumar -- 27 Nov 2009 
	public ReportPixHistory[] getPixOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput){
		
		ReportPixHistory[] resultOrderHistory=null;
		
		 try {
			ReleaseToPIXDelegate releaseToPIXDelegate=new ReleaseToPIXDelegate();
			 resultOrderHistory= releaseToPIXDelegate.getPixOrderHistoryInCES(orderInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return resultOrderHistory;
		
	}
	
	
	
}