package com.example.demotivators.helper_s;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demotivators.dao_s.MemesDAO;
import com.example.demotivators.entities.Mem;
import com.example.demotivators.entities.forPages.MemWithUser;
import org.apache.commons.codec.binary.Hex;

public class HelperForDAO_s {
    public static int howManyUsers(String table_name) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("Select " +
                    "   case when count(*)>=0 then count(*) else 0 end " +
                    "from " + table_name);

            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public static String hashString(String originalString) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] hashbytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

        return Hex.encodeHexString(hashbytes);
    }

    public static int sizeoOfResultSet(String executeQuerry) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {
            Statement st = conn.createStatement();

            ResultSet resultSet = st.executeQuery(executeQuerry);
            int rowCount = 0;

            if(resultSet == null || !resultSet.isBeforeFirst()) return 0;

            while (resultSet.next()) {
                rowCount++;
            }

            return rowCount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
