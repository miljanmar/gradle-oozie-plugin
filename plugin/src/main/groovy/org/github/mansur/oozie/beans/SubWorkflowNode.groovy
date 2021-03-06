package org.github.mansur.oozie.beans

import groovy.xml.MarkupBuilder;

class SubWorkflowNode extends ActionNode {
  private static final long serialVersionUID = 1L

  String appPath
  Boolean propagateConfiguration
  Map<String, String> configuration

  @Override
  public void buildXml(MarkupBuilder xml, CommonProperties common) {
    actionXml(xml, common) {
      xml.'sub-workflow' {
        'app-path'(appPath)
        if (propagateConfiguration ?: common.propagateConfiguration ?: false) {
          xml.'propagate-configuration'()
        }
        addProperties(xml, 'configuration', configuration ?: common.configuration)
      }
    }
  }
}
