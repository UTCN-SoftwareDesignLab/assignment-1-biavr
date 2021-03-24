package services.report;

import model.Action;

import java.util.List;

public interface ReportService {
    List<Action> getActions(Long employeeId);
}
