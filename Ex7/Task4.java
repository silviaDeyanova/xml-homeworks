//import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {

  private static boolean skipNL;

  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(false);
    DocumentBuilder builder = dbf.newDocumentBuilder();

    InputSource source = new InputSource("rss.xml");
    Document document = builder.parse(source);
    System.out.println(printXML(document.getDocumentElement()));
  }

  private static String printXML(Node rootNode) {
    String tab = "";
    skipNL = false;
    return (printXML(rootNode, tab));
  }

  //Принтиране на съдържанието
  private static String printXML(Node rootNode, String tab) {
    String print = "";
    if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
      //Извеждане на нов ред на елемента
      print += "\n" + tab + "<" + rootNode.getNodeName();
      //Вземане на атрибутите на елементите
      NamedNodeMap attributes = rootNode.getAttributes();
      //Итериране през атрибутите
      for (int j = 0; j < attributes.getLength(); j++) {
        Attr attr = (Attr) attributes.item(j);
        //Ако атрибутът не е null, да се изведе името му, = и стойността му
        if (attr != null) {
          print +=
            " " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"";
        }
      }
      print += ">";
    }
    //Вземане на наследниците и итериране през тях, при уловие че има такива
    //Ако има само root, да се изведе той
    NodeList nl = rootNode.getChildNodes();
    if (nl.getLength() > 0) {
      for (int i = 0; i < nl.getLength(); i++) {
        print += printXML(nl.item(i), tab + " ");
      }
    } else {
      if (rootNode.getNodeValue() != null) {
        print = rootNode.getNodeValue();
      }
      skipNL = true;
    }
    if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
      if (!skipNL) {
        print += "\n" + tab;
      }
      skipNL = false;
      print += "";
    }
    return (print);
  }
}
