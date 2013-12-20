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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper entry to facilitate search iterator
 * 
 * @author Chun Ho <chun.ho@permeance.com.au>
 */
public class JournalStructureBrowseEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Log _log = LogFactoryUtil.getLog(JournalStructureBrowseEntry.class);

    private static final String DEFAULT_ID = "0";
    private DDMStructure structure = null;

    public JournalStructureBrowseEntry() {
    }

    public JournalStructureBrowseEntry(DDMStructure structure) {
        this.structure = structure;
    }

    public DDMStructure getStructure() {
        return structure;
    }

    public String getId() {
        if (structure != null) {
            return Long.toString(structure.getGroupId()) + StringPool.UNDERLINE + structure.getStructureKey();
        }
        return DEFAULT_ID;
    }

    public boolean isBasicWebContent() {
        return structure == null;
    }

    public static List<JournalStructureBrowseEntry> getEntriesForGroup(long groupId) {
        List<JournalStructureBrowseEntry> toReturn = new ArrayList<JournalStructureBrowseEntry>();
        try {
            long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class.getName());
            List<DDMStructure> structures = DDMStructureLocalServiceUtil.getStructures(groupId, classNameId);

            for (DDMStructure structure : structures) {
                toReturn.add(new JournalStructureBrowseEntry(structure));
            }
        } catch (Exception e) {
            _log.error("While retrieving structures for group", e);
        }
        return toReturn;
    }

}
