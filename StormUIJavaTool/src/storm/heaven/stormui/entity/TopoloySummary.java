package storm.heaven.stormui.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司 Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights
 * Reserved. BidPlanStructForm.java Created on 2015-3-23 Author: <a
 * href=mailto:wanghd@bonree.com>王厚达</a>
 * 
 * @Title: Topoloies.java
 * @Package bonree.ui Description: Version: 1.0
 ******************************************************************************/
public class TopoloySummary extends UIEntity {
	private static final String TOPOLOGY_KEY="topologies";
	private List<Topology> topologies;

	public List<Topology> getTopologies() {
		return topologies;
	}

	public void setTopologies(List<Topology> topologies) {
		this.topologies = topologies;
	}

	public void parse(String json) throws JsonParseException, JsonMappingException, IOException {
		// 解包接收到的字符串
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = mapper.readValue(json, new TypeReference<Object>() {
		});
		Object topologyInfo = map.get(TOPOLOGY_KEY);
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, String>> topologyList = (ArrayList<HashMap<String, String>>) topologyInfo;
		topologies = new ArrayList<Topology>();
		for (HashMap<String, String> topologyMap : topologyList) {
			Topology topology = new Topology();
			super.parse(topologyMap, topology);
			topologies.add(topology);
		}
	}
}
