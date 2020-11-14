package web;

import domain.shoppinglist.NoSuchUserExists;
import domain.shoppinglist.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddBalanceToUser/*")
public class AddBalanceToUser extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null) {
            render("Olsker Cupcakes", "/WEB-INF/pages/addBalanceToUser.jsp", req, resp);
        } else {
            HttpSession session = req.getSession();
            int userID = (Integer)session.getAttribute("sessionId");
            System.out.println(userID);
            log(req, "Accessing user: " + " : " + userID);
            try {
                User userList = api.findUser(userID);
                req.setAttribute("list", userList);
                render("Olsker Cupcakes: " + userList.getName(), "/WEB-INF/pages/adminPage.jsp", req, resp);
            } catch (NoSuchUserExists noSuchUserExists) {
                resp.sendError(404, "User does not exist");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addFunds = req.getParameter("addFunds");
        String user = req.getParameter("user");
        if (addFunds == null || addFunds.equals("0") || addFunds.equals("")) {
            resp.sendError(400, "Skriv venligst det ønskede beløb");
        } else if (user == null || user.equals("")) {
            resp.sendError(400, "Indtast venligst emailen på en bruger");
        } else {
            User list = api.addFundsToUser(addFunds, user);
            HttpSession session = req.getSession();
            int userID = (Integer)session.getAttribute("sessionId");
            System.out.println(userID);
            try {
                User id = api.findUser(userID);
                resp.sendRedirect(req.getContextPath() + "/AddBalanceToUser/" + id.getId());
            } catch (NoSuchUserExists noSuchUserExists) {
                noSuchUserExists.printStackTrace();
            }
           // resp.sendRedirect(req.getContextPath() + "/AddBalanceToUser/" + id.getId());
        }
    }
}