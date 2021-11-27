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

class SAXValidator implements ContentHandler {

  Locator locator;
  OutputStreamWriter outputStreamWriter;
  private final Integer TAB_SIZE = 4;
  private String currentElementName;
  private boolean titleDetected = false;
  private int countOfTitle = 0;
  private boolean linkDetected = false;
  private int countOfLink = 0;
  private boolean descriptionDetected = false;
  private int countOfDescription = 0;
  private boolean itemDetected = false;
  private int countOfItem = 0;

  public SAXValidator(OutputStreamWriter outputStreamWriter) {
    this.outputStreamWriter = outputStreamWriter;
  }

  @Override
  public void setDocumentLocator(Locator locator) {
    this.locator = locator;
  }

  @Override
  public void startDocument() throws SAXException {
    //..
  }

  @Override
  public void endDocument() throws SAXException {
    // ...
  }

  @Override
  public void startElement(
    String uri,
    String localName,
    String qName,
    Attributes atts
  )
    throws SAXException {
    currentElementName = qName;
    validateVersion(atts);
    //Спрямо името на елемента се изпълняват съответните действия
    if (qName.equals("item")) {
      titleDetected = false;
      linkDetected = false;
      descriptionDetected = false;
      countOfTitle = 0;
      countOfDescription = 0;
      countOfLink = 0;
      itemDetected = true;
      ++countOfItem;
    }

    if (qName.equals("title")) {
      titleDetected = true;
      ++countOfTitle;
    }

    if (qName.equals("link")) {
      linkDetected = true;
      ++countOfLink;
    }

    if (qName.equals("description")) {
      descriptionDetected = true;
      ++countOfDescription;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName)
    throws SAXException {
    //Проверка  дали елемента item има поделементи title, link и description и дали всеки от тях се среща точно един път
    if (localName.equals("item")) {
      if (
        !(
          titleDetected &&
          linkDetected &&
          descriptionDetected &&
          countOfTitle == 1 &&
          countOfLink == 1 &&
          countOfDescription == 1
        )
      ) {
        reportError(
          "Item must have one subset of the sequence: title, link, description."
        );
      }
    }
    //Проверка дали елементът channel съдържа поне 2 и не повече от 10 поделемента item
    if (localName.equals("channel")) {
      if (!(itemDetected && countOfItem >= 2 && countOfItem <= 10)) {
        reportError("Number of elements item must be between 2 and 10: ");
      }
    }
  }

  @Override
  public void characters(char[] chars, int start, int length)
    throws SAXException {
    //..
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

  //Извеждане на грешката с отстъп
  private void printIndented(String what) {
    try {
      outputStreamWriter.write(what);
      outputStreamWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void reportError(String cause) {
    //Извеждане на грешка, като се посочва и на кой ред и колона е грешката
    printIndented(
      String.format(
        "\r\nError: %s on line %d column %d.",
        cause,
        locator.getLineNumber(),
        locator.getColumnNumber()
      )
    );
  }

  private void validateVersion(Attributes atts) {
    if (atts.getLength() > 0) {
      try {
        //Проверка за стойността на атрибута version на елемента rss
        if (
          currentElementName.equals("rss") &&
          (Integer.parseInt(atts.getValue("version")) < 0)
        ) {
          reportError(
            "Attribute version is expected to have a positive integer value: "
          );
        }
      } catch (NumberFormatException e) {
        reportError(
          String.format(
            "Wrong value for version: %s (Attribute version is expected to have a positive integer value):",
            atts.getValue("version")
          )
        );
      }
    }
  }
}