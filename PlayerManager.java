import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class PlayerManager implements PlayerOperations {
    private final Connection connection;

    public PlayerManager(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(dbUrl, user, password);
    }

    @Override
    public void addPlayer(Player player) {
        String sql = "INSERT INTO players (id, name, age, position, asal_negara, status, grade, gaji) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, player.getId());
            stmt.setString(2, player.getName());
            stmt.setInt(3, player.getAge());
            stmt.setString(4, player.getPosition());
            stmt.setString(5, player.getAsalNegara());
            stmt.setString(6, player.getStatus());
            stmt.setString(7, player.getGrade());
            stmt.setDouble(8, player.getGaji());
            stmt.executeUpdate();
            System.out.println("Player added successfully!");
        } catch (SQLException e) {
            System.err.println("Error adding player: " + e.getMessage());
        }
    }

    @Override
    public void viewPlayers() {
        String sql = "SELECT * FROM players";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(new Player(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("position"),
                    rs.getString("asal_negara"),
                    rs.getString("status"),
                    rs.getString("grade"),
                    rs.getDouble("gaji")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error viewing players: " + e.getMessage());
        }
    }

    @Override
    public void updatePlayer(int id, String name, int age, String position, String asalNegara, String status, String grade, double gaji) {
        String sql = "UPDATE players SET name = ?, age = ?, position = ?, asal_negara = ?, status = ?, grade = ?, gaji = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, position);
            stmt.setString(4, asalNegara);
            stmt.setString(5, status);
            stmt.setString(6, grade);
            stmt.setDouble(7, gaji);
            stmt.setInt(8, id);
            stmt.executeUpdate();
            System.out.println("Player updated successfully!");
        } catch (SQLException e) {
            System.err.println("Error updating player: " + e.getMessage());
        }
    }

    @Override
    public void deletePlayer(int id) {
        String sql = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Player deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting player: " + e.getMessage());
        }
    }

    @Override
    public double calculateTotalSalary() {
        String sql = "SELECT SUM(gaji) AS total_gaji FROM players";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total_gaji");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total salary: " + e.getMessage());
        }
        return 0;
    }
}