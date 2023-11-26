package org.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectPricesQuery {

    private final String FILE_FOR_FIND_PROJECT_PRICE = "src/main/resources/print_project_prices.sql";

    private final Connection connection;

    public ProjectPricesQuery(Connection connection) {
        this.connection = connection;
    }

    public List<Object[]> execute() {
        List<Object[]> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String query = Files.readString(Paths.get(FILE_FOR_FIND_PROJECT_PRICE));

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] result = new Object[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    result[i] = resultSet.getObject(i + 1);
                }

                resultList.add(result);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }
}