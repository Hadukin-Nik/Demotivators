package demotivators.dao_s;

import demotivators.entities.Download;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DownloadDAO {
    public static void insert(Download download) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "postgres")) {

            Statement st = conn.createStatement();

            st.executeUpdate("INSERT INTO public.downloads(" +
                    "\"UserId\", \"CollectionId\", \"CreationDate\", \"UpdateDate\", \"Process\")" +
                    " VALUES (\'"+download.getUserId()+"\', \'"+download.getCollectionId()+"\', \'"+download.getCreationDate()+"\', \'"+download.getUpdateDate()+"\', \'"+0+"\');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
