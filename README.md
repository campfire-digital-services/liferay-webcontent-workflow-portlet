# Liferay Web Content Workflow Configuration Portlet

*liferay-webcontent-workflow-portlet*

This portlet to allow assignment of different Liferay workflows to individual Web Content Structures, similar to the request here: [Liferay JIRA LPS-13617](https://issues.liferay.com/browse/LPS-13617 "LPS-13617")


## Supported Products

* Liferay Portal 6.1 CE GA2 (6.1.1+)
* Liferay Portal 6.1 EE GA2 (6.1.20+)


## Downloads

The latest releases are available from [SourceForge](http://sourceforge.net/projects/permeance-apps/files/liferay-web-content-workflow-configuration/ "Liferay Web Content Workflow Configuration Portlet").

It is also available from the [Liferay Marketplace](http://www.liferay.com/marketplace/-/mp/application/25619448 "Liferay Web Content Workflow Configuration Portlet").


## Usage

This is expected to be installed with a Liferay Workflow Engine, and has been tested with the Liferay Kaleo Workflow Engine. On installing the plugin:

When viewing the Workflow Configuration for a specific community/site, you will see that the "Web Content" asset type has moved to the second section of the page.

![Workflow Configuration](/doc/images/wcwfp3.PNG "Workflow Configuration")

You will also find a new portlet "Web Content Workflow Configuration" below Workflow Configuration in the Content section of the Control Panel.

![Control Panel](/doc/images/wcwfp1.PNG "Control Panel")

This portlet allows the administrator to select specific Workflows for each structure type available to the site (either in the site itself or in the global scope).

![Web Content Workflow Configuration](/doc/images/wcwfp2.PNG "Web Content Workflow Configuration")

## Building

Step 1. Checkout source from GitHub project

    % git  clone  https://github.com/permeance/liferay-webcontent-workflow-portlet

Step 2. Build and package

    % mvn  -U  clean  package

This will build "liferay-webcontent-workflow-portlet-XXX.war" in the targets tolder.

NOTE: You will require JDK 1.6+ and Maven 3.


## Installation

### Liferay Portal + Apache Tomcat Bundle

eg.

Deploy "liferay-webcontent-workflow-portlet-1.0.0.0.war" to "$LIFERAY_HOME/deploy" folder.

## License

Liferay Web Content Workflow Configuration Portlet is available under GNU Public License version 3.0 (GPLv3). A copy of the license is attached in the code package.

## Project Team

* Chun Ho - chun.ho@permeance.com.au
