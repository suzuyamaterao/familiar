package com.example.lateArrivalReportingApp.model;

import jakarta.persistence.*;

/**
 * チームマスタ.
 *
 * チーム及びセクションと所属するユニットを管理する。
 * 
 */
@Entity
@Table(name = "TEAM_MST")
public class TeamMst {

    @Id
    @Column(name = "TEAM_ID", length = 5, nullable = false)
    private String teamId;

    @Column(name = "TEAM_NAME", length = 50, nullable = false)
    private String teamName;

    @Column(name = "UNIT_NO", length = 1, nullable = false)
    private String unitNo;

    public TeamMst() {
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }
}
