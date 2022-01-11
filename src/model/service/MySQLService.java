package model.service;

import javafx.collections.FXCollections;
import model.config.SQLConfig;
import model.result.SQLResult;
import model.result.SQLResultTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLService {
    protected Connection conn = null;
    protected String driverClassName = null;
    protected SQLConfig config = null;

    public MySQLService(SQLConfig config) {
        this.driverClassName = "com.mysql.cj.jdbc.Driver";
        this.config = config;
    }

    public String getURL() {
        return "jdbc:mysql://" + config.url + ":" + config.port;
    }

    public boolean getService() {
        String link = getURL();
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.err.println(driverClassName + " can't be loaded");
            e.printStackTrace();
            return false;
        }
        try {
            conn = DriverManager.getConnection(link, config.user, config.password);
        } catch (SQLException e) {
            System.err.println("Can't connect the database");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public SQLResult getResult(String sqlCode) {

        SQLResult sqlResult = new SQLResult();
        StringBuilder sb = new StringBuilder();
        ArrayList<SQLResultTable> resultTables = new ArrayList<SQLResultTable>();
        // the result index
        int cnt = 0;
        // the operation index
        int count = 0;
        for (String query : sqlCode.split(";")) {
            query = query.trim();//remove the space
            if (query == null || query.length() == 0)
                continue;

            // change first word of query statement
            query = changeFirstWordToLowerCase(query);

            int type = getOperationType(query);
            // there exists spelling mistake or
            // the situation this program doesn't consider
            if (type == 0) {
                sb.append("Operation " + (++count) + ": Something wrong! Please check your statement(word spell).\n\n");
                continue;
            }

            // select, show, describe
            if (type == 1) {
                SQLResultTable sqlResultTable = getRetrieveResult(query);
                if (sqlResultTable == null) {
                    sb.append("Operation " + (++count) + ": Failed\n\n");
                } else {
                    sb.append("Operation " + (++count) + ": Successful\n\n");
                    sqlResultTable.name = "Result " + (++cnt);
                    resultTables.add(sqlResultTable);
                }
            }
            // insert, delete, update
            else if (type == 2) {
                String rs = getUpdateResult(query);
                sb.append("Operation " + (++count) + ": " + rs + "\n\n");
            }
            // create, drop, use
            else if (type == 3) {
                String rs = getCreateResult(query);
                sb.append("Operation " + (++count) + ": " + rs + "\n\n");
            }
        }
        sqlResult.info = sb.toString();
        sqlResult.tables = resultTables;
        return sqlResult;
    }

    //change the first word of the query statement to lower case
    //cause mysql can deal with word like from, into and table name in upper case
    //we can just care about first.
    protected String changeFirstWordToLowerCase(String query) {
        if (query.indexOf(' ') == -1)
            return query;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < query.length(); i++) {
            char c = query.charAt(i);
            if (c == ' ') break;
            sb.append(Character.toLowerCase(c));
        }
        sb.append(query, i, query.length());
        return sb.toString();
    }


    /*
     * 0: the operation that this code doesn't implement
     * 1: select or show operation
     * 2: insert, update, alter or delete operation
     * 3: create, drop or use operation
     */
    protected int getOperationType(String query) {
        //find the query statement's first word to judge what kind of operation it is
        String kind = query.split(" ")[0];
        if ("select".equals(kind) || "show".equals(kind) || "describe".equals(kind) || "desc".equals(kind))
            return 1;
        if ("insert".equals(kind) || "update".equals(kind) || "delete".equals(kind) || "alter".equals(kind))
            return 2;
        if ("create".equals(kind) || "drop".equals(kind) || "use".equals(kind))
            return 3;
        return 0;
    }

    // select, show, describe
    protected SQLResultTable getRetrieveResult(String query) {

        SQLResultTable sqlResultTable = new SQLResultTable();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            // table's columns
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int metaCount = resultSetMetaData.getColumnCount();
            sqlResultTable.columns = new ArrayList<String>(metaCount);
            for (int i = 1; i <= metaCount; ++i) {
                sqlResultTable.columns.add(resultSetMetaData.getColumnName(i));
            }

            // table's content
            sqlResultTable.content = FXCollections.observableArrayList();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 1; i <= metaCount; i++) {
                    map.put(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                sqlResultTable.content.add(map);
            }

            return sqlResultTable;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // insert, update, delete, alter no SQLResultTable to return
    protected String getUpdateResult(String query) {

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            int rs = ps.executeUpdate();
            if (rs > 0)
                return new String("Successful!");
            else
                return new String("Failed!");
        } catch (SQLException e) {
            e.printStackTrace();
            return new String("Something wrong! Please check your statement or make sure you have right to do this operation.");
        }
    }

    // create, drop no SQLResultTable to return
    protected String getCreateResult(String query) {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            if (ps.executeUpdate() == 0)
                return new String("Successful!");
            else
                return new String("Failed!");
        } catch (SQLException e) {
            e.printStackTrace();
            return new String("Something wrong! Please check your statement or make sure you have right to do this operation.");
        }
    }

    // close the connection
    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
