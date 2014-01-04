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
public interface SubjectImport {
    public void registerObserverImport(ObserverImport observer);

    public void removeObserverImport(ObserverImport observer);

    public void notifyObserversImport(Integer astatus) throws SQLException;
}
