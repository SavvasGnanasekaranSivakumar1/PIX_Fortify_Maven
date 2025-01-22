package com.pearson.pixrelease.webservice;

import com.pearson.pix.dto.ces.CustomBookSpecReleaseContainerDTO;
import com.pearson.pix.dto.ces.CustomPOReleaseContainerDTO;
import com.pearson.pix.dto.ces.PixEtlBookSpecCes;
import com.pearson.pix.dto.ces.PixEtlPoHeaderCes;
import com.pearson.pix.dto.ces.CustomOrderHistoryCESDTO;
import com.pearson.pix.dto.reports.Report;
import com.pearson.pix.dto.reports.ReportPixHistory;

public interface IPixReleaseService {
	
	public PixEtlPoHeaderCes[] insertPOInfoToPIX(CustomPOReleaseContainerDTO[] poData);
	
	public PixEtlBookSpecCes[] insertBookSpecInfoToPIX(CustomBookSpecReleaseContainerDTO[] obj);
	
	public Report[] getOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput);
	
	public ReportPixHistory[] getPixOrderHistoryInCES(CustomOrderHistoryCESDTO[] orderInput);
	
}