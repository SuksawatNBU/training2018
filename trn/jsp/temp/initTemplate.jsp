<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<br/><b>JSP Templat</b> แบ่งออกเป็น 2 ลักษณะ คือ
			<br/>1. Search JSP
			<br/>2. AddEditView JSP
			<br/>
			<br/>
			<br/><b>Search JSP</b> จะมีส่วนประกอบที่สำคัญดังนี้
		</p> 
		<fieldset>
			<legend>
				<s:text name="tmp.searchJsp" />
			</legend>
			<div class="SyntaxHighlighter">
				<pre class="brush: html">
					<!-- ส่วนที่จำเป็นต้องมี -->
					&lt;s:hidden name="criteria.criteriaKey" /&gt;
					&lt;s:hidden name="P_CODE"/&gt;
					&lt;s:hidden name="F_CODE"/&gt;
					&lt;s:hidden name="page"/&gt;
					&lt;s:hidden name="criteria.checkMaxExceed"/&gt;
					&lt;s:hidden name="criteria.start"/&gt;
					&lt;s:hidden name="criteria.statusForUpdate" /&gt;
					
					<!-- ส่วนที่เกี่ยวข้องกับ Data Table -->
					&lt;s:hidden name="criteria.headerSortsSelect" /&gt;
					&lt;s:hidden name="criteria.orderSortsSelect" /&gt;
					&lt;s:hidden name="criteria.alertMaxExceed"/&gt;
					&lt;s:hidden name="criteria.defaultHeaderSortsSelect" /&gt;
					&lt;s:hidden name="criteria.defaultOrderSortsSelect" /&gt;
					
					<!-- ส่วนนี้จะอยู่ในหน้า Search ของตัวเอง -->
					&lt;s:token /&gt;
				</pre>
			</div>
		</fieldset>
			
			
		<p>
			<br/><b>AddEditView JSP</b> จะมีส่วนประกอบที่สำคัญดังนี้
		</p> 
		<fieldset>
			<legend>
				<s:text name="tmp.searchJsp" />
			</legend>
			<div class="SyntaxHighlighter">
				<pre class="brush: html">
					<!-- ส่วนที่จำเป็นต้องมี -->
					&lt;s:include value="/jsp/template/transaction.jsp" /&gt;
					&lt;s:hidden name="criteria.criteriaKey" /&gt;
					&lt;s:hidden name="P_CODE"/&gt;
				    &lt;s:hidden name="F_CODE"/&gt;
				    &lt;s:hidden name="page"/&gt;
					&lt;s:token /&gt;

				    <!-- id ของ object ที่แสดงอยู่ -->
					&lt;s:hidden name="object.id" /&gt;
				</pre>
			</div>
		</fieldset>
		
	</div>
</s:form>
</body>
</html>