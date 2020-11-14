package domain.shoppinglist;


import java.util.Objects;

public class User {
    private final int id;
    private final String email;
    private final String password;
    private final double balance;
    private final boolean isAdmin;



    public User(int id, String name, String password, double balance, boolean isAdmin){
        this.id = id;
        this.email = name;
        this.password=password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return id==that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CupcakeList{" +
                "id=" + id +
                ", name='" + email + '\'' +
                '}';
    }
}
