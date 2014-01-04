/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.importer;

import java.sql.SQLException;

/**
 *
 * @author ulit6
 */
public abstract class InsertKomunikat {
    protected String Db;
    abstract void Insert() throws SQLException;
    public InsertKomunikat(String aDb)
    {
        Db=aDb;
    }
    
}
