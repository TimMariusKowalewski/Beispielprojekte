/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoplib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kowalewski
 */
public class BaseObject
{

    public final static String DBTableName = "table";
    public final static String DBIDField = "ID";
    private static MariaDBAdapter DBAdapter;

    static
    {
        BaseObject.DBAdapter = new MariaDBAdapter("localhost",
                                                  "3306",
                                                  "test",
                                                  "root",
                                                  "password");
    }

    private DBUUID ID;
    private DBTimestamp CreationDate;
    private DBTimestamp LastModificationDate;
    private DBBool Deleted;
    private DBTimestamp DeletionDate;

    public BaseObject()
    {
        this.ID = new DBUUID(UUID.randomUUID(),
                             "ID");
        this.CreationDate = new DBTimestamp(new Timestamp(System.
            currentTimeMillis()),
                                            "creation_date");
        this.LastModificationDate = new DBTimestamp(new Timestamp(System.
            currentTimeMillis()),
                                                    "last_modification_date");
        this.Deleted = new DBBool(false,
                                  "deleted");
        this.DeletionDate = new DBTimestamp(new Timestamp(System.
            currentTimeMillis()),
                                            "deletion_date");
    }

    public java.util.UUID getID()
    {
        return this.ID.getValue();
    }

    public void setID(java.util.UUID ID)
    {
        this.ID.setValue(ID);
    }

    public java.sql.Timestamp getCreationDate()
    {
        return this.CreationDate.getValue();
    }

    public void setCreationDate(java.sql.Timestamp CreationDate)
    {
        this.CreationDate.setValue(CreationDate);
    }

    public java.sql.Timestamp getLastModificationDate()
    {
        return this.LastModificationDate.getValue();
    }

    public void setLastModificationDate(java.sql.Timestamp LastModificationDate)
    {
        this.LastModificationDate.setValue(LastModificationDate);
    }

    public boolean isDeleted()
    {
        return this.Deleted.getValue();
    }

    public void setDeleted(boolean deleted)
    {
        this.Deleted.setValue(deleted);
    }

    public java.sql.Timestamp getDeletionDate()
    {
        return this.DeletionDate.getValue();
    }

    public void setDeletionDate(java.sql.Timestamp DeletionDate)
    {
        this.DeletionDate.setValue(DeletionDate);
    }

    public void create()
    {
        String create_sql = BaseObject.DBAdapter.createInsertQuery(this);
        this.DBAdapter.executeUpdate(create_sql);
    }

    public static void readList(String Filter,
                                Class<?> clazz)
    {

    }

    public static BaseObject read(UUID ID,
                                  Class<?> clazz)
    {
        Object obj = null;
        try
        {
            //obj = clazz.newInstance();
            Constructor<?> constructor = null;
            try
            {
                constructor = clazz.getConstructor(UUID.class);
            }
            catch (NoSuchMethodException ex)
            {
                Logger.getLogger(BaseObject.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
            catch (SecurityException ex)
            {
                Logger.getLogger(BaseObject.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
            try
            {
                obj = constructor.newInstance(new Object[]
                {
                    ID
                });
            }
            catch (IllegalArgumentException ex)
            {
                Logger.getLogger(BaseObject.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
            catch (InvocationTargetException ex)
            {
                Logger.getLogger(BaseObject.class.getName()).
                    log(Level.SEVERE,
                        null,
                        ex);
            }
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(BaseObject.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(BaseObject.class.getName()).
                log(Level.SEVERE,
                    null,
                    ex);
        }

        // Objekt mit Werten füllen
        String select_sql = BaseObject.DBAdapter.createSelectQuery(
            (BaseObject) obj);
        BaseObject.DBAdapter.executeQuery(select_sql);
        obj = BaseObject.DBAdapter.readResults((BaseObject) obj);

        return (BaseObject) obj;
    }

    public void update()
    {
        String update_sql = BaseObject.DBAdapter.createUpdateQuery(this);
        this.DBAdapter.executeUpdate(update_sql);
    }

    public void delete()
    {
        this.setDeleted(true);
        this.setDeletionDate(new Timestamp(System.currentTimeMillis()));
        this.update();
    }
}
