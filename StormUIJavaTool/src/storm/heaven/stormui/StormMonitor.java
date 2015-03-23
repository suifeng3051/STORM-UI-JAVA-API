package storm.heaven.stormui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import storm.heaven.stormui.entity.ClusterSummary;
import storm.heaven.stormui.entity.SupervisorSummary;
import storm.heaven.stormui.entity.TopologyInfo;
import storm.heaven.stormui.entity.TopoloySummary;
import storm.heaven.stormui.entity.UIEntity;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司 Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights
 * Reserved. BidPlanStructForm.java Created on 2015-3-20 Author: <a
 * href=mailto:wanghd@bonree.com>王厚达</a>
 * 
 * @Title: StormUIDemo.java
 * @Package bonree.utils Description: Version: 1.0
 ******************************************************************************/
public class StormMonitor {

	public static final String ACCESS_PREFIX = "http://10.10.0.102:8080/api/v1/";
	public static final String clusterSummary = "cluster/summary";
	public static final String supervisorSummary = "supervisor/summary";
	public static final String topologySummary = "topology/summary";
	public static final String activateTopology = "topology/calcData/activate";
	private static final String topologyInfo = "topology/";
	// 更多操作见：https://github.com/apache/storm/blob/master/STORM-UI-REST-API.md#apiv1clusterconfiguration-get
	public static final String POST = "HTTP_POST";
	public static final String GET = "HTTP_GET";

	public static void main(String[] args) throws ClientProtocolException, IOException {
		//Topoloies topoloies =(Topoloies)sendAndRcv(HTTP_GET, topologySummary);
		SupervisorSummary supervisors =(SupervisorSummary)sendAndRcv(GET, supervisorSummary);
		System.out.println(supervisors.toString());
	}
	/**
	 * 概述：
	 * @Title: sendRequest
	 * @param httpTpye //操作类型，是get还是post
	 * @param operations//操作，获取哪种信息
	 * @return UIEntity
	 * @throws ClientProtocolException
	 * @throws IOException
	 * UIEntity
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static UIEntity sendAndRcv(String httpTpye, String operations) throws ClientProtocolException, IOException {
		//向storm ui发送请求，获取json消息
		String result=sendRequest(httpTpye, operations);
		System.out.println(result);
		//根据获取的信息类型获得不同的消息实体
		UIEntity entity=getUIEntity(operations);
		//用相应的消息实体解析返回的json串
		entity.parse(result);
		return entity;
	}
	/**
	 * 概述：向stormUI发送请求方法
	 * @Title: sendRequest
	 * @param httpTpye //操作类型，是get还是post
	 * @param operations//操作，获取哪种信息
	 * @return UIEntity
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static String sendRequest(String httpTpye, String operations) throws ClientProtocolException, IOException {
		String result;//声明返回的json字符串
		String uri = ACCESS_PREFIX + operations;
		// 创建HttpClient实例
		HttpClient httpclient = new DefaultHttpClient();
		if (httpTpye.equals(GET)) {
			HttpGet httpgets = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpgets);
			result = parseResponseToString(response);
			httpgets.abort();
		} else if (httpTpye.equals(POST)) {
			HttpPost httpPost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httpPost);
			result = parseResponseToString(response);
			httpPost.abort();
		} else {
			throw new RuntimeException("不支持的httpType操作类型!");
		}
		return result;
	}
	/**
	 * 概述：
	 * @Title: queryTopologyInfo
	 * @param topologyId
	 * @return UIEntity
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static UIEntity queryTopologyInfo(String topologyId) throws ClientProtocolException, IOException {
		UIEntity result=StormMonitor.sendAndRcv(StormMonitor.GET,topologyInfo,topologyId);
		return result;
	}
	/**
	 * 概述：含topologyId参数的请求方法
	 * @Title: sendAndRcv
	 * @param httpType
	 * @param operation
	 * @param topologyId
	 * @return UIEntity
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	public static UIEntity sendAndRcv(String httpType, String operation, String topologyId) throws ClientProtocolException, IOException {
		//向storm ui发送请求，获取json消息
		String result=sendRequest(httpType, operation+topologyId);
		System.out.println(result);
		//根据获取的信息类型获得不同的消息实体
		UIEntity entity=getUIEntity(operation);
		//用相应的消息实体解析返回的json串
		entity.parse(result);
		return entity;
	}
	/**
	 * 概述：根据操作类型返回不同的结果实体
	 * @Title: getUIEntity
	 * @param operation
	 * @return UIEntity
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	private static UIEntity getUIEntity(String operation) {
		if(topologySummary.equals(operation)){
			return new TopoloySummary();
		}else if(supervisorSummary.equals(operation)){
			return new SupervisorSummary();
		}else if(clusterSummary.equals(operation)){
			return new ClusterSummary();
		}else if(topologyInfo.equals(operation)){
			return new TopologyInfo();
		}else{
			throw new RuntimeException("不支持的实体解析类型!"+operation);
		}
		
	}
	

	/**
	 * 概述：把相应结果解析成字符串方法
	 * @Title: parseResponseToString
	 * @param response
	 * @return String
	 * @throws IllegalStateException
	 * @throws IOException
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	private static String parseResponseToString(HttpResponse response) throws IllegalStateException, IOException {
		String result;
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instreams = entity.getContent();
			result = convertStreamToString(instreams);
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 概述：把输入流解析成字符串方法
	 * @Title: convertStreamToString
	 * @param is
	 * @return String
	 * @user <a href=mailto:wanghd@bonree.com>王厚达</a>
	 */
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
