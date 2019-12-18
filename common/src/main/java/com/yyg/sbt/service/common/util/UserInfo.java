package com.yyg.sbt.service.common.util;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yyg
 * @description 用户信息
 * @date 2018/11/16 10:20
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String employeeId;

    private String name;

    private String userName;

    private String nickName;

    private String picture;

    private String email;

    private List<String> emails;

    private List<String> roles;

    private String userType;

    private String token;

    private Map<String,List<String>> resourcesUrl;

    private List<String> businessLine;

    private List<String> platformIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        if(!StringUtils.isBlank(nickName) && nickName.contains("\\\\u")) {
            this.nickName = unicodeToCn(nickName);
        } else {
            this.nickName = nickName;
        }
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    private static String unicodeToCn(String unicode) {
        String[] strArray = unicode.split("\\\\u");
        StringBuilder result = new StringBuilder();
        for(int i = 1; i < strArray.length; i++) {
            result.append((char)Integer.valueOf(strArray[i], 16).intValue());
        }
        return result.toString();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getBusinessLine() {
        if(businessLine == null){
            return new ArrayList<String>();
        }
        return businessLine;
    }

    public void setBusinessLine(List<String> businessLine) {
        this.businessLine = businessLine;
    }

    public List<String> getPlatformIdList() {
        if(platformIdList == null){
            return new ArrayList<String>();
        }
        return platformIdList;
    }

    public void setPlatformIdList(List<String> platformIdList) {
        this.platformIdList = platformIdList;
    }

    public Map<String, List<String>> getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(Map<String, List<String>> resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }
}
