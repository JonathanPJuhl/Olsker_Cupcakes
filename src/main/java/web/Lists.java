package web;

/*
@WebServlet("/Lists/*")
public class Lists extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if(req.getPathInfo() == null){
          render("Olsker Cupcakes", "/WEB-INF/pages/createlists.jsp", req, resp);
      } else{
          int shoppingListID = Integer.parseInt(req.getPathInfo().substring(1));
          log(req, "Accessing list: " + " : " + shoppingListID );
          try {
              CupcakeList shoppingList = api.findList(shoppingListID);
              req.setAttribute("list", shoppingList);
              render("Olsker Cupcakes: " + shoppingList.getName(), "/WEB-INF/pages/displayUserPage.jsp", req, resp);
          } catch (NoCupcakeListExists noCupcakeListExists) {
              resp.sendError(404, "Shopping list does not exist");
          }
      }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if(name == null || name.equals("")){
            resp.sendError(400, "Intet navn indskrevet");
        }else{
            CupcakeList list = api.createList(name);
            resp.sendRedirect(req.getContextPath()+ "/Lists/" + list.getId());
        }
    }
}*/
