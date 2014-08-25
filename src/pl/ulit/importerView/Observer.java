/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.importerView;

/**
 *
 * @author pawel
 */
public interface Observer {
    public void update(String message);
    public void updateGUI(Throwable ex);
}
