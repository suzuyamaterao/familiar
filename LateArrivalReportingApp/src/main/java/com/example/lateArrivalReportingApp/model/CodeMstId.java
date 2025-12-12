package com.example.lateArrivalReportingApp.model;

import java.io.Serializable;
import java.util.Objects;

public class CodeMstId implements Serializable {
    private String groupId;
    private String codeId;

    public CodeMstId() {
    }

    public CodeMstId(String groupId, String codeId) {
        this.groupId = groupId;
        this.codeId = codeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CodeMstId))
            return false;
        CodeMstId that = (CodeMstId) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(codeId, that.codeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, codeId);
    }
}