package org.example.database;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    Files.readString(Path.of("src/main/resources/main/resources/init_db.sql"), StandardCharsets.UTF_8)
            );
            preparedStatement.execute();
        }  catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}



