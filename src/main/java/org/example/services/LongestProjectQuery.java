package org.example.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LongestProjectQuery {

    private static final String FIND_LONGEST_PROJECT_SQL_PATH = "src/main/resources/find_longest_project.sql";

    public static List<Object[]> execute(Connection connection) {
        List<Object[]> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = readSqlQuery(FIND_LONGEST_PROJECT_SQL_PATH);

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] result = new Object[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    result[i] = resultSet.getObject(i + 1);
                }

                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    private static String readSqlQuery(String filePath) {
        StringBuilder query = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return query.toString();
    }
}