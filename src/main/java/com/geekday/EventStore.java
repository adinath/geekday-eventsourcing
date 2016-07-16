package com.geekday;


import com.geekday.common.DomainEvent;

import java.sql.*;
import java.time.LocalDateTime;

import static com.geekday.DatabaseUtils.getConnection;

public class EventStore {

    private static EventStore eventStore;

    private EventStore() {

    }

    public static EventStore initialize() {
        if (eventStore == null) {
            try {
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                statement.execute("create table events(type varchar(50), payload varchar(500), createdAt date)");
                connection.commit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            eventStore = new EventStore();
        }
        return eventStore;

    }

    public void addEvent(DomainEvent event) {
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("insert into events values(?, ?, ?)");
            ps.setString(1, event.getType());
            ps.setString(2, event.getJson());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
