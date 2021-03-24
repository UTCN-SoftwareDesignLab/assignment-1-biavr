package services.report;

import model.Action;
import repositories.report.ReportRepository;

import java.util.List;

public class ReportServiceMySQL implements ReportService{

    private ReportRepository reportRepository;
    @Override
    public List<Action> getActions(Long employeeId) {
        return reportRepository.findAll(employeeId);
    }
}
