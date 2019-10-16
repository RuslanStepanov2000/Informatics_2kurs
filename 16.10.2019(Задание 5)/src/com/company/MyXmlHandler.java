package com.company;

import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class MyXmlHandler {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startDocument() {
            // Тут будет логика реакции на начало документа
        }

        @Override
        public void endDocument() {
            // Тут будет логика реакции на конец документа
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            // Тут будет логика реакции на начало элемента
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            // Тут будет логика реакции на конец элемента
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            // Тут будет логика реакции на текст между элементами
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) {
            // Тут будет логика реакции на пустое пространство внутри элементов (пробелы, переносы строчек и так далее).
        }
    }
}

