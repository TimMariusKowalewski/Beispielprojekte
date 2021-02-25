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
public class DBField<U, V, T, Z>
{

    public U name;       // name field of a DBField
    public V value;      // value field of a DBField
    public T type;       // type field of a DBField
    public Z name_prop;       // property name

    // Constructs a new DBField with the given values
    private DBField(U name,
                    V value,
                    T type,
                    Z name_prop)
    {
        this.name = name;
        this.value = value;
        this.type = type;
        this.name_prop = name_prop;
    }

    public DBField()
    {
        this.name = null;
        this.value = null;
        this.type = null;
        this.name_prop = null;
    }

    public void setName(U Name)
    {
        this.name = Name;
    }

    public String getName()
    {
        return this.name.toString();
    }

    public void setNameProp(Z NameProp)
    {
        this.name_prop = NameProp;
    }

    public String getNameProp()
    {
        return this.name_prop.toString();
    }

    public void setValue(V Value)
    {
        this.value = Value;
    }

    public Object getValue()
    {
        return this.value;
    }

    public void setType(T Type)
    {
        this.type = Type;
    }

    public Object getType()
    {
        return this.type;
    }

    @Override
    public boolean equals(Object o)
    {
        /* Checks specified object is "equal to" current object or not */

        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        DBField triplet = (DBField) o;

        // call equals() method of the underlying objects
        if (!name.equals(triplet.name)
            || !value.equals(triplet.value)
            || !type.equals(triplet.type))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        /* Computes hash code for an object by using hash codes of
        the underlying objects */

        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "(" + name + ", " + value + ", " + type + ")";
    }

    // Factory method for creating a Typed immutable instance of DBField
    public static <U, V, T, Z> DBField<U, V, T, Z> of(U a,
                                                      V b,
                                                      T c,
                                                      Z d)
    {
        return new DBField<>(a,
                             b,
                             c,
                             d);
    }
}
