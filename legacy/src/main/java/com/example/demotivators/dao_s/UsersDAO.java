package com.example.demotivators.dao_s;

import com.example.demotivators.entities.Mem;
import com.example.demotivators.entities.User;
import com.example.demotivators.helper_s.HelperForDAO_s;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demotivators.helper_s.HelperForDAO_s.hashString;
import static com.example.demotivators.helper_s.HelperForDAO_s.howManyUsers;

public class UsersDAO {

    public static boolean addUser(User user, String login, String password) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            if(checkUser(login) != null) {
                return false;
            }
            Statement st = conn.createStatement();

            int numberOfUsers = howManyUsers("users");

            st.executeUpdate("INSERT INTO public.users(" +
                    "\"Name\", \"Nickname\", \"Telephone\", \"RegistrationDate\", \"Role\", \"Login\", \"Password\", \"User_id\", \"Image\")\n" +
                    "VALUES (\'"+user.getName()+"\', \'"+user.getNickname()+"\', \'"+
                    user.getTelephone_number()+"\', \'"+
                    (new java.sql.Date(user.getDate_registration().getTime())) +"\', \'"+
                    User.Role.toInt(user.getRole())+"\', \'"+
                    login+"\', \'"+hashString(password)+"\', \'"+numberOfUsers+"\', \'" +user.getAvatar()+ "\');");

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User findUser(int user_id) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE \"User_id\" = \'" + user_id + "\' ;");

            while(rs.next()){
                return new User(rs.getString(9), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getInt(1));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static User checkUser(String login) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            if(howManyUsers("users") <= 0) return null;


            Statement st = conn.createStatement();

            String execute = "SELECT * FROM users WHERE \"Login\" = \'" + login + "\';";

            ResultSet resultSet;

            if(HelperForDAO_s.sizeoOfResultSet(execute) == 0) return null;

            resultSet = st.executeQuery(execute);

            resultSet.next();
            if(resultSet != null) {
                return new User(resultSet.getString(9), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(1));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT  * FROM users");
            resultSet.next();
            do {
                users.add(new User(resultSet.getString(9), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(1)));
            } while(resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public static User checkUser(String login, String password) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            if(howManyUsers("users") <= 0) return null;


            Statement st = conn.createStatement();

            String execute = "SELECT * FROM users WHERE \"Login\" = \'" + login + "\' " + " AND \"Password\" = \'" + hashString(password) + "\';";

            ResultSet resultSet;

            if(HelperForDAO_s.sizeoOfResultSet(execute) == 0) return null;

            resultSet = st.executeQuery(execute);

            resultSet.next();
            if(resultSet != null) {
                return new User(resultSet.getString(9), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(1));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
