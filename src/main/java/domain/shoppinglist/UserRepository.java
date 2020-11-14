package domain.shoppinglist;

import businessLogic.Cupcake;

import java.util.List;

public interface UserRepository {

    Iterable<User> findAll();
    User find(int id) throws NoSuchUserExists;
    User createUsr(String name, String password);
    User loginUsr(String email, String password) throws NoSuchUserExists;

    User addFundsToUser(String addFunds, String email);

    List<Cupcake> getToppings();

    List<Cupcake> getBottoms();
   /* CupcakeList createList(String name);
    CupcakeList findList(int id) throws NoCupcakeListExists;
*/
}

