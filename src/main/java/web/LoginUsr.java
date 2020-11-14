package web;

        import domain.shoppinglist.User;
        import domain.shoppinglist.NoSuchUserExists;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

@WebServlet("/LoginUsr/*")
public class LoginUsr extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null) {
            render("Olsker Cupcakes", "/WEB-INF/pages/loginUsr.jsp", req, resp);
        } else {

            int userID = Integer.parseInt(req.getPathInfo().substring(1));
                HttpSession session = req.getSession(true);
                session.setAttribute("sessionId", userID);
                log(req, "Accessing user: " + " : " + userID);

                try {
                    User userList = api.findUser(userID);
                    req.setAttribute("list", userList);
                    if(userList.isAdmin()==true){
                        render("Olsker Cupcakes: " + userList.getName(), "/WEB-INF/pages/adminPage.jsp", req, resp);
                    }
                    renderUser("Olsker Cupcakes: " + userList.getName(), "/WEB-INF/pages/displayUserPage.jsp", userID, req, resp);
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
            try {
                User list = api.loginUser(email, pass);
                resp.sendRedirect(req.getContextPath() + "/LoginUsr/" + list.getId());
            } catch (NoSuchUserExists noSuchUserExists) {
                noSuchUserExists.printStackTrace();
            }

        }
    }
}