package com.pearson.pix.dto.arptitlesetup;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  ###  Generated by Oracle TopLink Workbench 10.1.3.0.0 - Tue Mar 23 14:31:45 IST 2010.  ###
 */

public class FlipreportdetailsArp {

	private BigDecimal buyerId;
	private String buyerName;
	private BigDecimal copyrightYear;
	private BigDecimal eventId;
	private Date inventDueDate;
	private Date inventRequestDate;
	private Date postedDateTime;
	private BigDecimal printingNo;
	private BigDecimal titleId;
	private String titleIsbn;
	private String comments;
	private String buyerComments; 
	private String reviewCopyProvide;
	private String reviewCopyReq;
	private String reviewCopyType;
	private BigDecimal vendorPageCount;
	private BigDecimal buyerPageCount;
	private double unitCost;
	private String titleStatus;
	private String edition;
	private String author;
	private String titleDescription;
	private BigDecimal pageCount;
	private String statusCode;  

public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

public FlipreportdetailsArp() {
	super();
}

public String getAuthor() {
	return this.author;
}

public BigDecimal getBuyerId() {
	return this.buyerId;
}

public String getBuyerName() {
	return this.buyerName;
}

public String getComments() {
	return this.comments;
}

public BigDecimal getCopyrightYear() {
	return this.copyrightYear;
}



public BigDecimal getEventId() {
	return this.eventId;
}

public Date getInventDueDate() {
	return this.inventDueDate;
}

public Date getInventRequestDate() {
	return this.inventRequestDate;
}

public BigDecimal getPageCount() {
	return this.pageCount;
}

public Date getPostedDateTime() {
	return this.postedDateTime;
}

public BigDecimal getPrintingNo() {
	return this.printingNo;
}

public String getReviewCopyProvide() {
	return this.reviewCopyProvide;
}

public String getReviewCopyReq() {
	return this.reviewCopyReq;
}

public String getTitleDescription() {
	return this.titleDescription;
}

public BigDecimal getTitleId() {
	return this.titleId;
}

public String getTitleIsbn() {
	return this.titleIsbn;
}

public String getTitleStatus() {
	return this.titleStatus;
}

public double getUnitCost() {
	return this.unitCost;
}

public BigDecimal getVendorPageCount() {
	return this.vendorPageCount;
}

public void setAuthor(String author) {
	this.author = author;
}

public void setBuyerId(BigDecimal buyerId) {
	this.buyerId = buyerId;
}

public void setBuyerName(String buyerName) {
	this.buyerName = buyerName;
}

public void setComments(String comments) {
	this.comments = comments;
}

public void setCopyrightYear(BigDecimal copyrightYear) {
	this.copyrightYear = copyrightYear;
}


/**
 * @return the edition
 */
public String getEdition() {
	return edition;
}

/**
 * @param edition the edition to set
 */
public void setEdition(String edition) {
	this.edition = edition;
}

public void setEventId(BigDecimal eventId) {
	this.eventId = eventId;
}

public void setInventDueDate(Date inventDueDate) {
	this.inventDueDate = inventDueDate;
}

public void setInventRequestDate(Date inventRequestDate) {
	this.inventRequestDate = inventRequestDate;
}

public void setPageCount(BigDecimal pageCount) {
	this.pageCount = pageCount;
}

public void setPostedDateTime(Date postedDateTime) {
	this.postedDateTime = postedDateTime;
}

public void setPrintingNo(BigDecimal printingNo) {
	this.printingNo = printingNo;
}

public void setReviewCopyProvide(String reviewCopyProvide) {
	this.reviewCopyProvide = reviewCopyProvide;
}

public void setReviewCopyReq(String reviewCopyReq) {
	this.reviewCopyReq = reviewCopyReq;
}

public void setTitleDescription(String titleDescription) {
	this.titleDescription = titleDescription;
}

public void setTitleId(BigDecimal titleId) {
	this.titleId = titleId;
}

public void setTitleIsbn(String titleIsbn) {
	this.titleIsbn = titleIsbn;
}

public void setTitleStatus(String titleStatus) {
	this.titleStatus = titleStatus;
}

public void setUnitCost(double unitCost) {
	this.unitCost = unitCost;
}

public void setVendorPageCount(BigDecimal vendorPageCount) {
	this.vendorPageCount = vendorPageCount;
}

public String getReviewCopyType() {
	return reviewCopyType;
}

public void setReviewCopyType(String reviewCopyType) {
	this.reviewCopyType = reviewCopyType;
}

public BigDecimal getBuyerPageCount() {
	return buyerPageCount;
}

public void setBuyerPageCount(BigDecimal buyerPageCount) {
	this.buyerPageCount = buyerPageCount;
}

public String getBuyerComments() {
	return buyerComments;
}

public void setBuyerComments(String buyerComments) {
	this.buyerComments = buyerComments;
}

}
