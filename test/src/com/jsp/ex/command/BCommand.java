package com.jsp.ex.command;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public interface BCommand {
    
    void execute(HttpServletRequest request, HttpServletResponse response);
    
}
