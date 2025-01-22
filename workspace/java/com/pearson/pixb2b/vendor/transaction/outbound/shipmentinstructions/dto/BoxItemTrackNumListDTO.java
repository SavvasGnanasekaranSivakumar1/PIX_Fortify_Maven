/**
 * 
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class BoxItemTrackNumListDTO implements Serializable{
	
	private static final long serialVersionUID = -5313492389770501388L;

	private String asnId;
	private String trackingNum;
	private Integer booksPerCarton;
	private Integer boxCount;
	
	/**
	 * Default constructor.
	 */
	public BoxItemTrackNumListDTO() {
		super();
	}

	
	/**
	 * @return the asnId
	 */
	public String getAsnId() {
		return asnId;
	}
	/**
	 * @param asnId the asnId to set
	 */
	public void setAsnId(String asnId) {
		this.asnId = asnId;
	}
	/**
	 * @return the trackingNum
	 */
	public String getTrackingNum() {
		return trackingNum;
	}
	/**
	 * @param trackingNum the trackingNum to set
	 */
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	/**
	 * @return the booksPerCarton
	 */
	public Integer getBooksPerCarton() {
		return booksPerCarton;
	}
	/**
	 * @param booksPerCarton the booksPerCarton to set
	 */
	public void setBooksPerCarton(Integer booksPerCarton) {
		this.booksPerCarton = booksPerCarton;
	}
	/**
	 * @return the boxCount
	 */
	public Integer getBoxCount() {
		return boxCount;
	}
	/**
	 * @param boxCount the boxCount to set
	 */
	public void setBoxCount(Integer boxCount) {
		this.boxCount = boxCount;
	}
}
