package storm.heaven.stormui.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司
 * Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights Reserved.
 * BidPlanStructForm.java Created on 2015-3-23
 * Author: <a href=mailto:wanghd@bonree.com>王厚达</a>
 * @Title: TopologyInfo.java
 * @Package bonree.monitor.ui
 * Description:
 * Version: 1.0
 ******************************************************************************/
public class TopologyInfo extends UIEntity{
	private Integer msgTimeout;
	private Spout spout;
	@Override
	public void parse(String result) throws JsonParseException, JsonMappingException, IOException {
		super.parse(result, new Topology());
	}

}
