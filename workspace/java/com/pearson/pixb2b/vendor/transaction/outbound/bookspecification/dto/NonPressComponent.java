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
 * Title		: 	NonPressComponent.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * NonPressComponent is a data transfer object to store the Specification 
 * NonPress Component details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class NonPressComponent implements java.io.Serializable {
	private static final long serialVersionUID = 4316859592308339384L;
	
	private Media media		= null;
	private Gimmick gimmick = null;
	private Sleeve sleeve	= null;
	/**
	 * Default constructor.
	 */
	public NonPressComponent() {
		super();
	}
	/**
	 * @return the media
	 */
	public Media getMedia() {
		return media;
	}
	/**
	 * @param media the media to set
	 */
	public void setMedia(Media media) {
		this.media = media;
	}
	/**
	 * @return the gimmick
	 */
	public Gimmick getGimmick() {
		return gimmick;
	}
	/**
	 * @param gimmick the gimmick to set
	 */
	public void setGimmick(Gimmick gimmick) {
		this.gimmick = gimmick;
	}
	/**
	 * @return the sleeve
	 */
	public Sleeve getSleeve() {
		return sleeve;
	}
	/**
	 * @param sleeve the sleeve to set
	 */
	public void setSleeve(Sleeve sleeve) {
		this.sleeve = sleeve;
	}
}
