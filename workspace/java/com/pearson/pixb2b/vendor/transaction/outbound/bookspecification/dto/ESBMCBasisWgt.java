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
 * Title		: 	ESBMCBasisWgt.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ESBMCBasisWgt is a data transfer object to store the Endsheet Binding Material Characteristics  
 * BasisWeight details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESBMCBasisWgt implements java.io.Serializable {
	private static final long serialVersionUID = 2297240256236579752L;
	
	private String prodBasisSzType 	= null;
	private String sampleType 		= null;
	private String resultSource 	= null;
	private String testAgency 		= null;
	private String testMethod 		= null;
	private String detValue			= null;
	private String uomDetValue		= null;
	private String detRangeMin		= null;
	private String uomdetRangeMin	= null;
	private String detRangeMax		= null;
	private String uomdetRangeMax	= null;
	private String stdDeviation		= null;
	private String uomstdDeviation	= null;
	private String sampleSize		= null;
	private String twoSigLower		= null;
	private String uomtwoSigLower	= null;
	private String twoSigUpper		= null;
	private String uomtwoSigUpper	= null;
	private ArrayList esBMInValList	= null;
	/**
	 * Default constructor.
	 */
	public ESBMCBasisWgt() {
		super();
	}
	/**
	 * @return the prodBasisSzType
	 */
	public String getProdBasisSzType() {
		return prodBasisSzType;
	}
	/**
	 * @param prodBasisSzType the prodBasisSzType to set
	 */
	public void setProdBasisSzType(String prodBasisSzType) {
		this.prodBasisSzType = prodBasisSzType;
	}
	/**
	 * @return the sampleType
	 */
	public String getSampleType() {
		return sampleType;
	}
	/**
	 * @param sampleType the sampleType to set
	 */
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	/**
	 * @return the resultSource
	 */
	public String getResultSource() {
		return resultSource;
	}
	/**
	 * @param resultSource the resultSource to set
	 */
	public void setResultSource(String resultSource) {
		this.resultSource = resultSource;
	}
	/**
	 * @return the testAgency
	 */
	public String getTestAgency() {
		return testAgency;
	}
	/**
	 * @param testAgency the testAgency to set
	 */
	public void setTestAgency(String testAgency) {
		this.testAgency = testAgency;
	}
	/**
	 * @return the testMethod
	 */
	public String getTestMethod() {
		return testMethod;
	}
	/**
	 * @param testMethod the testMethod to set
	 */
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}
	/**
	 * @return the detValue
	 */
	public String getDetValue() {
		return detValue;
	}
	/**
	 * @param detValue the detValue to set
	 */
	public void setDetValue(String detValue) {
		this.detValue = detValue;
	}
	/**
	 * @return the uomDetValue
	 */
	public String getUomDetValue() {
		return uomDetValue;
	}
	/**
	 * @param uomDetValue the uomDetValue to set
	 */
	public void setUomDetValue(String uomDetValue) {
		this.uomDetValue = uomDetValue;
	}
	/**
	 * @return the detRangeMin
	 */
	public String getDetRangeMin() {
		return detRangeMin;
	}
	/**
	 * @param detRangeMin the detRangeMin to set
	 */
	public void setDetRangeMin(String detRangeMin) {
		this.detRangeMin = detRangeMin;
	}
	/**
	 * @return the uomdetRangeMin
	 */
	public String getUomdetRangeMin() {
		return uomdetRangeMin;
	}
	/**
	 * @param uomdetRangeMin the uomdetRangeMin to set
	 */
	public void setUomdetRangeMin(String uomdetRangeMin) {
		this.uomdetRangeMin = uomdetRangeMin;
	}
	/**
	 * @return the detRangeMax
	 */
	public String getDetRangeMax() {
		return detRangeMax;
	}
	/**
	 * @param detRangeMax the detRangeMax to set
	 */
	public void setDetRangeMax(String detRangeMax) {
		this.detRangeMax = detRangeMax;
	}
	/**
	 * @return the uomdetRangeMax
	 */
	public String getUomdetRangeMax() {
		return uomdetRangeMax;
	}
	/**
	 * @param uomdetRangeMax the uomdetRangeMax to set
	 */
	public void setUomdetRangeMax(String uomdetRangeMax) {
		this.uomdetRangeMax = uomdetRangeMax;
	}
	/**
	 * @return the stdDeviation
	 */
	public String getStdDeviation() {
		return stdDeviation;
	}
	/**
	 * @param stdDeviation the stdDeviation to set
	 */
	public void setStdDeviation(String stdDeviation) {
		this.stdDeviation = stdDeviation;
	}
	/**
	 * @return the uomstdDeviation
	 */
	public String getUomstdDeviation() {
		return uomstdDeviation;
	}
	/**
	 * @param uomstdDeviation the uomstdDeviation to set
	 */
	public void setUomstdDeviation(String uomstdDeviation) {
		this.uomstdDeviation = uomstdDeviation;
	}
	/**
	 * @return the sampleSize
	 */
	public String getSampleSize() {
		return sampleSize;
	}
	/**
	 * @param sampleSize the sampleSize to set
	 */
	public void setSampleSize(String sampleSize) {
		this.sampleSize = sampleSize;
	}
	/**
	 * @return the twoSigLower
	 */
	public String getTwoSigLower() {
		return twoSigLower;
	}
	/**
	 * @param twoSigLower the twoSigLower to set
	 */
	public void setTwoSigLower(String twoSigLower) {
		this.twoSigLower = twoSigLower;
	}
	/**
	 * @return the uomtwoSigLower
	 */
	public String getUomtwoSigLower() {
		return uomtwoSigLower;
	}
	/**
	 * @param uomtwoSigLower the uomtwoSigLower to set
	 */
	public void setUomtwoSigLower(String uomtwoSigLower) {
		this.uomtwoSigLower = uomtwoSigLower;
	}
	/**
	 * @return the twoSigUpper
	 */
	public String getTwoSigUpper() {
		return twoSigUpper;
	}
	/**
	 * @param twoSigUpper the twoSigUpper to set
	 */
	public void setTwoSigUpper(String twoSigUpper) {
		this.twoSigUpper = twoSigUpper;
	}
	/**
	 * @return the uomtwoSigUpper
	 */
	public String getUomtwoSigUpper() {
		return uomtwoSigUpper;
	}
	/**
	 * @param uomtwoSigUpper the uomtwoSigUpper to set
	 */
	public void setUomtwoSigUpper(String uomtwoSigUpper) {
		this.uomtwoSigUpper = uomtwoSigUpper;
	}
	/**
	 * @return the esBMInValList
	 */
	public ArrayList getEsBMInValList() {
		return esBMInValList;
	}
	/**
	 * @param esBMInValList the esBMInValList to set
	 */
	public void setEsBMInValList(ArrayList esBMInValList) {
		this.esBMInValList = esBMInValList;
	}
}
