package com.example.demotivators.dao_s;

import com.example.demotivators.entities.Request;
import com.example.demotivators.entities.User;
import com.example.demotivators.entities.forPages.RequestWithUser;
import com.example.demotivators.helper_s.HelperForDAO_s;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demotivators.helper_s.HelperForDAO_s.howManyUsers;

public class FriendshipRequestsDAO {
    public static List<RequestWithUser> getApprovedRequestsWithUser(int id) {
        List<Request> req = getRequestsToUser(id);
        List<RequestWithUser> ans = new ArrayList<>();

        for (var r : req) {
            if (r.isApproved()) {
                ans.add(new RequestWithUser(r));
            }
        }

        return ans;
    }


    public static List<RequestWithUser> getRequestsWithUser(int id) {
        List<Request> req = getRequestsToUser(id);
        List<RequestWithUser> ans = new ArrayList<>();

        for (var r : req) {
            if(!r.isApproved())
            ans.add(new RequestWithUser(r));
        }

        return ans;
    }

    public static List<Request> getRequestsToUser(int id) {
        List<Request> requests = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM \"friendshipRequests\" WHERE \"ToUserId\" = \'" + id + "\' ;");

            while (rs.next()) {
                requests.add(new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getBoolean(7), rs.getBoolean(8)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return requests;
    }

    public static boolean isInFriends(int firstId, int secondId) {
        boolean ans;

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            String query = "SELECT * FROM \"friendshipRequests\" WHERE \"ToUserId\" = \'" + firstId + "\' AND \"FromUserId\" = \'"+secondId+"\' AND \"Status_isApproved\" = \'"+true+"\';";
            ans = HelperForDAO_s.sizeoOfResultSet(query) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ans;
    }

    public static void insert(Request request) {
        int id = howManyUsers("\"friendshipRequests\"");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();
            java.sql.Date sqlDate = new java.sql.Date(request.getCreationDate().getTime());

            st.executeUpdate("INSERT INTO public.\"friendshipRequests\"(\n" +
                    "\t\"ToUserId\", \"FromUserId\", \"RequestId\", \"Text\", \"Create_date\", \"Update_date\", \"Status_isWatched\", \"Status_isApproved\")\n" +
                    "\tVALUES (\'"+request.getToUserId()+"\', \'"+request.getFromUserId()+"\', \'"+id+"\', \'"+request.getText()+"\', \'"+sqlDate+"\', \'"+sqlDate+"\', \'"+false+"\', \'"+false+"\');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Request request) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            st.executeUpdate("DELETE FROM public.\"friendshipRequests\"" +
                    " WHERE \"RequestId\"=\'"+request.getRequestId()+"\';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Request find(int id) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM public.\"friendshipRequests\"" +
                    " WHERE \"RequestId\"=\'"+id+"\';");

            if(rs.isBeforeFirst()) {
                rs.next();

                return new Request(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getString(4), rs.getDate(5), rs.getDate(6),
                        rs.getBoolean(7), rs.getBoolean(8));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Request request) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

            if(request.isApproved()) {
                insert(new Request(request.getRequestId(), request.getFromUserId(), request.getToUserId(),
                        request.getText(), request.getCreationDate(), sqlDate, request.isWatched(), request.isApproved()));
            }

            st.executeUpdate("UPDATE public.\"friendshipRequests\"" +
                    "SET \"ToUserId\"=\'"+request.getToUserId()+"\', \"FromUserId\"=\'"+request.getFromUserId()+"\', " +
                    "\"RequestId\"=\'"+request.getRequestId()+"\', \"Text\"=\'"+request.getText()+"\', \"Create_date\"=\'"+request.getCreationDate()+"\', " +
                    "\"Update_date\"=\'"+sqlDate+"\', \"Status_isWatched\"=\'"+request.isWatched()+"\', \"Status_isApproved\"=\'"+request.isApproved()+"\'" +
                    " WHERE \"RequestId\"=\'"+request.getRequestId()+"\';");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
