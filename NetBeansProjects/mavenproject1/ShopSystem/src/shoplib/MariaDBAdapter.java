/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kowalewski
 */
public class MariaDBAdapter
{

    static List<Class<?>> SUPPORTED_DATA_TYPES;

    static
    {
        Class<?>[] supported_data_types =
        {
            shoplib.DBString.class,
            shoplib.DBInt.class,
            shoplib.DBFloat.class,
            shoplib.DBTimestamp.class,
            shoplib.DBBool.class,
            shoplib.DBUUID.class
        };
        SUPPORTED_DATA_TYPES = Arrays.asList(supported_data_types);
    }

    private final String Database;
    private final String DbUrl;
    // JDBC driver name and database URL
    private final String JDBCDriver;
    //  Database credentials
    private final String Username;
    private final String Password;

    private Connection connection;
    private ResultSet results;

    public MariaDBAdapter(String Host,
                          String Port,
                          String Database,
                          String Username,
                          String Password)
    {
        this.connection = null;
        this.results = null;
        this.JDBCDriver = "org.mariadb.jdbc.Driver";
        this.DbUrl = String.
            format("jdbc:mariadb://%s:%s/%s?user=%s&password=%s",
                   Host,
                   Port,
                   Database,
                   Username,
                   Password
            );

        this.Username = Username;
        this.Password = Password;
        this.Database = Database;
    }

    public boolean connect()
    {
        try
        {
            try
            {
                // soll den static initializer der Klasse ausführen
                Class.forName("org.mariadb.jdbc.Driver").
                    newInstance();
            }
            catch (InstantiationException ex)
            {
                Logger.getLogger(MariaDBAdapter.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
            catch (IllegalAccessException ex)
            {
                Logger.getLogger(MariaDBAdapter.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        try
        {
            this.connection = DriverManager.getConnection(this.DbUrl);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        return this.connection != null;
    }

    public String createDeleteQuery(BaseObject O)
    {
        Field TableNameField = null;
        try
        {
            TableNameField = O.getClass().
                getField("DBTableName");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        String Table = "table";
        try
        {
            Table = TableNameField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        Field IDField = null;
        try
        {
            IDField = O.getClass().
                getField("DBIDField");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        String ID = "id";
        try
        {
            ID = IDField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        return String.format("DELETE FROM `%s` WHERE `%s`='%s'",
                             Table,
                             ID,
                             O.getID()
        );
    }

    public String createInsertQuery(Object O)
    {
        Field TableNameField = null;
        try
        {
            TableNameField = O.getClass().
                getField("DBTableName");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        String Table = "table";
        try
        {
            Table = TableNameField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        ArrayList<DBField> Fields = this.getObjectFields(O);

        String sql_begin = String.format("INSERT INTO\n\t`%s`",
                                         Table
        );

        String[] insert_fields = new String[Fields.size()];
        String[] insert_values = new String[Fields.size()];
        String FieldValue = "";
        for (int i = 0; i < Fields.size(); i++)
        {
            insert_fields[i] = Fields.get(i).
                getName();

            FieldValue = Fields.get(i).
                getValue().
                toString();

            if (Fields.get(i).
                getType() == shoplib.DBTimestamp.class)
            {
                FieldValue = FieldValue.substring(0,
                                                  FieldValue.length() - 3);
                FieldValue += "000000";
            }
            if (FieldValue.equals("true") == true)
            {
                FieldValue = "1";
            }
            if (FieldValue.equals("false") == true)
            {
                FieldValue = "0";
            }
            insert_values[i] = FieldValue;
        }

        return String.format("%s\n\t(`%s`)\nVALUES\n\t('%s')",
                             sql_begin,
                             String.join("`,\n\t`",
                                         insert_fields),
                             String.join("',\n\t'",
                                         insert_values)
        );
    }

    public String createSchemaQuery(Object O)

    {
        Field TableNameField = null;
        try
        {
            TableNameField = O.getClass().
                getField("DBTableName");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        String Table = "table";
        try
        {
            Table = TableNameField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        ArrayList<DBField> Fields = this.getObjectFields(O);
        String sql_begin = String.format("CREATE TABLE %s (\n",
                                         Table);

        String sql_field_type;
        String[] field_definitions = new String[Fields.size()];
        int i = 0;
        for (DBField field : Fields)
        {
            if (field.getType() == shoplib.DBString.class)
            {
                sql_field_type = "VARCHAR(255)";
            }
            else if (field.getType() == shoplib.DBInt.class)
            {
                sql_field_type = "INT";
            }
            else if (field.getType() == shoplib.DBFloat.class)
            {
                sql_field_type = "FLOAT";
            }
            else if (field.getType() == shoplib.DBTimestamp.class)
            {
                sql_field_type = "TIMESTAMP";
            }
            else if (field.getType() == shoplib.DBUUID.class)
            {
                sql_field_type = "VARCHAR(255)";
            }
            else if (field.getType() == shoplib.DBBool.class)
            {
                sql_field_type = "BOOL";
            }
            else
            {
                sql_field_type = "UNKNOWN";
            }

            field_definitions[i] = String.format("\t%s %s",
                                                 field.getName(),
                                                 sql_field_type);
            i++;
        }

        return String.format("%s %s,\n\tCONSTRAINT %s_pk PRIMARY KEY (ID)\n);",
                             sql_begin,
                             String.join(",\n",
                                         field_definitions),
                             Table);
    }

    public String createSelectQuery(BaseObject O)
    {
        //return "SELECT " + this.implodeMapKeysToString(Fields) + " FROM " + Table;
        Field TableNameField = null;
        try
        {
            TableNameField = O.getClass().
                getField("DBTableName");
        }
        catch (NoSuchFieldException | SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        String Table = "table";
        try
        {
            Table = TableNameField.get(null).
                toString();
        }
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        Field IDField = null;
        try
        {
            IDField = O.getClass().
                getField("DBIDField");
        }
        catch (NoSuchFieldException | SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        String ID = "id";
        try
        {
            ID = IDField.get(null).
                toString();
        }
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        ArrayList<DBField> Fields = this.getObjectFields(O);

        String[] select_fields = new String[Fields.size()];
        for (int i = 0; i < Fields.size(); i++)
        {
            select_fields[i] = Fields.get(i).
                getName();
        }
        return String.format("SELECT\n\t`%s`\nFROM \n\t%s\nWHERE\n\t`%s` = '%s'",
                             String.join("`,\n\t`",
                                         select_fields),
                             Table,
                             ID,
                             O.getID()
        );
    }

    public String createUpdateQuery(BaseObject O)
    {
        Field TableNameField = null;
        try
        {
            TableNameField = O.getClass().
                getField("DBTableName");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        String Table = "table";
        try
        {
            Table = TableNameField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        Field IDField = null;
        try
        {
            IDField = O.getClass().
                getField("DBIDField");
        }
        catch (NoSuchFieldException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        String ID = "id";
        try
        {
            ID = IDField.get(null).
                toString();
        }
        catch (IllegalArgumentException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        ArrayList<DBField> Fields = this.getObjectFields(O);

        // zu überspringende Felder abziehen
        int array_size = Fields.size() - 4;
        String[] update_fields = new String[array_size];

        int j = 0;
        for (int i = 0; i < Fields.size(); i++)
        {
            // zu überspringende Felder beim UPDATE
            if (Fields.get(i).
                getName().
                equals("creation_date") == true)
            {
                continue;
            }
            if (Fields.get(i).
                getName().
                equals("deletion_date") == true)
            {
                continue;
            }
            if (Fields.get(i).
                getName().
                equals("deleted") == true)
            {
                continue;
            }
            if (Fields.get(i).
                getName().
                equals("ID") == true)
            {
                continue;
            }

            String FieldValue = Fields.get(i).
                getValue().
                toString();

            if (Fields.get(i).
                getType() == shoplib.DBTimestamp.class)
            {
                FieldValue = FieldValue.substring(0,
                                                  FieldValue.length() - 3);
                FieldValue += "0000000";
            }
            if (FieldValue.equals("true") == true)
            {
                FieldValue = "1";
            }
            if (FieldValue.equals("false") == true)
            {
                FieldValue = "0";
            }
            update_fields[j] = String.format("`%s`='%s'",
                                             Fields.get(i).
                                                 getName(),
                                             FieldValue);
            j++;

        }

        return String.format("UPDATE\n\t`%s`\nSET\n\t%s\nWHERE\n\t`%s`='%s'",
                             Table,
                             String.join(",\n\t",
                                         update_fields),
                             ID,
                             O.getID()
        );
    }

    public boolean disconnect()
    {
        if (this.connection != null)
        {
            try
            {
                this.connection.close();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(MariaDBAdapter.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
        }

        return this.connection == null;
    }

    // gibt Daten zurück
    public boolean executeQuery(String SQL)
    {
        System.out.println(SQL);
        if (this.connection == null)
        {
            this.connect();
        }

        Statement stmt = null;
        try
        {
            stmt = this.connection.createStatement();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        this.results = null;
        try
        {
            this.results = stmt.executeQuery(SQL);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        return this.results != null;
    }

    // gibt die Anzahl der betroffene Datensätze zurück
    public int executeUpdate(String SQL)
    {
        System.out.println(SQL);
        if (this.connection == null)
        {
            this.connect();
        }

        Statement stmt = null;
        try
        {
            stmt = this.connection.createStatement();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        int ret = 0;
        try
        {
            ret = stmt.executeUpdate(SQL);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        return ret;
    }

    public Map<String, Object> getClassFields(Class<?> clazz)
    {
        Map<String, Object> create_fields = new LinkedHashMap<>(10);
        try
        {
            Field f[] = clazz.getDeclaredFields();
            Field f_parent[] = clazz.getSuperclass().
                getDeclaredFields();
            Field f_all[] = new Field[f.length + f_parent.length];
            System.arraycopy(f_parent,
                             0,
                             f_all,
                             0,
                             f_parent.length);
            System.arraycopy(f,
                             0,
                             f_all,
                             f_parent.length,
                             f.length);

            for (int i = 0; i < f_all.length; i++)
            {
                //System.out.println(f_all[i].toString());
                if (SUPPORTED_DATA_TYPES.contains(f_all[i].getType()))
                {
                    create_fields.put(f_all[i].getName(),
                                      f_all[i].getType());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        return create_fields;
    }

    // lieferte eine Map aus Feldnamen und deren Werten zurück
    public ArrayList<DBField> getObjectFields(Object O)
    {
        ArrayList<DBField> create_fields = new ArrayList<>(10);
        try
        {
            // Felder der Klasse abrufen
            Field f[] = O.getClass().
                getDeclaredFields();
            // Felder der Super-Klasse abrufen
            Field f_parent[] = O.getClass().
                getSuperclass().
                getDeclaredFields();

            // alle Felder in einem Array kombinieren
            Field f_all[] = new Field[f.length + f_parent.length];
            System.arraycopy(f_parent,
                             0,
                             f_all,
                             0,
                             f_parent.length);
            System.arraycopy(f,
                             0,
                             f_all,
                             f_parent.length,
                             f.length);

            // Array mit Feldern durchlaufen - diese werden im Rückgabe-Datentyp
            // gespeichert
            // wir rufen für jedes Feld den korrespondierenden Getter auf
            // um die Daten abzurufen
            DBField<String, Object, Object, String> current_field; // das aktuelle zu verarbeitende Feld
            for (int i = 0; i < f_all.length; i++)
            {
                if (SUPPORTED_DATA_TYPES.contains(f_all[i].getType()))
                {
                    // hier versuchen wir das Feld als Eigenschaft der Klasse
                    // zu interpretieren
                    try
                    {
                        current_field = new DBField<>();

                        String getter_prefix = "get";

                        if (f_all[i].getType() == shoplib.DBBool.class)
                        {
                            getter_prefix = "is";
                        }

                        // der mit dem aktuellen Feld korrespondierende Getter
                        Method getter_method = O.getClass().
                            getMethod(getter_prefix + f_all[i].getName());
                        // ruft der Getter auf
                        Object getter_method_return_value = getter_method.
                            invoke(O);

                        current_field.setName(f_all[i].getName());
                        current_field.setNameProp(f_all[i].getName());
                        try
                        {
                            f_all[i].setAccessible(true);
                            Object fieldValue = f_all[i].get(O);

                            Method myMethod = fieldValue.getClass().
                                getSuperclass().
                                getDeclaredMethod("getFieldName",
                                                  new Class[]
                                                  {
                                                  });
                            Object lala = myMethod.invoke(fieldValue);
                            current_field.setName(lala.toString());
                        }
                        catch (Exception e)
                        {
                            //System.out.println(e.getMessage());
                        }

                        current_field.setValue(getter_method_return_value);
                        current_field.setType(f_all[i].getType());

                        create_fields.add(current_field);
                    }
                    // wenn obiger Block scheitert, versuchen wir hier
                    // das Feld als Eigenschaft der Super-Klasse
                    // zu interpretieren
                    catch (Exception e)
                    {
                        current_field = new DBField<>();
                        String getter_prefix = "get";

                        if (f_all[i].getType() == shoplib.DBBool.class)
                        {
                            getter_prefix = "is";
                        }

                        Method getter_method = O.getClass().
                            getSuperclass().
                            getMethod(getter_prefix + f_all[i].getName());
                        Object getter_method_return_value = getter_method.
                            invoke(O);

                        current_field.setName(f_all[i].getName());
                        current_field.setValue(getter_method_return_value);
                        current_field.setType(f_all[i].getType());

                        create_fields.add(current_field);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        return create_fields;
    }

    public String implodeMapKeysToString(Map Map)
    {
        String[] ret = new String[Map.size()];

        int i = 0;
        for (Object key : Map.keySet())
        {
            ret[i] = key.toString();
            i++;
        }

        return String.join(", ",
                           ret);
    }

    //public ArrayList<ArrayList<DBField>> readResults(BaseObject O)
    public BaseObject readResults(BaseObject O)
    {
        ArrayList<ArrayList<DBField>> resultset = new ArrayList<>(10);
        ArrayList<DBField> record = new ArrayList<>(10);
        DBField field = null;
        Object value = null;

        ArrayList<DBField> Fields = this.getObjectFields(O);
        try
        {
            while (this.results.next())
            {
                for (int i = 0; i < Fields.size(); i++)
                {
                    field = new DBField<>();
                    if (Fields.get(i).
                        getType() == DBString.class)
                    {
                        try
                        {
                            value = this.results.getString(Fields.get(i).
                                getName());
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else if (Fields.get(i).
                        getType() == DBInt.class)
                    {
                        try
                        {
                            value = this.results.getInt(Fields.get(i).
                                getName());
                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else if (Fields.get(i).
                        getType() == DBTimestamp.class)
                    {
                        try
                        {
                            value = this.results.getTimestamp(Fields.get(i).
                                getName());

                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else if (Fields.get(i).
                        getType() == DBUUID.class)
                    {
                        try
                        {
                            value = this.results.getString(Fields.get(i).
                                getName());

                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else if (Fields.get(i).
                        getType() == DBFloat.class)
                    {
                        try
                        {
                            value = this.results.getFloat(Fields.get(i).
                                getName());

                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else if (Fields.get(i).
                        getType() == DBBool.class)
                    {
                        try
                        {
                            value = this.results.getBoolean(Fields.get(i).
                                getName());

                        }
                        catch (SQLException ex)
                        {
                            Logger.getLogger(MariaDBAdapter.class.getName()).
                                log(Level.SEVERE,
                                    null,
                                    ex);
                        }
                    }
                    else
                    {

                    }

                    try
                    {
                        field.setName(Fields.get(i).
                            getName());
                        field.setType(Fields.get(i).
                            getType());
                        field.setValue(value);
                        record.add(field);

                        // call the correct setter
                        Method methods[] = O.getClass().
                            getDeclaredMethods();
                        for (Method m : methods)
                        {
                            if (m.getName().
                                equals("set" + Fields.get(i).
                                    getNameProp()) == true)
                            {
                                if (Fields.get(i).
                                    getType() == DBFloat.class)
                                {
                                    m.invoke(O,
                                             Float.parseFloat(value.toString()));
                                }
                                else if (Fields.get(i).
                                    getType() == DBInt.class)
                                {
                                    m.invoke(O,
                                             Integer.parseInt(value.toString()));
                                }
                                else if (Fields.get(i).
                                    getType() == DBBool.class)
                                {
                                    m.invoke(O,
                                             Boolean.parseBoolean(value.
                                                 toString()));
                                }
                                else
                                {
                                    m.invoke(O,
                                             value);
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("lala: " + e.getMessage());
                    }
                }
                resultset.add(record);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MariaDBAdapter.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        return O;
    }

}
