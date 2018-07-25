<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.cct.trn.core.config.parameter.domain.ParameterConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Struts</title>
	<script type="text/javascript">
		function test() {
			document.forms[0].action = '<s:url value="/jsp/tutorial/searchEmployee.action"/>';
			document.forms[0].submit();
		}
	</script>
</head>
<body>

<button type="submit" onclick="test()">Test</button>
	
</body>
</html>