/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.udc.cdm.plataforma;

import es.udc.cdm.model.Version;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author maria
 */
public class EjemploDOMLeer {

 
    private static final String VERSION_TAG = "version";
    private static final String VERSION_NOMBRE_TAG = "nombre";
    private static final String VERSION_API_TAG = "api";
    private static final String VERSION_ATT_NUMERO = "numero";

    private static final String VERSIONES_INPUT_FILE = Paths.get("src", "docs", "versiones.xml").toString();

    public static void main(String[] args) {

        double numero = 0;
        int api=0;
        String  nombre;
        Version version = null;

        ArrayList<Version> versions = new ArrayList<>();
        int contador = 1;

        try {
            File inputFile = new File(VERSIONES_INPUT_FILE);
           Document doc =  XmlCtrlDom.instanciarDocument(inputFile);

         

            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());

       
            NodeList nList = doc.getElementsByTagName(VERSION_TAG);

            System.out.println("----------------------------");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    
                    numero = Double.parseDouble(eElement.getAttribute(VERSION_ATT_NUMERO));
                   
                    nombre =  XmlCtrlDom.getValorEtiqueta(VERSION_NOMBRE_TAG, eElement);
                  
                    api = Integer.parseInt(   XmlCtrlDom.getValorEtiqueta(VERSION_API_TAG, eElement));

                    version = new Version(numero, nombre, api);

                    versions.add(version);
                }
            }

         
            for (Version v : versions) {
                System.out.println("Version: " + contador + " " + v);
                contador++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ha ocurrido una exception: " + e.getMessage());
        }
    }

}
