package com.yyg.sbt.service.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 应用模块名称<p>UserRightsInfo用户权限信息
 * 代码描述<p>
 * @author yyg
 * @since 2019/3/11 14:06
 */
public class UserRightsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String employeeId;

    private Map<String,List<String>> resourcesUrl;

    private List<String> businessLine;

    private List<String> platformIdList;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Map<String, List<String>> getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(Map<String, List<String>> resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    public List<String> getBusinessLine() {
        if(businessLine == null){
            return new ArrayList<>();
        }
        return businessLine;
    }

    public void setBusinessLine(List<String> businessLine) {
        this.businessLine = businessLine;
    }

    public List<String> getPlatformIdList() {
        if(platformIdList == null){
            return new ArrayList<>();
        }
        return platformIdList;
    }

    public void setPlatformIdList(List<String> platformIdList) {
        this.platformIdList = platformIdList;
    }

}
