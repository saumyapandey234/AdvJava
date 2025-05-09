
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part; //interface, which is part of the Servlet 3.0+ API.

@MultipartConfig 
//this annotation is used to show to jvm that our servlet contains multiple part like img,text,and some other field.
public class Register extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
            //getting all the incoming detail from the request
            String name=request.getParameter("user_name");
            String email=request.getParameter("user_email");
            String password=request.getParameter("user_password");
            Part part=request.getPart("image");
            String filename=part.getSubmittedFileName();
//            out.println(filename);
            
            
            
            //connection.....
            try{
            Thread.sleep(3000);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","12345");
            //query
            String q="insert into user (name,password,email,image) values(?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(q);
            pst.setString(1,name);
            pst.setString(2,password);
            pst.setString(3,email);
            pst.setString(4,filename);
            pst.executeUpdate();
            
            //uploading file...
            InputStream is=part.getInputStream();
            byte []data=new byte[is.available()];
            is.read(data);
            String path=request.getRealPath("/")+"img"+File.separator+filename;  
//          \\is used to add in windows and / to add in linux so we have t make it platform independent so we use File.separator.
            out.println(path);
            
            FileOutputStream fos=new FileOutputStream(path);
            fos.write(data);
            fos.close();
            
            
            out.println("done");
            
            
            }catch(Exception e){
                e.printStackTrace();
                out.println("error");
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
