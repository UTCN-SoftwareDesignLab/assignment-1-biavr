package launcher;

import controller.LoginController;
import database.DBConnectionFactory;
import repositories.account.AccountRepository;
import repositories.account.AccountRepositoryMySQL;
import repositories.client.ClientRepository;
import repositories.client.ClientRepositoryMySQL;
import repositories.report.ReportRepository;
import repositories.report.ReportRepositoryMySQL;
import repositories.security.RightsRolesRepository;
import repositories.security.RightsRolesRepositoryMySQL;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryMySQL;
import services.user.AuthenticationService;
import services.user.AuthenticationServiceMySQL;
import view.LoginView;

import java.sql.Connection;

/**
 * Created by Bianca on 20/03/2021.
 */
public class ComponentFactory {

    private final LoginView loginView;

    private final LoginController loginController;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.reportRepository = new ReportRepositoryMySQL(connection);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.loginView = new LoginView();
        this.loginController = new LoginController(loginView, authenticationService);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public AccountRepository getAccountRepository(){
        return accountRepository;
    }

    public ClientRepository getClientRepository(){
        return clientRepository;
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }
}
