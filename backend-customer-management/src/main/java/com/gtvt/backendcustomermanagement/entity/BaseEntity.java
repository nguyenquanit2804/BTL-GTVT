package com.gtvt.backendcustomermanagement.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Basic
    @Column(name = "STATUS")
    protected Long status;

    @Basic
    @Column(name = "CREATE_DATE", updatable = false)
    protected Date createDate;

    @CreatedBy
    @Basic
    @Column(name = "CREATE_BY", updatable = false)
    protected String createBy;

    @Basic
    @Column(name = "UPDATE_DATE")
    protected Date updateDate;

    @LastModifiedBy
    @Basic
    @Column(name = "UPDATE_BY")
    protected String updateBy;



    @Basic
    @Column(name = "CREATE_DATE", updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATE_BY", updatable = false)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "UPDATE_BY")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    @Basic
    @Column(name = "UPDATE_DATE")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "STATUS")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @PrePersist
    protected void prePersist() {
        this.createDate = new Date();
        this.updateDate = new Date();
        String username = "admin";
        this.createBy = username;
        this.updateBy = username;
    }

    @PreUpdate
    private void preUpdate() {
        this.updateDate = new Date();
        this.updateBy = "admin";
    }
}
