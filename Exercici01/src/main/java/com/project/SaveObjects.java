package com.project;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SaveObjects {
    protected abstract void save(Connection conn) throws SQLException;
}
