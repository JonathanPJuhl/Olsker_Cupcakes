package web;

import domain.shoppinglist.User;
import domain.shoppinglist.NoSuchUserExists;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateUsr/*")
public class CreateUsr extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            render("Olsker Cupcakes", "/WEB-INF/pages/createUsr.jsp", req, resp);
        } else {
            int userID = Integer.parseInt(req.getPathInfo().substring(1));
            log(req, "Accessing user: " + " : " + userID);
            try {
                User userList = api.findUser(userID);
                req.setAttribute("list", userList);
                render("Olsker Cupcakes: " + userList.getName(), "/WEB-INF/pages/displayUserPage.jsp", req, resp);
            } catch (NoSuchUserExists noSuchUserExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        if (email == null || email.equals("")) {
            resp.sendError(400, "Mangler email");
        } else if(pass == null || pass.equals("")){
            resp.sendError(400, "Mangler password");
        } else{
            User list = api.createUser(email, pass);
            resp.sendRedirect(req.getContextPath() + "/CreateUsr/" + list.getId());
        }
    }
}