<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="include.jsp" %>
<html>
<head>
	<title>MAIL</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="parent-html">
	<table>
		<tr>
			<th class="th" width="160">HREF</th>
			<th class="th" width="160">TEXT</th>
		</tr>
		<core:forEach items="${linkDataResultDto.linkDataDtoList}" var="linkDto">
			<tr>
				<!--<td> ${linkDto.linkHref} </td>-->
				  <td width="50%"> ${linkDto.linkText} </td>
			</tr>
		</core:forEach>
	</table>
</body>
</html>
