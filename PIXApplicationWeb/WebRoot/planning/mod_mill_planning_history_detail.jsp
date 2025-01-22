
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>

<html-el:form action="/planning/planninghistorydetail.do">
<c:if test="${orderDetailForm.poHeader.partyCollection != null && not empty orderDetailForm.poHeader.partyCollection}">
<c:set var="readOnlyForm" value="F"/>
<c:forEach var="party" items="${orderDetailForm.poHeader.partyCollection}" varStatus="partyCount">
	<c:choose>
		<c:when test="${party.partyType=='B'}">
			<c:set var="buyer" value="${party}"/>
			<c:if test="${buyer.san==loggedInSAN}">
				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			<c:forEach var="contact" items="${buyer.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactBuyer" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='M'}">
			<c:set var="vendor" value="${party}"/>
			<c:set var="partyCounter" value="${partyCount.index}"/>
			<c:forEach var="contact" items="${vendor.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactVendor" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:when test="${party.partyType=='S'}">
			<c:set var="shipTo" value="${party}"/>
			<c:if test="${shipTo.san==loggedInSAN}">
				<c:set var="readOnlyForm" value="T"/>
			</c:if>
			<c:forEach var="contact" items="${shipTo.contactCollection}">
				<c:if test="${contact.orderNo==1}">
					<c:set var="contactShipTo" value="${contact}"/>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</c:forEach>
</c:if>
<c:set var="totalLineItems"/>
<c:forEach var="lineItem" items="${orderDetailForm.poHeader.lineItemCollection}" varStatus="lineInfo">
	<c:set var="totalLineItems" value="${lineInfo.count}"/>
</c:forEach>
 
<tr>
    <td height="25" align="left" valign="top"><img src="<%=request.getContextPath()%>/images/heading_icon.gif" width="23" height="9"><span class="headingText">History - Planning No.  ${orderDetailForm.poHeader.poNo}</span></td>
</tr>
<tr>
    <td align="left" valign="top" class="padding23">
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
      
      <tr>
        <td height="15" colspan="2"></td>
      </tr>
        </table>
      <table width="98%" border="0" cellspacing="0" cellpadding="0">
 
        <tr>
          <td height="20" align="left" valign="top" class="headingMain"><span class="headingMainArrow">&gt; </span>TITLE:&nbsp;&nbsp;${orderDetailForm.poHeader.titleDesc}</td>
        </tr>
        <tr>
          <td><table width="100%"  border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td width="17%" height="23" class="blueColumn">MATERIAL NUMBER: </td>
              <td width="17%" class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.isbn10}</td>
              <td width="17%" class="blueColumn">ISSUE DATE: </td>
              <td width="17%" class="lightblueColumn"><fmt:formatDate value="${orderDetailForm.poHeader.issueDate}" type="both" pattern="MM/dd/yyyy" /></td>
              <td height="23" class="blueColumn">STATUS: </td>
              <td class="lightblueColumn" colspan="5">
              	<c:forEach var="itemBuyer" items="${planningAllStatus}">
              		<c:if test="${itemBuyer.statusId==buyer.statusId}">
						<c:out value="${itemBuyer.statusDescription}"/>
					</c:if>
				</c:forEach>
				</td>
              
            </tr>
            <tr>
              <td height="23" class="blueColumn">PAPER GRADE: </td>
              <td class="lightblueColumn">${orderDetailForm.poHeader.titleDetail.paperGrade}</td>
              <td class="blueColumn">BASIS WEIGHT: </td>
              <td class="lightblueColumn" id="headerDlDate"><c:out value="${orderDetailForm.poHeader.titleDetail.basisWeight}"/></td>
              <td class="blueColumn">DELIVERY DATE: </td>
              <td class="lightblueColumn"> <c:out value="${buyer.deliveryDate}"/></td>
               
            </tr>
            <tr>
            <td class="blueColumn">UOM: </td>
              <td class="lightblueColumn" id="headerDlDate">
              	<c:choose>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='LB'}">
              			<c:out value="Roll"/>
              		</c:when>
              		<c:when test="${orderDetailForm.poHeader.UOMDetail.uom=='SH'}">
              			<c:out value="Sheet"/>
              		</c:when>
              		
              	</c:choose>
              </td>
             <td class="blueColumn">Quantity: </td>
              <td class="lightblueColumn" id="headerDlDate">
              		${orderDetailForm.poHeader.totalQuantity}
              </td>
              <td class="blueColumn">  &nbsp;</td>
              <td class="lightblueColumn"> &nbsp;</td>
              
            </tr>
          </table></td>
        </tr>
        <tr>
          <td height="16" align="left" valign="top"></td>
        </tr>
         <tr>
          <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" class="tableBorderDash">
    <tr>
    <td valign="middle" class="greyColumnHeading">BUYER NOTES: </td>
    <td colspan="3" valign="middle" class="greyColumnContent"> 
     <textarea class="textsmall" readonly="readonly" rows="5" style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  
${buyer.comments}
 </textarea></td>
  </tr>
  <tr>
    <td height="40" valign="middle" class="greyColumnHeading">YOUR COMMENTS:   </td>
    <td height="40" colspan="3" valign="middle" class="greyColumnContent">
    <c:choose>
	    <c:when test="${readOnlyForm!='F'}">
   			<html-el:textarea property="poHeader.partyCollectionIdx[${partyCounter}].comments" rows="2" style="width:80%" styleClass= "textfield" value="${vendor.comments}"/>
    	</c:when>
		<c:otherwise>
			${vendor.comments}
		</c:otherwise>
	</c:choose>	
    </td>
    </tr>
</table></td>
        </tr>

        
      <tr>
          <td height="16" align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">BUYER (${buyer.san})</legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${buyer.name1} ${buyer.name2} ${buyer.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
                      ${buyer.address1} ${buyer.address2}<br>
                      ${buyer.address3} ${buyer.address4}<br>
                      ${buyer.city} ${buyer.postalCode} ${buyer.state} ${buyer.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactBuyer.contactFirstName} ${contactBuyer.contactLastName}<br>
<c:if test="${contactBuyer.phone!=null}">&nbsp;${contactBuyer.phone} (Business)</c:if><br>
<c:if test="${contactBuyer.mobile!=null}">&nbsp;${contactBuyer.mobile} (Mob)</c:if><br>
<c:if test="${contactBuyer.fax!=null}">&nbsp;${contactBuyer.fax} (Fax)</c:if><br>
<c:if test="${contactBuyer.email!=null}">&nbsp;${contactBuyer.email} </c:if><br>
                  </tr>
                </table>
              </fieldset></td>
              <td width="2%" height="170">&nbsp;</td>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">MILL/MERCHANT (${vendor.san})</legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${vendor.name1} ${vendor.name2} ${vendor.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
                      ${vendor.address1} ${vendor.address2}<br>
                      ${vendor.address3} ${vendor.address4}<br>
                      ${vendor.city} ${vendor.postalCode} ${vendor.state} ${vendor.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactVendor.contactFirstName} ${contactVendor.contactLastName}<br>
<c:if test="${contactVendor.phone!=null}">&nbsp;${contactVendor.phone} (Business)</c:if><br>
<c:if test="${contactVendor.mobile!=null}">&nbsp;${contactVendor.mobile} (Mob)</c:if><br>
<c:if test="${contactVendor.fax!=null}">&nbsp;${contactVendor.fax} (Fax)</c:if><br>
<c:if test="${contactVendor.email!=null}">&nbsp;${contactVendor.email} </c:if><br>
                  </tr>
                </table>
              </fieldset></td>
              <%--<td width="2%">&nbsp;</td>
              <td width="30%" valign="top"><fieldset class="legendBorder">
                <legend class="legendeTitle">SHIP TO (${shipTo.san})</legend>
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="5"></td>
                  </tr>
                  <tr>
                    <td class="headingText12">${shipTo.name1} ${shipTo.name2} ${shipTo.name3}</td>
                  </tr>
                  <tr>
                    <td class="textLegend"><br>
                      ${shipTo.address1} ${shipTo.address2}<br>
                      ${shipTo.address3} ${shipTo.address4}<br>
                      ${shipTo.city} ${shipTo.postalCode} ${shipTo.state} ${shipTo.countryDetail.countryName}</td>
                  </tr>
                  <tr>
                    <td height="5" class="textLegend"></td>
                  </tr>
                  <tr>
                    <td class="textLegend"><p>- ${contactShipTo.contactFirstName} ${contactShipTo.contactLastName}<br>
<c:if test="${contactShipTo.phone!=null}">&nbsp;${contactShipTo.phone} (Business)</c:if><br>
<c:if test="${contactShipTo.mobile!=null}">&nbsp;${contactShipTo.mobile} (Mob)</c:if><br>
<c:if test="${contactShipTo.fax!=null}">&nbsp;${contactShipTo.fax} (Fax)</c:if><br>
<c:if test="${contactShipTo.email!=null}">&nbsp;${contactShipTo.email} </c:if><br>

                  </tr>
                </table>
              </fieldset></td>--%>
              
              </tr>
          </table></td>
        </tr>
        <tr>
          <td height="10"></td>
        </tr>
        
 
     
        <c:if test="${orderDetailForm.poHeader.termsConditions!=null}">
		<tr>
          <td height="16">&nbsp;</td>
        </tr>
        <tr>
          <td align="left" valign="top" class="subLinksMain">TERMS &amp; CONDITIONS: </td>
        </tr>
        <tr>
         <td align="left" valign="top">
      
        <textarea class="textsmall" readonly="readonly" rows="5"  style="color: black; background-color:transparent; border:none; overflow:visible; width:722px;text-align:left">  

${orderDetailForm.poHeader.termsConditions}
</textarea>
        
		</td>
        </tr>
        </c:if>
        <tr>
          <td height="16">&nbsp;</td>
        </tr>
        <tr>
          <td><label>
          <input name="Button22" type="button" class="buttonMain" onClick="javascript:window.close();" value="Close">
          </label></td>
        </tr>
        <tr>
          <td height="35">&nbsp;</td>
        </tr>
         </html-el:form> 
     </table></td>
  </tr>
 