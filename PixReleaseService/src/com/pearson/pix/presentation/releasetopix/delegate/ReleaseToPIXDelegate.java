package com.pearson.pix.presentation.releasetopix.delegate;

import java.util.Map;
import java.util.Set;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocal;
import com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome;
import com.pearson.pix.dto.ces.CustomOrderHistoryCESDTO;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.dto.reports.ReportPixHistory;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.exception.AppException;


public class ReleaseToPIXDelegate {
	
	
	   private static com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome releaseToPIXLocalHome = null;
	
	  public static com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome getReleaseToPIXHome() throws javax.naming.NamingException
	   {
	      if (releaseToPIXLocalHome == null) {System.out.println("com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome.JNDI_NAME--vsivagn----"+com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome.JNDI_NAME);//java:global/PIXApplicationEJB/ReleaseToPIX!com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome
	    	  releaseToPIXLocalHome = (com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome) lookupHome(null, "ejb:PIXApplication/PIXApplicationEJB/ReleaseToPIX!com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome", com.pearson.pix.business.releasetopix.interfaces.ReleaseToPIXLocalHome.class);
	      }System.out.println("releaseToPIXLocalHome----vsivagn----"+releaseToPIXLocalHome);
	      return releaseToPIXLocalHome;
	   }
	
	 private ReleaseToPIXLocal getReleaseToPIXLocal() throws AppException
	    {
		 ReleaseToPIXLocal objReleaseToPIXLocal = null;
	    	try{
	    		ReleaseToPIXLocalHome objReleaseToPIXLocalHome =getReleaseToPIXHome();System.out.println("objReleaseToPIXLocalHome-----"+objReleaseToPIXLocalHome);
	    		objReleaseToPIXLocal = objReleaseToPIXLocalHome.create();
	    	} 
	    	catch(NamingException ne)
	    	{
	    		ne.printStackTrace();
	    	}
		   
	    	catch(CreateException ce)
	    	{
	    		ce.printStackTrace();
	    	}
	    	return objReleaseToPIXLocal;
	    }
	 
	 
	 
	 private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
	      // Obtain initial context
	      javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
	      try {
	         Object objRef = initialContext.lookup(jndiName);
	         // only narrow if necessary
	         if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
	            return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
	         else
	            return objRef;
	      } finally {
	         initialContext.close();
	      }
	   }
	 
	 public Boolean insertPOInfoToPIX(Map poData)throws AppException
	 {
		 return getReleaseToPIXLocal().insertPOInfoToPIX(poData);
	 }
	 
	 
	 public PixEtlPoHeaderCes[] validatePOInfoToPIXFromStaging(Map poData)throws AppException
	 {
		 return getReleaseToPIXLocal().validatePOInfoToPIXFromStaging(poData);
	 }
	 
	 
	 public Boolean insertBookSpecInfoToPIX(Map bookSpecData)throws AppException{
		 return getReleaseToPIXLocal().insertBookSpecInfoToPIX(bookSpecData);
	  }
		

		public PixEtlBookSpecCes[] validateBookSpecInfoToPIXFromStaging(Map bookSpecData)throws AppException{
			 return getReleaseToPIXLocal().validateBookSpecInfoToPIXFromStaging(bookSpecData);
		}
		
		
		public Report[] getOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException{
			 System.out.println(" in......releaseToPIXDelegate..in.....................");
			 return getReleaseToPIXLocal().getOrderHistoryInCES(orderInput);
		}
		
		public ReportPixHistory[] getPixOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput)throws AppException{
			 System.out.println(" in......releaseToPIXDelegate..in.....................");
			 return getReleaseToPIXLocal().getPixOrderHistoryInCES(orderInput);
		}

}
