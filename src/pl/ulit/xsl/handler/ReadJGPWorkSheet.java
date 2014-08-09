/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.xsl.handler;

/**
 *
 * @author pawel
 */
public abstract class ReadJGPWorkSheet extends ReadWorkSheet{
    protected int wprm;
    public void setWprm(int wprm){
        this.wprm = wprm;
    }
}
