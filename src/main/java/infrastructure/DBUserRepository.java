package infrastructure;

import businessLogic.Bottom;
import businessLogic.Cupcake;
import businessLogic.Topping;
import domain.shoppinglist.User;
import domain.shoppinglist.UserRepository;
import domain.shoppinglist.NoSuchUserExists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUserRepository implements UserRepository {
    private final Database db;

    public DBUserRepository(Database db){

        this.db = db;
    }

    @Override
    public Iterable<User> findAll() {

        return null;
    }

    @Override
    public User find(int id) throws NoSuchUserExists {
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM user WHERE id=?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if(set.next()) {
                return parseUsrList(set);
            }else {
                throw new RuntimeException("Unexpected error");
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoSuchUserExists();
        }

    }
    /*
    @Override
    public CupcakeList findList(int id) throws NoCupcakeListExists {
        try (Connection conn = db.connect()){
            String sql = "SELECT * FROM shoppinglist WHERE id=?";
            var smt = conn.prepareStatement(sql);
            smt.setInt(1, id);
            smt.executeQuery();
            ResultSet set = smt.getResultSet();
            if(set.next()) {
                return parseList(set);
            }else {
                throw new RuntimeException("Unexpected error");
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new NoCupcakeListExists();
        }

    }*/

    /*private CupcakeList parseList(ResultSet set) throws SQLException {
        return new CupcakeList(set.getInt("shoppinglist.id"),
                set.getString("shoppinglist.name")
        );
    }*/
    private User parseUsrList(ResultSet set) throws SQLException {
        return new User(set.getInt("user.id"),
                set.getString("user.email"),
                set.getString("user.password"),
                set.getDouble("user.balance"),
                set.getBoolean("user.isAdmin")
        );
    }




    @Override
    public User createUsr(String email, String password) {
        int newID;
        SHA256 encrypt = new SHA256();
        String encryptedPass = encrypt.sha256(password);
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO user (email, password, isAdmin) VALUES (?, ?, 0)";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setString(1, email);
            smt.setString(2, encryptedPass);
            smt.executeUpdate();
            ResultSet set = smt.getGeneratedKeys();
            if(set.next()) {
                newID = set.getInt(1);
            }else {
                throw new RuntimeException("Unexpected error");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        try {
            return find(newID);
        } catch (NoSuchUserExists e) {
            throw new RuntimeException();
        }

    }
    @Override
    public User loginUsr(String email, String password) throws NoSuchUserExists {
        SHA256 encrypt = new SHA256();
        String encryptedPass = encrypt.sha256(password);
        String passCheck = "";
        boolean isAdmin = false;
        int id = 0;

        try (Connection conn = db.connect()) {
            String sql = "SELECT * FROM user WHERE email = ?";
            var smt = conn.prepareStatement(sql);
            smt.setString(1, email);
            ResultSet set = smt.executeQuery();
            System.out.println(smt.toString());
            if(set.next()) {
                 id = parseUsrList(set).getId();
                 passCheck = parseUsrList(set).getPassword();
                System.out.println("pass: " + passCheck);
            }
            System.out.println("ID: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(encryptedPass + "Comparrison: " + passCheck);
            if (encryptedPass.equals(passCheck)) {
                try {
                    return find(id);
                } catch (NoSuchUserExists e) {
                    throw new RuntimeException();
                }
            } else {
                System.out.println("You returned null");
                return null;
            }



    }
    @Override
    public User addFundsToUser(String addFunds, String email) {
        double totalFunds = 0;
        double tempFunds = 0;
        int tempId = 0;
        try (Connection conn = db.connect()) {
            String sql = "UPDATE user set balance=? WHERE email=?";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            String sql2 = "SELECT * FROM user WHERE email = ?";
            var smt2 = conn.prepareStatement(sql2);
            smt2.setString(1, email);
            ResultSet set = smt2.executeQuery();
            if(set.next()) {
                tempFunds = parseUsrList(set).getBalance();
                tempId = parseUsrList(set).getId();
            }
            totalFunds = Double.parseDouble(addFunds)+tempFunds;
            smt.setDouble(1, totalFunds);
            smt.setString(2, email);
            smt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return find(tempId);
        } catch (NoSuchUserExists noSuchUserExists) {
            noSuchUserExists.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Cupcake> getToppings(){
        List<Cupcake> listCategory = new ArrayList<>();

        try (Connection conn = db.connect())  {
            String sql = "SELECT * FROM cupcakeTops ORDER BY ingredient";
            var smt = conn.prepareStatement(sql);
            ResultSet result = smt.executeQuery(sql);

            while (result.next()) {
                String top = result.getString("cupcakeTops.ingredient");
                double price = result.getDouble("cupcakeTops.price");
                int id = result.getInt("cupcakeTops.id");
                Cupcake cupcake = new Topping(id, price, top);
                listCategory.add(cupcake);
            }
            System.out.println("Topping: " + listCategory.size());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listCategory;
    }
    @Override
    public List<Cupcake> getBottoms(){
        List<Cupcake> listCategory = new ArrayList<>();

        try (Connection conn = db.connect())  {
            String sql = "SELECT * FROM cupcakeBottoms ORDER BY ingredient";
            var smt = conn.prepareStatement(sql);
            ResultSet result = smt.executeQuery(sql);

            while (result.next()) {
                String bottom = result.getString("cupcakeBottoms.ingredient");
                double price = result.getDouble("cupcakeBottoms.price");
                int id = result.getInt("cupcakeBottoms.id");
                Cupcake cupcake = new Bottom(id, price, bottom);
                listCategory.add(cupcake);
            }
            System.out.println("Bottom: "+ listCategory.size());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listCategory;
    }
    /*
    @Override
    public CupcakeList createList(String name) {
        int newID;
        try(Connection conn = db.connect()){
            String sql = "INSERT INTO shoppinglist (name) VALUES (?)";
            var smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            smt.setString(1, name);
            smt.executeUpdate();
            ResultSet set = smt.getGeneratedKeys();
            if(set.next()) {
                newID = set.getInt(1);
            }else {
                throw new RuntimeException("Unexpected error");
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        try {
            return findList(newID);
        } catch (NoCupcakeListExists e) {
            throw new RuntimeException();
        }

    }*/
}
