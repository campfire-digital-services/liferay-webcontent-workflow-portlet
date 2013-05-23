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

import com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.BaseControlPanelEntry;

/**
 * Workflow control panel entry designed to hide this portlet if no workflow engine is deployed.
 * 
 * @author Chun Ho <chun.ho@permeance.com.au>
 */
public class WorkflowControlPanelEntry extends BaseControlPanelEntry {

    public boolean isVisible(PermissionChecker permissionChecker, Portlet portlet) throws Exception {
        return false;
    }

    @Override
    public boolean isVisible(Portlet portlet, String category, ThemeDisplay themeDisplay) throws Exception {

        if (WorkflowEngineManagerUtil.isDeployed()) {
            return super.isVisible(portlet, category, themeDisplay);
        } else {
            return false;
        }
    }

}
