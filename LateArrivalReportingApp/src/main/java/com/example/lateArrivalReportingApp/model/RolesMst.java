package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * ロールマスタ.
 *
 * 閲覧権限の範囲を管理する。
 * 
 */
@Entity
@Table(name = "ROLES_MST")
public class RolesMst {

    @Id
    @Column(name = "ROLES_ID", length = 20, nullable = false, unique = true)
    private String rolesId;

    @Column(name = "PERSON", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String person = "0";

    @Column(name = "TEAM", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String team = "0";

    @Column(name = "UNIT", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String unit = "0";

    @Column(name = "OTHER_UNIT", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String otherUnit = "0";

    @Column(name = "MANAGER", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String manager = "0";

    @Column(name = "LEADER", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String leader = "0";

    @Column(name = "CHIEF", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String chief = "0";

    @Column(name = "GENERAL_AFFAIRS", columnDefinition = "char(1) default '0'", length = 1, nullable = false)
    private String generalAffairs = "0";

    public RolesMst() {
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOtherUnit() {
        return otherUnit;
    }

    public void setOtherUnit(String otherUnit) {
        this.otherUnit = otherUnit;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getGeneralAffairs() {
        return generalAffairs;
    }

    public void setGeneralAffairs(String generalAffairs) {
        this.generalAffairs = generalAffairs;
    }
}