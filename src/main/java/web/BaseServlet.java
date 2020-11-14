package web;

import api.OlskerCupcakes;
import infrastructure.DBUserRepository;
import infrastructure.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class BaseServlet extends HttpServlet {

    protected static final OlskerCupcakes api;

    //Dette er gjort på dette format, da vi ikke har lyst til at instantiere et nyt API hver gang render køres, i det
    //kan give problemer med at dele det imellem vores servlets. Til dette bruges en class-constructor, fordi emnet
    //er static.
    static {
        Database db = new Database();
        api = createCupcake();
    }
    private static OlskerCupcakes createCupcake() {
        Database db = new Database();
        return new OlskerCupcakes(new DBUserRepository(db));

    }

    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("version", api.getVERSION());
        req.setAttribute("title", title);
        req.setAttribute("content", content);
       // req.setAttribute("image", this.getClass().getResource("cupcake.jpg"));
        req.getRequestDispatcher("/WEB-INF/base/base.jsp").forward(req,resp);
    }
    protected void renderUser(String title, String content, int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("version", api.getVERSION());
        req.setAttribute("title", title);
        req.setAttribute("content", content);
        HttpSession session = req.getSession(true);
        session.setAttribute("id", id);
        // req.setAttribute("image", this.getClass().getResource("cupcake.jpg"));
        req.getRequestDispatcher("/WEB-INF/base/base.jsp").forward(req,resp);
    }
    public void log(HttpServletRequest req, String message){
        System.err.println("(" + LocalDateTime.now() +  "): "  + this.getClass() + " : " + req.getRequestURI() + " : " + message);
    }

}
