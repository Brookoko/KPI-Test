package com.github;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XmlLoader
{
    public TestUser Load()
    {
        try
        {
            File file = new File("data.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            Element userNode = (Element) doc.getElementsByTagName("user").item(0);
            TestUser testUser = new TestUser();
            testUser.username = userNode.getElementsByTagName("username").item(0).getTextContent();
            testUser.email = userNode.getElementsByTagName("email").item(0).getTextContent();
            testUser.password = userNode.getElementsByTagName("password").item(0).getTextContent();
            return testUser;
        } catch (ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
