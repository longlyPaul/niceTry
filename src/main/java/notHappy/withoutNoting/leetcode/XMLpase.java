package notHappy.withoutNoting.leetcode;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class XMLpase {
	
	private Map<String, Object> valueMap=new HashMap<String, Object>();
	
	private Stack<String> stack = new Stack<String>();  

	  public Document initDOC() {
	       try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory
	                    .newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.newDocument();
	            return document;
	        } catch (ParserConfigurationException e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }

	public String paseXml(String xml) {
		try {
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document document = db.parse(xml);
            
           NodeList root = document.getChildNodes();
           for (int i = 0; i < root.getLength(); i++) {
               Node employee = root.item(i);
               NodeList employeeInfo = employee.getChildNodes();
               String table = null;
               table=employee.getAttributes().getNamedItem("tableName").getNodeValue();
               for (int j = 0; j < employeeInfo.getLength(); j++) {
                  Node node = employeeInfo.item(j);
                  String columnStr=null;
                  if(node.getNodeName().equals("tr")&&table!=null)
               	   	  columnStr=builtColumnPro(node.getChildNodes(),table,node.getAttributes().getNamedItem("type").getNodeValue());
                  if(columnStr!=null)
                	  System.out.println(columnStr);
               }
           }
           System.out.println("�������");
       } catch (FileNotFoundException e) {
           System.out.println(e.getMessage());
       } catch (ParserConfigurationException e){
           System.out.println(e.getMessage());
       } catch (SAXException e) {
           System.out.println(e.getMessage());
       } catch (IOException e) {
           System.out.println(e.getMessage());
       }
		return xml;
	}

	private String builtColumnPro(NodeList childNodes, String table, String type) {
		StringBuffer sb=new StringBuffer();
		if(type.equals("insert")){
			StringBuffer valuesb=new StringBuffer();
			sb.insert(0, "INSERT INTO "+table+"(");
			valuesb.append(")values(");
			for (int k = 0; k < childNodes.getLength(); k++){
				if(!childNodes.item(k).getNodeName().equals("#text")){
					sb.append(childNodes.item(k).getNodeName());
					sb.append(",");
					if(childNodes.item(k).getTextContent()!=null)
						valuesb.append(":"+childNodes.item(k).getNodeName());
					else valuesb.append("null");
					valuesb.append(",");
					valueMap.put(childNodes.item(k).getNodeName(), childNodes.item(k).getTextContent());
				}
			}
			return sb.substring(0, sb.length()-1)+valuesb.substring(0, valuesb.length()-1)+")";
		}else{
			StringBuffer conditionsb=new StringBuffer();
			conditionsb.append(" WHERE ");
			sb.insert(0, "UPDATE "+table+" SET ");
			for (int k = 0; k < childNodes.getLength(); k++){
				if(!childNodes.item(k).getNodeName().equals("#text")&& !childNodes.item(k).getNodeName().equals("ID")){
					sb.append(childNodes.item(k).getNodeName());
					sb.append("=");
					if(childNodes.item(k).getTextContent()!=null)
						sb.append(":"+childNodes.item(k).getNodeName());
					else sb.append("null");
					sb.append(",");
					valueMap.put(childNodes.item(k).getNodeName(), childNodes.item(k).getTextContent());
				}else if(childNodes.item(k).getNodeName().equals("ID")) {
					conditionsb.append(childNodes.item(k).getNodeName());
					conditionsb.append("=");
					conditionsb.append(":"+childNodes.item(k).getNodeName());
					valueMap.put(childNodes.item(k).getNodeName(), childNodes.item(k).getTextContent());
				}
			}
			return sb.substring(0, sb.length()-1)+conditionsb.toString();
		}
		
	}
	
	
	
	
	public String pase(String path){
		
          
      
		return path;  
	}
	
	
	public static void main(String[] args) {
		XMLpase xmLpase=new XMLpase();
		xmLpase.initDOC();
		xmLpase.paseXml("C:\\Users\\liusl\\Desktop\\222.xml");
	}
}
