package com.business.admin.entity.systementity.vo;

public class SysMenu {
    private Long menuid;

    private String menuno;

    private String menuparentno;

    private Long menuorder;

    private String menuname;

    private String menuurl;

    private String menuicon;

    private Long isvisible;

    private Long isleaf;

    private String systemid;

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getMenuno() {
        return menuno;
    }

    public void setMenuno(String menuno) {
        this.menuno = menuno == null ? null : menuno.trim();
    }

    public String getMenuparentno() {
        return menuparentno;
    }

    public void setMenuparentno(String menuparentno) {
        this.menuparentno = menuparentno == null ? null : menuparentno.trim();
    }

    public Long getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Long menuorder) {
        this.menuorder = menuorder;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public Long getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(Long isvisible) {
        this.isvisible = isvisible;
    }

    public Long getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Long isleaf) {
        this.isleaf = isleaf;
    }

    public String getSystemid() {
        return systemid;
    }

    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }
}