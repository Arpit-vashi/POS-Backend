package org.pgs.postp.mapper;

import org.pgs.postp.dto.ReportDTO;
import org.pgs.postp.dto.UserDTO;
import org.pgs.postp.model.ReportModel;
import org.pgs.postp.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    // Convert ReportModel to ReportDTO
    public ReportDTO toDTO(ReportModel model) {
        ReportDTO dto = new ReportDTO();
        dto.setReportID(model.getReportID());
        dto.setTotalUsers(model.getTotalUsers());
        return dto;
    }

    public ReportModel toEntity(ReportDTO reportDTO) {

        ReportModel reportModel = new ReportModel();
        reportModel.setReportID(reportDTO.getReportID());
        reportModel.setTotalUsers(reportDTO.getTotalUsers());
        return reportModel;
    }
}
