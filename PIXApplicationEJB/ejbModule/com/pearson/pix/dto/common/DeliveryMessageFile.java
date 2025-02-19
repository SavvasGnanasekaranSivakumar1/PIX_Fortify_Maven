package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  ###  Generated by Oracle TopLink Workbench 10.1.3.0.0 - Fri Sep 28 18:08:53 IST 2007.  ###
 */

public class DeliveryMessageFile implements Serializable {

	private String activeFlag;
	private String billOfLaiding;
	private String carrierNo;
	private String createdBy;
	private Date creationDateTime;
	private BigDecimal fileSeq;
	private String fileUrl;
	private String frieghtNo;
	private String modifiedBy;
	private BigDecimal msgId;
	private BigDecimal msgLineNo;
	private Date pmsTransactionDate;
	private Integer poLineNo;
	private String poNo;
	private Date modificationDateTime;
	
	private String fileName;
	
	private String rollNumber;
	private BigDecimal rollWeight;
	private String dmPostedBy;
	private String dmPostedDate;
	
	

public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public BigDecimal getRollWeight() {
		return rollWeight;
	}

	public void setRollWeight(BigDecimal rollWeight) {
		this.rollWeight = rollWeight;
	}

	public String getDmPostedBy() {
		return dmPostedBy;
	}

	public void setDmPostedBy(String dmPostedBy) {
		this.dmPostedBy = dmPostedBy;
	}

	public String getDmPostedDate() {
		return dmPostedDate;
	}

	public void setDmPostedDate(String dmPostedDate) {
		this.dmPostedDate = dmPostedDate;
	}

public String getCarrierNo() {
		return carrierNo;
	}

	public void setCarrierNo(String carrierNo) {
		this.carrierNo = carrierNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getPmsTransactionDate() {
		return pmsTransactionDate;
	}

	public void setPmsTransactionDate(Date pmsTransactionDate) {
		this.pmsTransactionDate = pmsTransactionDate;
	}

public DeliveryMessageFile() {
	super();
}

public String getActiveFlag() {
	return this.activeFlag;
}

public String getBillOfLaiding() {
	return this.billOfLaiding;
}

public BigDecimal getFileSeq() {
	return this.fileSeq;
}

public String getFileUrl() {
	return this.fileUrl;
}

public String getFrieghtNo() {
	return this.frieghtNo;
}

public Date getModificationDateTime() {
	return this.modificationDateTime;
}

public String getModifiedBy() {
	return this.modifiedBy;
}

public BigDecimal getMsgId() {
	return this.msgId;
}

public BigDecimal getMsgLineNo() {
	return this.msgLineNo;
}

public Integer getPoLineNo() {
	return this.poLineNo;
}

public String getPoNo() {
	return this.poNo;
}

public void setActiveFlag(String activeFlag) {
	this.activeFlag = activeFlag;
}

public void setBillOfLaiding(String billOfLaiding) {
	this.billOfLaiding = billOfLaiding;
}

public void setFileSeq(BigDecimal fileSeq) {
	this.fileSeq = fileSeq;
}

public void setFileUrl(String fileUrl) {
	this.fileUrl = fileUrl;
}

public void setFrieghtNo(String frieghtNo) {
	this.frieghtNo = frieghtNo;
}

public void setModificationDateTime(Date modificationDateTime) {
	this.modificationDateTime = modificationDateTime;
}

public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}

public void setMsgId(BigDecimal msgId) {
	this.msgId = msgId;
}

public void setMsgLineNo(BigDecimal msgLineNo) {
	this.msgLineNo = msgLineNo;
}

public void setPoLineNo(Integer poLineNo) {
	this.poLineNo = poLineNo;
}

public void setPoNo(String poNo) {
	this.poNo = poNo;
}

}
