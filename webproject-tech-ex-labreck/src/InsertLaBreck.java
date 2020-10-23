/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertLaBreck")
public class InsertLaBreck extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertLaBreck() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
      String year = request.getParameter("year");
      String director = request.getParameter("director");
      String genre = request.getParameter("genre");

      Connection connection = null;
      String insertSql = " INSERT INTO TechExerciseLaBreckTable (id, TITLE, YEAR, DIRECTOR, GENRE) values (default, ?, ?, ?, ?)";

      try {
         DBConnectionLaBreck.getDBConnection();
         connection = DBConnectionLaBreck.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, title);
         preparedStmt.setString(2, year);
         preparedStmt.setString(3, director);
         preparedStmt.setString(4, genre);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String inserting = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + inserting + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + inserting + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Title</b>: " + title + "\n" + //
            "  <li><b>Year</b>: " + year + "\n" + //
            "  <li><b>Director</b>: " + director + "\n" + //
            "  <li><b>Genre</b>: " + genre + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject-tech-ex-labreck/search_labreck.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
