package repositories.report;

import model.Action;

import java.util.List;

public interface ReportRepository {
    List<Action> findAll(Long employeeId);

    boolean save(Action activity, Long employeeId);
}
