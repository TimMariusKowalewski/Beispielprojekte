/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RENDER.Display;

import geometricobjects.OSMNode;
import geometricobjects.OSMRelation;
import geometricobjects.OSMWay;
import geometricobjects.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Kowalewski
 */
public class OSMObjectFactory {
    private static List<OSMNode> _nodes;
    private static List<OSMWay> _ways;
    private static List<OSMRelation> _relations;
    
    public static List getNodeList() {
        return OSMObjectFactory._nodes;
    }
    
    public static List getNodeRectangles() {
        List<OSMNodeRectangle> _list = new ArrayList();
        
        for(Object _o: OSMObjectFactory.getNodeList()) {
            OSMNode _n = (OSMNode)_o;
            // punkte, datne
            Point _a = new Point(0, 0);
            Point _b = new Point(0, 1);
            Point _c = new Point(1, 1);
            Point _d = new Point(1, 0);
            OSMNodeRectangle _r = new OSMNodeRectangle(_a, _b, _c, _d);
            _list.add(_r);
        }
        
        return _list;
    }
    
    public static List getWayList() {
        return OSMObjectFactory._ways;
        
    }
    
    public static List getRelationList() {
        return OSMObjectFactory._relations;
        
    }
    
    public static void parseOSMXML(String FilePath) {
        OSMObjectFactory._nodes = new ArrayList();
        
        try {
            File inputFile = new File(FilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            NodeList nList = doc.getElementsByTagName("*");
            //System.out.println("----------------------------");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                    
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                switch(nNode.getNodeName()) {
                    case "node":
                        OSMNode _node = new OSMNode(new Point(0,0));
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element _element = (Element) nNode; 
                            System.out.println(_element.getAttribute("id"));
                            _node.setID(_element.getAttribute("id"));
                            _node.setLongitude(_element.getAttribute("lon"));
                            _node.setLatitude(_element.getAttribute("lat"));
                            _node.setTimestamp(_element.getAttribute("timestamp"));
                            _node.setUid(_element.getAttribute("uid"));
                            _node.setUser(_element.getAttribute("user"));

                            if(_element.getAttribute("visible").equals("true")) {
                                _node.setVisible(true);
                            } else {
                                _node.setVisible(false);
                            }

                            _node.setVersion(_element.getAttribute("version"));
                            _node.setChangeset(_element.getAttribute("changeset"));

                            OSMObjectFactory.getNodeList().add(_node);
                        }
                        break;
                    case "way":
                        OSMWay _way = new OSMWay(new Point(0,0));
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element _element = (Element) nNode; 
                        _way.setID(_element.getAttribute("id"));
                        //_way.setLongitude(_element.getAttribute("lon"));
                        //_way.setLatitude(_element.getAttribute("lat"));
                        _way.setTimestamp(_element.getAttribute("timestamp"));
                        _way.setUid(_element.getAttribute("uid"));
                        _way.setUser(_element.getAttribute("user"));

                        if(_element.getAttribute("visible").equals("true")) {
                        _way.setVisible(true);
                        } else {
                        _way.setVisible(false);
                        }

                        _way.setVersion(_element.getAttribute("version"));
                        _way.setChangeset(_element.getAttribute("changeset"));

                        OSMObjectFactory.getWayList().add(_way);
                                }
                        break;
                    case "relation":
                        OSMRelation _relation = new OSMRelation(new Point(0,0));

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element _element = (Element) nNode; 
                            _relation.setID(_element.getAttribute("id"));
                            //_relation.setLongitude(_element.getAttribute("lon"));
                            //_relation.setLatitude(_element.getAttribute("lat"));
                            _relation.setTimestamp(_element.getAttribute("timestamp"));
                            _relation.setUid(_element.getAttribute("uid"));
                            _relation.setUser(_element.getAttribute("user"));

                            if(_element.getAttribute("visible").equals("true")) {
                                _relation.setVisible(true);
                            } else {
                                _relation.setVisible(false);
                            }

                            _relation.setVersion(_element.getAttribute("version"));
                            _relation.setChangeset(_element.getAttribute("changeset"));

                            OSMObjectFactory.getRelationList().add(_relation);
                        }
                        break;
                    default:
                        System.out.println("not handled:" + nNode.getNodeName());
                        break;
                    
                    
                    

                    /*if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        System.out.println("id: " + eElement.getAttribute("id"));
                        System.out.println("lon: " + eElement.getAttribute("lon"));
                        System.out.println("lat: " + eElement.getAttribute("lat"));
                       System.out.println("First Name : " 
                          + eElement
                          .getElementsByTagName("firstname")
                          .item(0)
                          .getTextContent());
                       System.out.println("Last Name : " 
                          + eElement
                          .getElementsByTagName("lastname")
                          .item(0)
                          .getTextContent());
                       System.out.println("Nick Name : " 
                          + eElement
                          .getElementsByTagName("nickname")
                          .item(0)
                          .getTextContent());
                       System.out.println("Marks : " 
                          + eElement
                          .getElementsByTagName("marks")
                          .item(0)
                          .getTextContent());
                    }*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}