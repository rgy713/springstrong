

package com.xspeeder.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class XMLParser {

    public static String[] getMailMessage(String filename, String msgid) throws Exception {

        File file = new File(filename);
        String[] result = {null, null};

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/messages/message[@id='" + msgid + "']/subject";
            result[0] = xPath.compile(expression).evaluate(doc);

            expression = "/messages/message[@id='" + msgid + "']/content";
            result[1] = xPath.compile(expression).evaluate(doc);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        } catch (SAXException e) {
            e.printStackTrace();
            throw e;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw e;

        }

        return result;
    }

    public static String[] getLogMessage(String filename, String msgid) throws Exception {

        File file = new File(filename);
        String[] result = {null, null};

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/messages/message[@id='" + msgid + "']/log";
            result[0] = xPath.compile(expression).evaluate(doc);

            expression = "/messages/message[@id='" + msgid + "']/param";
            result[1] = xPath.compile(expression).evaluate(doc);

        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        } catch (SAXException e) {
            e.printStackTrace();
            throw e;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw e;

        }

        return result;
    }

    public static String getConfigParam(String param) {

        File file = new File(ContextUtils.webRootPath() + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "server.config.xml");
        String result = "";

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = param;
            try {
                result = xPath.compile(expression).evaluate(doc);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (SAXException e) {
            e.printStackTrace();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getClientConfigParam(String param) {

        File file = new File(ContextUtils.webRootPath() + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "client.config.xml");
        String result = "";

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = param;
            try {
                result = xPath.compile(expression).evaluate(doc);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

        } catch (SAXException e) {
            e.printStackTrace();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getConfigValue(XPath xPath, Document doc, String expression) {
        String value = "";
        try {
            value = xPath.compile(expression).evaluate(doc);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return value;
    }

    public static Map<String, String> getConfigParams() {

        File file = new File(ContextUtils.webRootPath() + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "server.config.xml");
        String value = "";

        Map<String, String> resultMap = new HashMap<String, String>();
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/CONFIG/MAIN/PROJECTNAME";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

            expression = "/CONFIG/JOGGER_USER_SITE/IMAGE_URL";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

            expression = "/CONFIG/WEBSERVICE/URL";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

            expression = "/CONFIG/WEBSERVICE/NAMESPACE";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

            expression = "/CONFIG/PATH/IMAGES";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

            expression = "/CONFIG/PATH/STORAGE";
            value = getConfigValue(xPath, doc, expression);
            resultMap.put(expression, value);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (SAXException e) {
            e.printStackTrace();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
