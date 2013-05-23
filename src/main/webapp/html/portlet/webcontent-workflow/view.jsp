<%--
Copyright (C) 2013 Permeance Technologies

This program is free software: you can redistribute it and/or modify it under the terms of the
GNU General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If
not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="init.jsp"%>
<%@ page import="com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil" %>


<c:choose>
	<c:when test="<%= WorkflowEngineManagerUtil.isDeployed() %>">
		<%@ include file="/html/portlet/webcontent-workflow/view_wcwf.jspf" %>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>

