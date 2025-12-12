package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * コードマスタ.
 *
 * システムで使用するコードを管理する。
 * 
 */
@Entity
@Table(name = "CODE_MST")
@IdClass(CodeMstId.class)
public class CodeMst {

    @Id
    @Column(name = "GROUP_ID", length = 20, nullable = false)
    private String groupId;

    @Id
    @Column(name = "CODE_ID", length = 3, nullable = false)
    private String codeId;

    @Column(name = "CODE_NAME", length = 30)
    private String codeName;

    @Column(name = "REMARK", length = 100)
    private String remark;

    public CodeMst() {
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
