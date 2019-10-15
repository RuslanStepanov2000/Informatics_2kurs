package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;


public class newclass {
    public static void readAndPrintXML() {
        // DOM parser - загружает весь xml-файл в память; ! для небольших документов
        try {
            // Создать построитель документа
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // из файла Создать объект документа для обхода дерева DOM документа
            Document document = builder.parse("nomer1.xml");

            // Получить корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("List of authors:\n===========>>>>");

            // Просматриваем все подэлементы корневого - т.е. контакты
            NodeList author = root.getChildNodes();
            for (int i = 0; i < author.getLength(); i++) {
                Node pers = author.item(i);
                // Если узел не текст, то заходим внутрь
                if (pers.getNodeName().equals("contain")) {                    //.getNodeType() != Node.TEXT_NODE) {
                    System.out.println("Author: " + pers.getAttributes().getNamedItem("name").getNodeValue() + "\n");
                    NodeList books = pers.getChildNodes();
                    for(int j = 0; j < books.getLength(); j++) {
                        Node book = books.item(j);
                        // Если нода не текст, то это один из параметров книги - печатаем
                        if (book.getNodeName().equals("book")) {
                            System.out.println(book.getNodeName() + ":" + book.getAttributes().getNamedItem("nameBook").getNodeValue()+
                                    "\npages: "+book.getAttributes().getNamedItem("numberOfPage").getNodeValue()+"\n");
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }



    }


    public static void main(String[] args) {
        // только чтение и вывод
        readAndPrintXML();


        // чтение, изменение файла xml
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("nomer1.xml");

            // Вызываем метод для добавления новой книги
            // addNew(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }


    private static void addNew(Document document) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        // Создаем нового по элементам
        Element pers = document.createElement("Contact");
        //
        Element first = document.createElement("firstName");
        // Устанавливаем значение текста внутри тега
        first.setTextContent("A");
        //
        Element last = document.createElement("lastName");
        last.setTextContent("S");
        //
        Element email = document.createElement("email");
        email.setTextContent("2015@er.ru");
        //

        // Устанавливаем атрибут
        pers.setAttribute("type", "???");
        pers.setAttribute("weight", "22");

        // Добавляем внутренние элементы книги в элемент <Book>
        pers.appendChild(first);
        pers.appendChild(last);
        pers.appendChild(email);

        // Добавляем  в корневой элемент
        root.appendChild(pers);

        // Записываем XML в файл
        writeDocument(document);
    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();

            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}