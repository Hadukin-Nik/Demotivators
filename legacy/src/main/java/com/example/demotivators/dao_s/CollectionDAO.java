package com.example.demotivators.dao_s;

import com.example.demotivators.entities.Collection;
import com.example.demotivators.entities.Mem;
import com.example.demotivators.entities.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demotivators.helper_s.HelperForDAO_s.howManyUsers;

public class CollectionDAO {
    private static int lastId;

    public static int getLasttId() {
        return lastId;
    }

    public static List<Collection> getCollections() {
        List<Collection> collections = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM \"collections\"");

            while(rs.next()){
                collections.add(new Collection(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return collections;
    }
    public static Collection findCollection(int collectionId) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM \"collections\" WHERE \"Collection_id\" = \'" + collectionId + "\' ;");

            while(rs.next()){
                return new Collection(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getBoolean(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public static void insertNewCollection(Collection collection) {
        int id = howManyUsers("\"collections\"");
        lastId = id;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            st.executeUpdate("INSERT INTO public.collections(" +
                    "\"Collection_id\", \"Creator_id\", \"Name\", \"Description\", \"Image\", \"IsPrivate\")" +
                    "VALUES (\'"+id+"\', \'"+collection.getCreatorId()+"\', \'"+collection.getName()+"\', \'"+
                    collection.getDescription()+"\', \'"+collection.getImage()+"\', \'"+collection.isPrivate()+"\');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertMemToCollection(int memId, int collectionId){
        if(!isMemInCollection(memId, collectionId))

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            st.executeUpdate("INSERT INTO public.\"memToCollection\"(\n" +
                    "\t\"Mem_id\", \"Collection_id\")\n" +
                    "\tVALUES (\'"+memId+"\', \'"+collectionId+"\');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isMemInCollection(int memId, int collectionId){
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM public.\"memToCollection\"" +
                    " WHERE \"Mem_id\"=\'"+memId+"\' AND \"Collection_id\" = \'"+collectionId+"\';");

            if(rs.isBeforeFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMemFromCollection(int memId, int collectionId){
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            st.executeUpdate("DELETE FROM public.\"memToCollection\"" +
                    " WHERE \"Mem_id\"=\'"+memId+"\' AND \"Collection_id\" = \'"+collectionId+"\';");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
