package storm.heaven.util;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司
 * Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights Reserved.
 * BidPlanStructForm.java Created on 2015-3-24
 * Author: <a href=mailto:wanghd@bonree.com>王厚达</a>
 * @Title: ConfUtil.java
 * @Package bonree.monitor.util
 * Description:
 * Version: 1.0
 ******************************************************************************/
public class ConfUtil {
	/**
	 * 默认配置文件存放目录名称
	 * */
	public static void main(String[] args) {
		System.out.println(ConfUtil.topologyInConfigMap);
		System.out.println(ConfUtil.supervisorInConfigMap);
	}
	
	private final static String DEFAULT_CONFIG_DIR = "conf";
	public static Map<String, String> topologyInConfigMap=new HashMap<String, String>();
	public static Map<String, String> supervisorInConfigMap=new HashMap<String, String>();;
	static{
		String path = getFilePath("topology.xml");
		try{
			Document doc = createDoc(path);
			NodeList topologyList =getNodeList(doc, "//topologysummary/topology");
			for (int i=0; i<topologyList.getLength(); i++) {
				NamedNodeMap att = topologyList.item(i).getAttributes();
				String topologyName=att.getNamedItem("name").getNodeValue();
				topologyInConfigMap.put(topologyName,topologyName);
			}
			NodeList boltList = getNodeList(doc, "//supervisorsummary/bolt");
			for (int i=0; i<boltList.getLength(); i++) {
				NamedNodeMap att = boltList.item(i).getAttributes();
				String host=att.getNamedItem("host").getNodeValue();
				String value=att.getNamedItem("slotsTotal").getNodeValue()+"-"+att.getNamedItem("slotsUsed").getNodeValue();
				supervisorInConfigMap.put(host, value);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文档对象
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document createDoc(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		return builder.parse(path);
	}
	/**
	 * 概述：获取配置文件路径名
	 * 
	 * @Title: getFilePath
	 * @param fileName
	 * @return String
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static String getFilePath(String fileName) {
		return getConfigDir() + fileName;
	}
	/**
	 * 概述：获取配置文件存放路径
	 * 
	 * @Title: getConfigDir
	 * @return String
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static String getConfigDir() {
		String systemPath=System.getProperty("user.dir") ;;
		return new StringBuffer().append(systemPath).append(File.separator).append(DEFAULT_CONFIG_DIR).append(File.separator).toString();
	}
	

	public static NodeList getNodeList(Document doc, String expression)
			throws XPathExpressionException {

		return (NodeList) getResult(doc, XPathConstants.NODESET, expression);
	}
	private static Object getResult(Document doc, QName returnType,
			String expression) throws XPathExpressionException {
		
		XPathExpression pathExpression = getPathExpression(expression);

		return pathExpression.evaluate(doc, returnType);

	}
	private static XPathExpression getPathExpression(String expression)
			throws XPathExpressionException {
		
		XPathFactory xPathFactory = XPathFactory.newInstance();
		
		XPath xPath = xPathFactory.newXPath();
		return xPath.compile(expression);
	}

}
