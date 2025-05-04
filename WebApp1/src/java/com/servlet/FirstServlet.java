package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import java.util.Date;


public class FirstServlet  implements Servlet{
    //Life Cycle method;
    ServletConfig conf;
    public void init(ServletConfig conf){
        this.conf=conf;
        System.out.println("Creating object....");
    }
    public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
        System.out.println("Servicing ........");
        //set the content type of the response
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h3>This is my output from servlet method:</h3>");
        out.println("<h3>Today's date and time is " + new Date().toString() + "</h3>");
    }
    public void destroy(){
        System.out.print("Going to destroy servlet object...");
    }
    
    //nonlifecycle methods(when we call them,then only they gets executed);
    public ServletConfig getServletConfig(){
        return this.conf;
    }
    public String getServletInfo(){
        return "this servlet is created by Saumya Pandey";
    }
}

