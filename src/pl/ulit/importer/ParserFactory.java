/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.ulit.importer;

import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author pawel
 */
public class ParserFactory {
    private static final Logger logger = LoggerFactory.getLogger(ParserKomunikat.class);
    
    public static DefaultHandler newInstance(String typ,Connection aconnection,String adatabase){
        if("prh".equalsIgnoreCase(typ)){
            return new ParserPrh(aconnection, adatabase);
        }
        return new ParserKomunikat();
    }
    
}
