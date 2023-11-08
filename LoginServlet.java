import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    // Update these details with your actual database connection parameters
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; // For example: ""
    static final String DB_URL = "jdbc:mysql://localhost:3306/demo"; // For example: "jdbc:mysql://localhost:3306/your_db_name";
    static final String USER = "demo";
    static final String PASS = "demo";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the data sent from the frontend
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                // Successful login - user found in the database
                response.setStatus(HttpServletResponse.SC_OK); // Send a success status
            } else {
                // Failed login - user not found in the database or wrong credentials
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Send an unauthorized status
            }

            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources in finally block
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
