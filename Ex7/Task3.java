import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

class SAXTransformator implements ContentHandler {

  private class Item {

    String title;
    String link;
    String description;
  }

  Locator locator;
  OutputStreamWriter outputStreamWriter;
  private final Integer TAB_SIZE = 4;
  private String currentElement;
  private Integer indent;
  private Item currentItem;
  boolean inItem = false;

  public SAXTransformator(OutputStreamWriter outputStreamWriter) {
    this.outputStreamWriter = outputStreamWriter;
  }

  @Override
  public void setDocumentLocator(Locator locator) {
    this.locator = locator;
    indent = 0;
  }

  @Override
  public void startDocument() throws SAXException {
    printIndented("<!DOCTYPE html>");
    printIndented("<html>");
    // Увеличаване на брояча, за да се принтира по-навътре следващия елементF
    ++indent;
    printIndented("<head><title>List of items</title></head>");
    printIndented("<body>");
    ++indent;
    //Създаване на таблица с три колони, в които са разпределени стойностите на елементите title, link, description
    printIndented("<table style=\"border: 1px solid black;\">");
    ++indent;
    printIndented(
      "<thead><tr><th>Title</th><th>Link</th><th>Description</th></tr></thead>"
    );
    printIndented("<tbody>");
    ++indent;
  }

  @Override
  public void endDocument() throws SAXException {
    // Намаляване на брояча, за да се принтира по-наляво затварящия таг
    --indent;
    printIndented("</tbody>");
    --indent;
    printIndented("</table>");
    --indent;
    printIndented("</body>");
    --indent;
    printIndented("</html>");
  }

  @Override
  public void startElement(
    String uri,
    String localName,
    String qName,
    Attributes atts
  )
    throws SAXException {
    currentElement = qName;

    if ("item".equals(currentElement)) {
      currentItem = new Item();
      inItem = true;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName)
    throws SAXException {
    //Ако текущият елемент е бил item, принтираме елементите във вид на таблица
    if ("item".equals(localName)) {
      printIndented(
        "<tr><td>" +
        currentItem.title +
        "</td><td>" +
        currentItem.link +
        "</td><td>" +
        currentItem.description +
        "</td></tr>"
      );
      inItem = false;
    }
  }

  @Override
  public void characters(char[] chars, int start, int length)
    throws SAXException {
    String s = new String(chars, start, length).trim();

    if (inItem && s.length() > 0) {
      if ("title".equals(currentElement)) {
        currentItem.title = s;
      }
      if ("link".equals(currentElement)) {
        currentItem.link = s;
      }
      if ("description".equals(currentElement)) {
        if (currentItem.description == null) {
          currentItem.description = s;
        } else {
          currentItem.description += s;
        }
      }
    }
  }

  @Override
  public void startPrefixMapping(String prefix, String uri)
    throws SAXException {
    // ...
  }

  @Override
  public void endPrefixMapping(String prefix) throws SAXException {
    // ...
  }

  @Override
  public void ignorableWhitespace(char[] chars, int start, int length)
    throws SAXException {
    // ...
  }

  @Override
  public void processingInstruction(String target, String data)
    throws SAXException {
    // ...
  }

  @Override
  public void skippedEntity(String name) throws SAXException {
    // ...
  }

  private void printIndented(String what) {
    try {
      if (indent > 0) {
        outputStreamWriter.write(
          String.format("%1$" + (indent * TAB_SIZE) + "s", "")
        );
      }
      outputStreamWriter.write(what + "\r\n");
      outputStreamWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
