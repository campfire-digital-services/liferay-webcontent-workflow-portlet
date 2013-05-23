/**
 * Copyright (C) 2013 Permeance Technologies
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package au.com.permeance.liferay.portlets.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Main portlet to show and edit workflow configuration for Web Content by Structure
 * 
 * @author Chun Ho <chun.ho@permeance.com.au>
 */
public class WebContentWorkflowPortlet extends MVCPortlet {
    private static final Log log = LogFactoryUtil.getLog(WebContentWorkflowPortlet.class);
    private static final String VIEW_PAGE = "/html/portlet/webcontent-workflow/view.jsp";
    private static final String ERROR_PAGE = "/html/portlet/webcontent-workflow/error.jsp";
    private static final String _PREFIX = "workflowDefinitionName@";

    @Override
    public void doView(final RenderRequest renderRequest, final RenderResponse renderResponse) throws IOException, PortletException {
        try {
            include(VIEW_PAGE, renderRequest, renderResponse);
        } catch (final Exception e) {
            log.warn(e);
            include(ERROR_PAGE, renderRequest, renderResponse);
        }
    }

    @Override
    public void processAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws IOException, PortletException {

        final long groupId = ParamUtil.getLong(actionRequest, "groupId");
        final ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        final Enumeration<String> paramNames = actionRequest.getParameterNames();

        boolean success = true;
        while (paramNames.hasMoreElements()) {
            final String name = paramNames.nextElement();

            if (!name.startsWith(_PREFIX)) {
                continue;
            }
            final long structureId = GetterUtil.getLong(name.substring(_PREFIX.length(), name.length()), -1l);
            if (structureId >= 0) {
                String workflowDefinition = ParamUtil.getString(actionRequest, name);
                try {
                    WorkflowDefinitionLinkLocalServiceUtil.updateWorkflowDefinitionLink(themeDisplay.getUserId(),
                            themeDisplay.getCompanyId(), groupId, JournalArticle.class.getName(), structureId, 0, workflowDefinition);
                } catch (SystemException e) {
                    log.warn(e);
                    SessionErrors.add(actionRequest, e.toString());
                    success = false;
                } catch (PortalException e) {
                    log.warn(e);
                    SessionErrors.add(actionRequest, e.toString());
                    success = false;
                }
            }
        }

        if (success) {
            SessionMessages.add(actionRequest, "success");
        }
        sendRedirect(actionRequest, actionResponse);
    }
}
