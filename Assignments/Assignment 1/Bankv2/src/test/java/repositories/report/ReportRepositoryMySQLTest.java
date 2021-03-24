package repositories.report;

import launcher.ComponentFactory;
import model.Action;
import model.Report;
import model.User;
import model.builder.UserBuilder;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import services.account.AccountServiceMySQL;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReportRepositoryMySQLTest {

    private ReportRepository reportRepository;

    @BeforeClass
    public void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        reportRepository = componentFactory.getReportRepository();
    }

    @Test
    void findAll() {
        assertEquals(0,reportRepository.findAll(1L).size());
    }

    @Test
    void save() {
        assertTrue(reportRepository.save(new Action(3L,"DELETE",new Date()), 2L));
    }
}