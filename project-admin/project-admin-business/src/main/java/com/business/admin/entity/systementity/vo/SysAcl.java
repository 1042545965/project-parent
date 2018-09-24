package com.business.admin.entity.systementity.vo;

import java.util.Date;

public class SysAcl {
    private Long id;

    private String privilegemaster;

    private String privilegemasterkey;

    private String privilegeaccess;

    private String privilegeaccesskey;

    private Long privilegeoperation;

    private Date authorizationdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrivilegemaster() {
        return privilegemaster;
    }

    public void setPrivilegemaster(String privilegemaster) {
        this.privilegemaster = privilegemaster == null ? null : privilegemaster.trim();
    }

    public String getPrivilegemasterkey() {
        return privilegemasterkey;
    }

    public void setPrivilegemasterkey(String privilegemasterkey) {
        this.privilegemasterkey = privilegemasterkey == null ? null : privilegemasterkey.trim();
    }

    public String getPrivilegeaccess() {
        return privilegeaccess;
    }

    public void setPrivilegeaccess(String privilegeaccess) {
        this.privilegeaccess = privilegeaccess == null ? null : privilegeaccess.trim();
    }

    public String getPrivilegeaccesskey() {
        return privilegeaccesskey;
    }

    public void setPrivilegeaccesskey(String privilegeaccesskey) {
        this.privilegeaccesskey = privilegeaccesskey == null ? null : privilegeaccesskey.trim();
    }

    public Long getPrivilegeoperation() {
        return privilegeoperation;
    }

    public void setPrivilegeoperation(Long privilegeoperation) {
        this.privilegeoperation = privilegeoperation;
    }

    public Date getAuthorizationdate() {
        return authorizationdate;
    }

    public void setAuthorizationdate(Date authorizationdate) {
        this.authorizationdate = authorizationdate;
    }
}