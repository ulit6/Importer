package pl.ulit.importer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulit6
 */
public class ParserKomunikat extends DefaultHandler{
    private String typ;
    private String wersja;
    private String temp;
    private static final Logger logger = LoggerFactory.getLogger(ParserKomunikat.class);
    
    @Override
    public void characters(char[] buffer, int start, int length) {
              temp = new String(buffer, start, length);
       }
   
    
    @Override
    public void startElement(String uri, String localName,
                     String qName, Attributes attributes) throws SAXException {
              
              if (qName.equalsIgnoreCase("komunikat")) {
                     logger.info("Parsowanie elementu komunikat...");
                     this.typ = attributes.getValue("typ");
                     this.wersja = attributes.getValue("wersja");
                    
                          
              }
       }
    public String getTyp()
    {
        return typ;
    }
    public String getWersja()
    {
        return wersja;
    }
}
