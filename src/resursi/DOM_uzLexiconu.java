package resursi;
import java.io.File;
import java.util.regex.*;
//import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.Writer;
import java.io.BufferedWriter;
//import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lv.semti.morphology.lexicon.*;
import lv.semti.Vardnicas.*;


public class DOM_uzLexiconu {

	/**
	 * @param args
	 */
public static String vards = "";
public static String avots = "";
private static String verb = "";



public static void main(String[] args) throws Exception{
				
		//izveido DOM koku no Skaidrojosās vārdnīcas faila
		
		Document doc = null;
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File("testXML.xml"));		
		
		Node node = doc.getDocumentElement();
		System.out.println(node);
		//traverseAllNodes(node);
		
		getVerbs(node);//vajag static vai nē???
		System.out.println(node);		
		}
	
	public static void traverseAllNodes(Node node)
	//apstaigā DOM koku un iegūst informāciju no vajadzīgajiem mezgliem
	   {   
				
	   if(node.hasChildNodes())
	     {    
	     NodeList NL=node.getChildNodes();            
	     for(int i=0;i<NL.getLength();i++)
	       {
	    	
	       Node childnode=NL.item(i);
	             
	              
	       if (childnode.getNodeName() == "#text"){
	    	   //atrod vārdformas
	    	   if (childnode.getParentNode().getNodeName() == "vf"){
	    		   System.out.print("Name:"+childnode.getParentNode().getNodeName());
			       System.out.print("Value:"+childnode.getNodeValue()+"/\n");
			       
			       try {
			    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
	                           "testImport.txt"), true));
		                bw.write("Name: "+childnode.getParentNode().getNodeName()+" "+"Value: "+childnode.getNodeValue());
		                bw.newLine();
		                bw.close();
		        } catch (Exception e) {
		        }	
			       //sagatavo vardu leksēmas veidošanai
			       if (childnode.getNodeValue().length() > 4){
			    	   vards = childnode.getNodeValue();
			       }else vards = "";
	    	   }
	    	   //atrod avotus
	    	   else  if (childnode.getParentNode().getNodeName() == "avots"){
	    		   System.out.print("Name:"+childnode.getParentNode().getNodeName());
			       System.out.print("Value:"+childnode.getNodeValue()+"/\n");
			       
			       try {
			    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
	                           "testImport.txt"), true));
		                bw.write("Name: "+childnode.getParentNode().getNodeName()+" "+"Value: "+childnode.getNodeValue());
		                bw.newLine();
		                bw.close();
		        } catch (Exception e) {
		        }	
			       if (vards != ""){
			    	   avots = childnode.getNodeValue();
			       }
	    	   }
	    	   //atrod gramatikas formu
	    	   else  if (childnode.getParentNode().getNodeName() == "gram"){
	    		   System.out.print("Name:"+childnode.getParentNode().getNodeName());
			       System.out.print("Value:"+childnode.getNodeValue()+"/\n");
			       
			       try {
			    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
	                           "testImport.txt"), true));
		                bw.write("Name: "+childnode.getParentNode().getNodeName()+" "+"Value: "+childnode.getNodeValue());
		                bw.newLine();
		                bw.close();
		        } catch (Exception e) {
		        }		    
	    	   }
	    	   //atrod skaidrojumus
	    	   else  if (childnode.getParentNode().getNodeName() == "t"){
	    		   System.out.print("Name:"+childnode.getParentNode().getNodeName());
			       System.out.print("Value:"+childnode.getNodeValue()+"/\n");
			       
			       try {
			    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
	                           "testImport.txt"), true));
		                bw.write("Name: "+childnode.getParentNode().getNodeName()+" "+"Value: "+childnode.getNodeValue());
		                bw.newLine();
		                bw.close();
		        } catch (Exception e) {
		        }		    
	    	   }
	    	   
	       }
	       //ja vards != "", tad veido leksēmu
	       //kad vards ir dabūts, tam piekārto paradigmu
	       //katrai t jātaisa sava leksēma tikai tad, ja atšķiras locīšana
	       //kad leksēma uztaisīta - avots ir ""
	       
	       traverseAllNodes(childnode);
	       }
	     }
	   
	   } 
	
	public static void appendParadigm(String word){
		//nosaka līdzi padotajam vārdam locīšanas paradigmu
		
		//jāieiet xml failā un jāmeklē
		//ja beidzas ar t vai ties tad automātiski tas ir darbības vārds;
		//gram. -> procedūra, kas piešķir paradigmu, to, ko var atpazīt, atpazīst;
		//tiem, kam nevar atpazīt, konsolē izvada vf ar gram un piedāvā izvēles variantus 
	}
	
	public static void getVerbs(Node node){
		//pieņem, ka gram vienmēr seko aiz vf
		//System.out.println("kaķis");
		Pattern p = Pattern.compile("(.*)trans.(.*)");		
		//System.out.println(node);
		if(node.hasChildNodes())
	     {    
	     NodeList NL=node.getChildNodes();            
	     for(int i=0;i<NL.getLength();i++)
	       {
	    	
	       Node childnode=NL.item(i);
	       
	       if (childnode.getParentNode().getNodeName() == "vf") System.out.println(childnode);
	       //if (childnode.getNodeValue().toString().endsWith("ties")||childnode.getNodeValue().toString().endsWith("t")) System.out.println(childnode);
	       
	       if ((childnode.getParentNode().getNodeName() == "vf") ){
    		   //kā arī (!childnode.getNodeValue().equals("ščit"))
	    	   // && (childnode.getNodeValue().endsWith("ties")||childnode.getNodeValue().endsWith("t"))
	    	   
		       verb = childnode.getNodeValue();
		       //System.out.println(verb);
		              
	       }else 
	    	   verb = ":)";//vai nu inicializācija sākumā ir lieka, vai arī
	       
	          	   
	       if ((childnode.getParentNode().getNodeName() == "gram") ){//  && (verb != "")
    		  	//	childnode.getNodeValue().matches(reg.izt.);  childnode.getParentNode().getParentNode().getPreviousSibling();
	    	   Matcher m = p.matcher(childnode.getNodeValue());
	    	   boolean b = m.matches();
	    	   
	    	   if (b == true){
	    		   
	    		   try {
	    			   System.out.println(verb);
			    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
	                           "verbs.txt"), true));
		                bw.write("vārds:" + verb + ", " + childnode.getParentNode().getNodeName()+": "+childnode.getNodeValue());//te jābūt verb nevis tam penterim
		                bw.newLine();
		                bw.close();
		        } catch (Exception e) {
		        }
	    	   }
		       		    
    	   
	       }
	       
	       getVerbs(childnode);
	       }
	     }
		
	}
}