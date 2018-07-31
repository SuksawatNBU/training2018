<%@ taglib uri="http://util.paginationtagiii" prefix="pagination-tag"%>
<pagination-tag:pager
	styleClass='navigateColor' 
	form='0'
	start='<%=String.valueOf(request.getParameter("start"))%>'
	range='<%=String.valueOf(request.getParameter("linePerPage"))%>'
	results='<%=String.valueOf(request.getParameter("totalResult"))%>'
	action='<%=request.getContextPath()%>'
	criteriaPrefix='<%=String.valueOf(request.getParameter("criteriaName"))%>'
	naviAjaxScript='<%=String.valueOf(request.getParameter("ajaxScript"))%>'
/>
