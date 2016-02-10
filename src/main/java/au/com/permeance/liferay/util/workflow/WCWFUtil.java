package au.com.permeance.liferay.util.workflow;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;

public class WCWFUtil {
	public static boolean isUseDDMStructureId() {
		String value = PropsUtil.get("webcontent-workflow-portlet.isUseDDMStructureId");
		return "true".equals(value);
	}
	
	public static long getDDMStructureId(JournalArticle article) {
		try {
	        long classNameId = ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class.getName());
	        DDMStructure structure = DDMStructureLocalServiceUtil.getStructure(article.getGroupId(), classNameId, article.getStructureId(), true);
	        if(structure != null) {
	        	return structure.getStructureId();
	        }
		} catch (PortalException e) {
			return 0l;
		} catch (SystemException e) {
			return 0l;
		}
		return 0l;
	}
	
}
