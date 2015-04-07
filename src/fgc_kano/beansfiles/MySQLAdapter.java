/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fgc_kano.beansfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root
 */
@SuppressWarnings("serial")
public class MySQLAdapter extends AbstractTableModel {

    /**
     * An adaptor, transforming the JDBC interface to the TableModel interface.
     *
     * @author Philip Milne
     */
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String[] columnNames = {};
    List<List<Object>> rows = new ArrayList<>();
    ResultSetMetaData metaData;

    public MySQLAdapter() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Opening db connection");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/fgc_kano","root" , "toor");
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot find the database driver classes.");
            System.err.println(ex);
        } catch (SQLException ex) {
            System.err.println("Cannot connect to this database.");
            System.err.println(ex);
        }
    }

    public void executeQuery(String query) {
        if (connection == null || statement == null) {
            System.err.println("There is no database to execute the query.");
            return;
        }
        try {
            resultSet = statement.executeQuery(query);
            metaData = resultSet.getMetaData();

            int numberOfColumns = metaData.getColumnCount();
            columnNames = new String[numberOfColumns];
            // Get the column names and cache them.
            // Then we can close the connection.
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames[column] = metaData.getColumnLabel(column + 1);
            }

            // Get all rows.
            rows = new ArrayList<List<Object>>();
            while (resultSet.next()) {
                List<Object> newRow = new ArrayList<Object>();
                for (int i = 1; i <= getColumnCount(); i++) {
                    newRow.add(resultSet.getObject(i));
                }
                rows.add(newRow);
            }
            //  close(); Need to copy the metaData, bug in jdbc:odbc driver.

            // Tell the listeners a new table has arrived.
            fireTableChanged(null);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void close() throws SQLException {
        System.out.println("Closing db connection");
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    //////////////////////////////////////////////////////////////////////////
    //
    //             Implementation of the TableModel Interface
    //
    //////////////////////////////////////////////////////////////////////////
    // MetaData
    @Override
    public String getColumnName(int column) {
        if (columnNames[column] != null) {
            return columnNames[column].toUpperCase();
        } else {
            return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        int type;
        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return super.getColumnClass(column);
        }

        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                return String.class;

            case Types.BIT:
                return Boolean.class;

            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                return Integer.class;

            case Types.BIGINT:
                return Long.class;

            case Types.FLOAT:
            case Types.DOUBLE:
                return Double.class;

            case Types.DATE:
                return java.sql.Date.class;

            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        try {
            return metaData.isWritable(column + 1);
        } catch (SQLException e) {
            return false;
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    // Data methods
    public int getRowCount() {
        return rows.size();
    }

    public Object getValueAt(int aRow, int aColumn) {
        List<Object> row = rows.get(aRow);
        return row.get(aColumn);
    }

    public String dbRepresentation(int column, Object value) {
        int type;

        if (value == null) {
            return "null";
        }

        try {
            type = metaData.getColumnType(column + 1);
        } catch (SQLException e) {
            return value.toString();
        }

        switch (type) {
            case Types.INTEGER:
            case Types.DOUBLE:
            case Types.FLOAT:
                return value.toString();
            case Types.BIT:
                return ((Boolean) value).booleanValue() ? "1" : "0";
            case Types.DATE:
                return value.toString(); // This will need some conversion.
            default:
                return "\"" + value.toString() + "\"";
        }

    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        try {
            String tableName = metaData.getTableName(column + 1);
            // Some of the drivers seem buggy, tableName should not be null.
            if (tableName == null) {
                System.out.println("Table name returned null.");
            }
            String columnName = getColumnName(column);
            String query
                    = "update " + tableName + " set " + columnName + " = "
                    + dbRepresentation(column, value) + " where ";
            // We don't have a model of the schema so we don't know the
            // primary keys or which columns to lock on. To demonstrate
            // that editing is possible, we'll just lock on everything.
            for (int col = 0; col < getColumnCount(); col++) {
                String colName = getColumnName(col);
                if (colName.equals("")) {
                    continue;
                }
                if (col != 0) {
                    query = query + " and ";
                }
                query = query + colName + " = " + dbRepresentation(col,
                        getValueAt(row, col));
            }
            System.out.println(query);
            System.out.println("Not sending update to database");
            // statement.executeQuery(query);
        } catch (SQLException e) {
            //     e.printStackTrace();
            System.err.println("Update failed");
        }
        List<Object> dataRow = rows.get(row);
        dataRow.set(column, value);

    }
}
