import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class ContactUsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/demo";
        String username = "demo";
        String password = "demo";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO contact_form (name, email, message) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, message);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new contact form entry was inserted successfully.");
                // Redirect to a success page or perform other actions as needed
                response.sendRedirect("success.html");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exceptions or errors accordingly
            response.sendRedirect("error.html"); // Redirect to an error page in case of failure
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
