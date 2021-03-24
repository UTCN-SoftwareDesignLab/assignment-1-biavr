package repositories.report;

import model.Action;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryMySQL implements ReportRepository{

    private final Connection connection;

    public ReportRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Action> findAll(Long employeeId) {
        List<Action> actions = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String sql = "Select * from report where employee_id = " + employeeId.toString();
            ResultSet rs= statement.executeQuery(sql);

            while(rs.next()){
                actions.add(getActionFromResultSet(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return actions;
    }

    private Action getActionFromResultSet(ResultSet rs) throws SQLException {
        return new Action(rs.getLong("id"),rs.getString("activity"),rs.getDate("date"));
    }

    @Override
    public boolean save(Action activity, Long employeeId) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO report values (null, ?, ?, ?)");
            insertStatement.setLong(1, employeeId);
            insertStatement.setString(2, activity.getType());
            insertStatement.setDate(3, (Date) activity.getDate());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
