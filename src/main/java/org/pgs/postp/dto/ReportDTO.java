package org.pgs.postp.dto;

public class ReportDTO {

    private Long reportID;
    private int totalUsers;

    public ReportDTO() {
    }

    public ReportDTO(Long reportID, int totalUsers) {
        this.reportID = reportID;
        this.totalUsers = totalUsers;
    }

    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }

    public int getTotalUsers() {return totalUsers;}

    public void setTotalUsers(int totalUsers) {this.totalUsers = totalUsers;}
}
