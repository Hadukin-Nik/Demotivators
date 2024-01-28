package com.example.demotivators.dao_s;

import com.example.demotivators.entities.Mem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demotivators.helper_s.HelperForDAO_s.howManyUsers;

public class MemesDAO {
    public static void InsertMem(Mem mem) {
        int id = howManyUsers("memes");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();
            java.sql.Date sqlDate = new java.sql.Date(mem.getCreationDate().getTime());

            st.executeUpdate("INSERT INTO public.memes(\n" +
                            "        \"Mem_id\", \"User_id\", \"Picture\", \"CreationDate\", \"UpdateDate\", \"CommentsAllowed\", \"Description\", \"Tags\")" +
                    "VALUES ("+ id + ", " + mem.getUserId() + ", \'" +
                    mem.getPicture() + "\', \'" + (sqlDate) + "\', \'" +
                    (sqlDate)+ "\', \'" +
                    mem.isCommentsAllowed() + "\', \'" + mem.getDescription() + "\',\' " + mem.getTags() +"\')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Mem> getMemes() {
        List<Mem> mems = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM memes");

            while(rs.next()){
                mems.add(new Mem(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(7), rs.getString(8),
                        rs.getDate(4), rs.getDate(5), rs.getBoolean(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mems;
    }

    public static Mem findMemInDB(int memID) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM memes WHERE \"Mem_id\" = \'" + memID + "\' ;");

            while(rs.next()){
                return  new Mem(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(7), rs.getString(8),
                        rs.getDate(4), rs.getDate(5), rs.getBoolean(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void update(Mem mem) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

            st.executeUpdate("UPDATE public.memes" +
                    " SET \"UpdateDate\"=\'" +sqlDate + "\', \"CommentsAllowed\"=\'"+mem.isCommentsAllowed()+"\', \"Description\" = \'"+mem.getDescription()+"\', \"Tags\" = \'" + mem.getTags() + "\'" +
                    " WHERE \"Mem_id\"=\'"+mem.getMemId()+"\';");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int memId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            st.executeUpdate("DELETE FROM public.memes" +
                    " WHERE \"Mem_id\"=\'"+memId+"\';");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
