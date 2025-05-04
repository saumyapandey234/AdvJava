package com.servlet;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;




public class SecondServlet extends GenericServlet{
    @Override
    public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException{
        System.out.println("this is servlet using generic servlet");
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h3>This is my second servlet using generic servlet:</h3>");
        
    }
}
