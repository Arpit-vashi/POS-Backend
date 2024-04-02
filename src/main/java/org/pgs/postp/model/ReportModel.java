package org.pgs.postp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Reports")
public class ReportModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportID")
    private Long reportID;

    @Column(name = "TotalUsers")
    @JsonProperty("total_users")
    private int totalUsers;

    public ReportModel() {
    }

    // Getters and Setters
    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }
    public ReportModel(int totalUsers) {
        this.totalUsers = totalUsers;
    }
    public int getTotalUsers() {return totalUsers;}

    public void setTotalUsers(int totalUsers) {this.totalUsers = totalUsers;}

}
