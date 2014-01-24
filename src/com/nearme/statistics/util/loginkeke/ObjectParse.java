package com.nearme.statistics.util.loginkeke;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.oppo.base.security.Hmac;
import com.oppo.base.xml.XmlEntityParser;

@SuppressWarnings("deprecation")
public class ObjectParse extends XmlEntityParser {
	private StringBuilder signValueBuilder;
	private String inputSignValue;
	private boolean isSignLegal;
	
	/**
     * 提供额外处理属性的方法,属性处理完成后对根节点的处理
     * @param rootNode
     * @return
     */
	@Override
    protected void handlePropertyWhenGenerateEnd(Document doc, Node rootNode) {
    	//签名
    	String signValue = getSignValue(signValueBuilder.toString());
    	//添加签名节点
    	Element subEle = doc.createElement("OPPOIdentity");
    	subEle.appendChild(doc.createTextNode(signValue));

    	rootNode.appendChild(subEle);
    }
    
    /**
     * 提供额外处理属性的方法,在生成xml时若有特殊要求可覆盖此方法
     * 如利用节点进行签名等
     * @param propertyString 属性的toString值
     * @return
     */
	@Override
    protected void handlePropertyWhenGenerate(String propertyString) {
    	signValueBuilder.append(propertyString);
    }
    
    /**
     * 提供额外处理xml text节点的方法,在解析xml时若有特殊要求可覆盖此方法
     * 如利用节点进行签名等
     * @param xmlText xml text节点值
     * @return
     */
	@Override
    protected void handleXmlTextWhenParse(String nodeName, String xmlText) {
    	if("OPPOIdentity".equals(nodeName)) {
    		inputSignValue = xmlText;
    	} else {
    		signValueBuilder.append(xmlText);
    	}
    }
    
    /**
     * 提供额外处理xml text的方法,在解析xml完成时若有特殊要求可覆盖此方法
     * 如利用节点进行签名验证等
     * @param xmlText xml text节点值
     * @return
     */
	@Override
    protected void handleXmlTextWhenParseEnd() {
    	//签名
    	String signValue = getSignValue(signValueBuilder.toString());
    	
    	isSignLegal = signValue.equals(inputSignValue);
    }
    
    private String getSignValue(String value) {
    	return Hmac.hmacSign(value, "a4Gs#vHnmlr54PzdBT3");
    }
    
    protected void clearState() {
    	signValueBuilder = new StringBuilder();
    	inputSignValue = null;
    	isSignLegal = false;
    }

	/**
	 * 获取签名是否合法
	 * @return  the isSignLegal
	 * @since   Ver 1.0
	 */
	public boolean isSignLegal() {
		return isSignLegal;
	}
}
