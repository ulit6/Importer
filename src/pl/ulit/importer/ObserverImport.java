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
public interface ObserverImport {
     public void update(Integer astatus) throws SQLException;
}
