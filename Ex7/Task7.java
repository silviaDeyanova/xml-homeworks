import java.io.IOException;
import java.io.StringWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.xmlOutputFactory;
import javax.xml.stream.xmlStreamWriter;

public class Main {

  public static void main(String[] args) {
    try {
      StringWriter stringWriter = new StringWriter();
      xmlOutputFactory xmlOutputFactory = xmlOutputFactory.newInstance();
      xmlStreamWriter xmlStreamWriter = xmlOutputFactory.createxmlStreamWriter(
        stringWriter
      );
      //Записваме XML хедър, елементите bookstore, book
      xmlStreamWriter.writeStartDocument();
      xmlStreamWriter.writeStartElement("bookstore");
      xmlStreamWriter.writeStartElement("book");
      xmlStreamWriter.writeAttribute("category", "COOKING");
      //Създаваме поделементите чрез лично създадения метод
      createSimpleElement(
        xmlStreamWriter,
        "title",
        "lang",
        "en",
        "Everyday Italian"
      );
      createSimpleElement(
        xmlStreamWriter,
        "author",
        null,
        null,
        "Giada De Laurentiis"
      );
      createSimpleElement(xmlStreamWriter, "year", null, null, "2005");
      createSimpleElement(xmlStreamWriter, "price", null, null, "30.00");
      xmlStreamWriter.writeEndElement();
      xmlStreamWriter.writeStartElement("book");
      xmlStreamWriter.writeAttribute("category", "CHILDREN");
      createSimpleElement(
        xmlStreamWriter,
        "title",
        "lang",
        "en",
        "Harry Potter"
      );
      createSimpleElement(
        xmlStreamWriter,
        "author",
        null,
        null,
        "J K. Rowling"
      );
      createSimpleElement(xmlStreamWriter, "year", null, null, "2005");
      createSimpleElement(xmlStreamWriter, "price", null, null, "29.99");
      xmlStreamWriter.writeEndElement();
      xmlStreamWriter.writeStartElement("book");
      xmlStreamWriter.writeAttribute("category", "WEB");
      createSimpleElement(
        xmlStreamWriter,
        "title",
        "lang",
        "en",
        "Learning XML"
      );
      createSimpleElement(xmlStreamWriter, "author", null, null, "Erik T. Ray");
      createSimpleElement(xmlStreamWriter, "year", null, null, "2003");
      createSimpleElement(xmlStreamWriter, "price", null, null, "39.95");
      xmlStreamWriter.writeEndElement();
      xmlStreamWriter.writeEndDocument();
      //Правим запис на буферния изход и затваряме xmlStreamWriter
      xmlStreamWriter.flush();
      xmlStreamWriter.close();
      String xmlString = stringWriter.getBuffer().toString();
      stringWriter.close();
      System.out.println(xmlString);
    } catch (XMLStreamException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Функция за създаване на елементите по подадени име на елемента, атрибут, стойност на атрибута и съдържание
  public static void createSimpleElement(
    xmlStreamWriter xmlStreamWriter,
    String elementName,
    String attributeName,
    String attributeValue,
    String strValue
  ) {
    try {
      if (xmlStreamWriter != null && elementName != null) {
        xmlStreamWriter.writeStartElement(elementName);
        if (attributeName != null) {
          xmlStreamWriter.writeAttribute(attributeName, attributeValue);
        }
        if (strValue != null) {
          xmlStreamWriter.writeCharacters(strValue);
        }
        xmlStreamWriter.writeEndElement();
      }
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }
  }
}
