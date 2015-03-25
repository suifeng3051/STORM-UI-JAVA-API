package storm.heaven.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import storm.heaven.monitor.entity.ClusterSummary;
import storm.heaven.monitor.entity.MonitorResult;
import storm.heaven.monitor.entity.Spout;
import storm.heaven.monitor.entity.Supervisor;
import storm.heaven.monitor.entity.SupervisorSummary;
import storm.heaven.monitor.entity.Topology;
import storm.heaven.monitor.entity.TopologyInfo;
import storm.heaven.monitor.entity.TopologySummary;
import storm.heaven.monitor.entity.UIEntity;
import storm.heaven.util.ConfUtil;


public class StormMonitor {

	public static final String ACCESS_PREFIX = "http://10.10.0.102:8080/api/v1/";
	public static final String clusterSummary = "cluster/summary";
	public static final String topologySummary = "topology/summary";
	public static final String supervisorSummary = "supervisor/summary";
	private static final String topologyInfo = "topology/";
	public static final String activateTopology = "topology/calcData/activate";
	
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	public static UIEntity sendAndRcv(String httpTpye, String operations) throws ClientProtocolException, IOException {
		//向storm ui发送请求，获取json消息
		String result=sendRequest(httpTpye, operations);
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	public static UIEntity sendAndRcv(String httpType, String operation, String topologyId) throws ClientProtocolException, IOException {
		//向storm ui发送请求，获取json消息
		String result=sendRequest(httpType, operation+topologyId);
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	private static UIEntity getUIEntity(String operation) {
		if(topologySummary.equals(operation)){
			return new TopologySummary();
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
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
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
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
	/**
	 * 概述：获取监测结果状态方法
	 * @Title: getMonitorResult
	 * @return MonitorResult
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	public static MonitorResult getMonitorResult() throws ClientProtocolException, IOException {
		//ClusterSummary clusterSummary=(ClusterSummary)StormMonitor.sendAndRcv(StormMonitor.GET, StormMonitor.clusterSummary);
		MonitorResult result=new MonitorResult();
		TopologySummary topologySummary=(TopologySummary)StormMonitor.sendAndRcv(StormMonitor.GET, StormMonitor.topologySummary);
		result.setTopologyError(getTopologyError(topologySummary)); 
		result.setBoltError(getBoltError());
		result.setTopologyInnerError(getTopologyInnerError(topologySummary.getTopologies()));
	
		return result;
	}
	/**
	 * 概述：获取topology内部错误方法
	 * @Title: getTopologyInnerError
	 * @param topologies
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	private static String getTopologyInnerError(List<Topology> topologies) throws ClientProtocolException, IOException {
		StringBuffer errorInfo=new StringBuffer();
		for(Topology topology :topologies){
			TopologyInfo topologyInfo=(TopologyInfo)StormMonitor.queryTopologyInfo(topology.getId());
			Spout spout=topologyInfo.getSpouts().get(0);//我们的topology只有一个spout
			if(spout.getFailed()!=null&&spout.getFailed()!=0){
				errorInfo.append(spout.getErrorHost()).append(":").append(" failed="+spout.getFailed()).append(" errorPort="+spout.getErrorPort()).append(" lastError="+spout.getLastError()).append(";");
			}
		}
		if(errorInfo.length()==0){
			errorInfo.append("无topology内部错误!");
		}
		return errorInfo.toString();
	}
	/**
	 * 概述：获取bolt错误信息方法
	 * @Title: getBoltError
	 * @return  String
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	private static String getBoltError() throws ClientProtocolException, IOException {
		SupervisorSummary supervisorSummary=(SupervisorSummary)StormMonitor.sendAndRcv(StormMonitor.GET, StormMonitor.supervisorSummary);
		StringBuffer errorInfo=new StringBuffer();
		Map<String, String> supervisorSummaryMap=new HashMap<String, String>();
		for(Supervisor supervisor:supervisorSummary.getSupervisors()){
			supervisorSummaryMap.put(supervisor.getHost(), supervisor.getSlotsTotal()+"-"+supervisor.getSlotsUsed());
		}
		StringBuffer errorBolt=new StringBuffer();
		StringBuffer errorTransfer=new StringBuffer();
		for(Entry<String, String> entry:ConfUtil.supervisorInConfigMap.entrySet()){
			if(!supervisorSummaryMap.containsKey(entry.getKey())){
				errorBolt.append(entry.getKey()).append(" 已挂掉!;");
			}else if(!supervisorSummaryMap.get(entry.getKey()).equals(entry.getValue())){
				errorTransfer.append(entry.getKey()).append("-").append(supervisorSummaryMap.get(entry.getKey())).append(" 已发生故障转移!;");
			}
		}
		errorInfo.append(errorBolt).append(errorTransfer);
		if(errorInfo.length()==0){
			errorInfo.append("无bolt异常信息!");
		}
		return errorInfo.toString();
	}
	/**
	 * 概述：获取topology的错误信息方法
	 * @Title: getTopologyError
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @user <a href=mailto:wanghouda@126.com>王厚达</a>
	 */
	private static String getTopologyError(TopologySummary topologySummary) throws ClientProtocolException, IOException {
		StringBuffer errorInfo=new StringBuffer();
		Map<String, String> topologySummaryMap=new HashMap<String, String>();
		for(Topology topology:topologySummary.getTopologies()){
			topologySummaryMap.put(topology.getName(), topology.getName());
		}
		for(Entry<String, String> entry:ConfUtil.topologyInConfigMap.entrySet()){
			if(!topologySummaryMap.containsKey(entry.getKey())){
				errorInfo.append(entry.getKey()).append(" 已挂掉!;");
			}
		}
		if(errorInfo.length()==0){
			errorInfo.append("无topology挂掉信息!");
		}
		return errorInfo.toString();
		
	}
	
}
