/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ulit.importer;

import pl.ulit.prh.Element;

/**
 *
 * @author ulit6
 */
public interface SubjectElement {
    public void registerObserver(ObserverElement observer);

    public void removeObserver(ObserverElement observer);

    public void notifyObservers(Element element);
   
}
