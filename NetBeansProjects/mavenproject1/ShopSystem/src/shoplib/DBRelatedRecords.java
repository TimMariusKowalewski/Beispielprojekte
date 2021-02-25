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
public class DBRelatedRecords<T>
{

    private DBRelationshipType RelationshipType;

    public DBRelatedRecords(DBRelationshipType RelationshipType)
    {
        this.RelationshipType = RelationshipType;
    }
}
