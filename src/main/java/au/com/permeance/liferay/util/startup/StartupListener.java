package au.com.permeance.liferay.util.startup;

import au.com.permeance.liferay.util.workflow.WebContentWorkflowHandler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Startup Context Listener
 * 
 * @author Chun Ho <chun.ho@permeance.com.au>
 */
public class StartupListener implements ServletContextListener {
    private static final Log _log = LogFactoryUtil.getLog(StartupListener.class);

    private static WorkflowHandler newWorkflowHandler = new WebContentWorkflowHandler();
    private static WorkflowHandler oldWorkflowHandler = null;
    private static volatile boolean applicationStarted = false;

    public void contextInitialized(final ServletContextEvent event) {
        applicationStart();
    }

    public void contextDestroyed(final ServletContextEvent event) {
        applicationStop();
    }

    protected static synchronized void applicationStart() {
        if (!applicationStarted) {
            applicationStarted = true;
            _log.info("Starting up Web Content Workflow Configuration Portlet");
            oldWorkflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(JournalArticle.class.getName());
            WorkflowHandlerRegistryUtil.register(newWorkflowHandler);
        }
    }

    protected static synchronized void applicationStop() {
        if (applicationStarted) {
            applicationStarted = false;
            WorkflowHandlerRegistryUtil.unregister(newWorkflowHandler);
            if (oldWorkflowHandler != null) {
                WorkflowHandlerRegistryUtil.register(oldWorkflowHandler);
            }
            _log.info("Shutting down Web Content Workflow Configuration Portlet");
        }
    }
}
