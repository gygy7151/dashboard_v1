package com.jsp.ex;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.jsp.ex.command.BCommand;
import com.jsp.ex.command.BContentCommand;
import com.jsp.ex.command.BDeleteCommand;
import com.jsp.ex.command.BListCommand;
import com.jsp.ex.command.BModifyCommand;
import com.jsp.ex.command.BWriteCommand;
 
@WebServlet("*.do")
public class FrontCon extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public FrontCon() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionDo(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionDo(request, response);
    }
    
    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("actionDo");
        request.setCharacterEncoding("UTF-8");
        String viewPage = null;
        BCommand command = null;
        System.out.println("모디파이");
        String uri = request.getRequestURI();
        String conPath = request.getContextPath();
        String com = uri.substring(conPath.length());
        System.out.println("모디파이2");
        if(com.equals("/write_view.do")) {
            viewPage = "write_view.jsp";
        } else if(com.equals("/write.do")) {
            command = new BWriteCommand();
            command.execute(request, response);
            viewPage = "boardList.do";
        } else if(com.equals("/boardList.do")) {
            command = new BListCommand();
            command.execute(request, response);
            viewPage = "boardList.jsp";
        } else if(com.equals("/boardViewContent.do")){
            command = new BContentCommand();
            command.execute(request, response);
            viewPage = "boardViewContent.jsp";
        } else if(com.equals("/boardModify.do")) {
            command = new BContentCommand();
            command.execute(request, response);
            viewPage = "boardModify.jsp";
        } else if(com.equals("/boardContentModify.do")) {
            command = new BModifyCommand();
            command.execute(request, response);
            viewPage = "boardList.do";
        } else if(com.equals("/delete.do")) {
            command = new BDeleteCommand();
            command.execute(request, response);
            viewPage = "boardList.do";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
        dispatcher.forward(request, response);
        
    }
 
}