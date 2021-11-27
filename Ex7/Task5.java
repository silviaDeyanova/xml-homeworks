import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DOM2 {
  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(false);
    DocumentBuilder builder = dbf.newDocumentBuilder();
    //xml документът, който ще бъде променян
    InputSource source = new InputSource("rss.xml");
    Document document = builder.parse(source);
    processTree(document);

    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer writer = tf.newTransformer();
    writer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
    writer.transform(
      new DOMSource(document),
      new StreamResult(new File("rss_new.xml"))
    );
  }

  private static void processTree(Document document) {
    NodeList linkList = document.getElementsByTagName("link");
    NodeList itemList = document.getElementsByTagName("item");

    //Итериране през списъка на всички елементи с име link и премахване на елементи до достигане на 10-тия
    for (int i = itemList.getLength() - 1; i >= 10; --i) {
      Element item = (Element) itemList.item(i);
      item.getParentNode().removeChild(item);
    }

    for (int i = linkList.getLength() - 1; i >= 0; --i) {
      Element link = (Element) linkList.item(i);
      Element item = (Element) link.getParentNode();

      //Превръщане на поделемента link в атрибут на item
      if ("item".equals(item.getNodeName())) {
        item.setAttribute("link", link.getTextContent().trim());
        item.removeChild(link);
      }
    }

    //Създаванене на нов елемент sponsor, поделемент на channel
    Element sponsor = document.createElement("sponsor");
    sponsor.setTextContent("IBM");
    document.getElementsByTagName("channel").item(0).appendChild(sponsor);
  }
}
