package resursi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lv.semti.morphology.lexicon.Paradigm;


public class dom {

	/**
	 * @param args
	 */
	
	/*public static void getSubNodes(Node x){		
		NodeList subnodes = x.getChildNodes();
	}*/
	
	
	public static void main(String[] args)throws Exception{
				
		//izveido DOM koku no Skaidrojosās vārdnīcas faila
		
		Document doc = null;
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		doc = docBuilder.parse(new File("Skaidrojosa Vardnica.xml"));//ignore tuksumus pie lasisanas?
		
		Node node = doc.getDocumentElement();
		
		traverseAllNodes(node);
				
		/*	
		NodeList nodes = node.getChildNodes();//šitādu uztaisi arī katram <s> obkjektam, salasi vf un avots tagu saturu un tad apstrada to, piemekle atbilstošū paradigmu un uztaisi izvadi jaunā xml faila
		
		for (int i = 0; i < nodes.getLength(); i++) {
			 
			if ("s".equalsIgnoreCase(nodes.item(i).getNodeName())){
				NodeObject skirklis = new NodeObject(nodes.item(i));
				System.out.println(skirklis.getVf());
				
				izeja.write("<s>"+"<vf>"+skirklis.getVf()+"</vf>"+"<gram>"+skirklis.getGram()+"</gram>"+"<avots>"+skirklis.getAvots()+"</avots>"+"</s>");
				
			}
		}*/
		
		
	}
//ko darām ar atribūtu nodēm - vajag, nevajag?
	// 
	private static void traverseAllNodes(Node node)
	
	   {   
				
	   if(node.hasChildNodes())
	     {    
	     NodeList NL=node.getChildNodes();            
	     for(int i=0;i<NL.getLength();i++)
	       {
	    	
	       Node childnode=NL.item(i);
	             
	              
	       /*if ((childnode.getNodeName() == "#text") && (childnode.getNodeValue().trim() != null)){
	    	   System.out.print("Name:"+childnode.getNodeName());
		       System.out.print("Value:/"+childnode.getNodeValue()+"/\n");
		       
		       try {
		    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                           "testImport.txt"), true));
	                bw.write("Name: "+childnode.getNodeName()+" "+"Value:/"+childnode.getNodeValue());
	                bw.newLine();
	                bw.close();
	        } catch (Exception e) {
	        }		       
	       }*/
	       
	       if (childnode.getNodeName() == "#text"){
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
	    	   }
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
	    	   }
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
	    	   /*else  if (isIgnorable(childnode)){  
	    		   childnode = childnode.getFirstChild();
	    	   }
	       
    	   else  if (!isIgnorable(childnode)){  
    	   System.out.print("Name:"+childnode.getNodeName());
	       System.out.print("Value:/"+childnode.getNodeValue()+"/\n");
	       
	       try {
	    	   BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                       "testImport.txt"), true));
                bw.write("Name: "+childnode.getNodeName()+" "+"Value:/"+childnode.getNodeValue());
                bw.newLine();
                bw.close();
        } catch (Exception e) {
        }	
    	   }*/
	       }
	       
	       traverseAllNodes(childnode);
	       }
	     }
	   
	   } 
	
	private static boolean isWhiteSpace(Node n){
		if ((n.getNodeName() == "#text") && (n.getNodeValue() == "")){
			return true;
		}
		else return false;
	}
	
	private static boolean isIgnorable(Node n){
		return (isWhiteSpace(n) == true);//tālāk - skatities, vai Nodi var ignorēt un ja var, tad vinu nemaz neielasi bet parej pie nakamas
	}
}