/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

/**
 *
 * @author Kowalewski
 */
public class MariaDBSQLGenerator
{

    String _sql;

    public MariaDBSQLGenerator()
    {
        this._sql = "";
    }

    public MariaDBSQLGenerator from(String TableName)
    {
        this._sql += " " + TableName + " ";
        return this;
    }

    public MariaDBSQLGenerator select(String[] Fields)
    {
        this._sql += "SELECT " + String.join(", ",
                                             Fields) + " FROM";
        return this;
    }

    public MariaDBSQLGenerator where(String Condition)
    {
        this._sql += " WHERE " + Condition;
        return this;
    }

    public MariaDBSQLGenerator order_by(String asd)
    {
        this._sql += " WHERE " + asd;
        return this;
    }

    public MariaDBSQLGenerator limit(String asd)
    {
        this._sql += " WHERE " + asd;
        return this;
    }

    public String export()
    {
        return this._sql;
    }
}
