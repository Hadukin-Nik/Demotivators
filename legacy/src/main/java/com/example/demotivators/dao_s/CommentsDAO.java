package com.example.demotivators.dao_s;

import com.example.demotivators.entities.Comment;
import com.example.demotivators.entities.Mem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demotivators.helper_s.HelperForDAO_s.howManyUsers;

public class CommentsDAO {
    public static void insert(Comment comment) {
        int id = howManyUsers("comments");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();
            java.sql.Date sqlDate = new java.sql.Date(comment.getDate().getTime());

            st.executeUpdate("INSERT INTO public.comments(" +
                            "\"Comment_id\", \"Mem_id\", \"User_id\", \"Date\", \"Text\", \"Likes\", \"Dislikes\")" +
                    "VALUES ("+ id + ", " + comment.getMemId() + ", \'" +
                    comment.getUserId() + "\', \'" + (sqlDate) + "\', \'" +
                    comment.getText() + "\', \'" + 0 + "\',\' " + 0 +"\')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Comment> getAllComments(int memId) {
        List<Comment> comments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM comments WHERE \"Mem_id\" = \'"+ memId +"\';");

            while(rs.next()){
                comments.add(new Comment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }
}
