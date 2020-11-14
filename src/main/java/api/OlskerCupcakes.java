package api;

import businessLogic.Cupcake;
import domain.shoppinglist.User;
import domain.shoppinglist.UserRepository;
import domain.shoppinglist.NoSuchUserExists;

import java.util.List;

public class OlskerCupcakes {
    private String VERSION = "0.1";
    private final UserRepository userLists;

    public OlskerCupcakes(UserRepository userLists) {

        this.userLists = userLists;
    }

    public Object getVERSION() {
        return VERSION;
    }



    public User findUser(int i) throws NoSuchUserExists {
        return userLists.find(i);
    }

    /*public CupcakeList findList(int i) throws NoCupcakeListExists {
        return userLists.findList(i);
    }*/

    public User createUser(String name, String password) {

        return userLists.createUsr(name, password);
    }

    public User loginUser(String email, String pass) throws NoSuchUserExists {
        return userLists.loginUsr(email, pass);
    }

    public User addFundsToUser(String addFunds, String user) {
        return userLists.addFundsToUser(addFunds, user);
    }

    public List<Cupcake> createCupcakeToppinglist(){
        return userLists.getToppings();
    }
    public List<Cupcake> createCupcakeBottomlist(){
        return userLists.getBottoms();
    }
   /*
    public CupcakeList createList(String name) {

        return userLists.createList(name);
    }*/
}
