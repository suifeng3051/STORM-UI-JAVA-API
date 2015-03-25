package storm.heaven.monitor.entity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import storm.heaven.monitor.StormMonitor;
import storm.heaven.monitor.entity.MonitorResult;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司
 * Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights Reserved.
 * BidPlanStructForm.java Created on 2015-3-23
 * Author: <a href=mailto:wanghd@bonree.com>王厚达</a>
 * @Title: TestMonitor.java
 * @Package bonree.monitor.ui
 * Description:
 * Version: 1.0
 ******************************************************************************/
public class TestMonitor {
	public static void main(String[] args) {
		
		try {
			//ClusterSummary clusterSummary=(ClusterSummary)StormMonitor.sendAndRcv(StormMonitor.GET, StormMonitor.clusterSummary);
			//TopologySummary topologySummary=(TopologySummary)StormMonitor.sendAndRcv(StormMonitor.GET, StormMonitor.topologySummary);
			//TopologyInfo topologyInfo=(TopologyInfo)StormMonitor.queryTopologyInfo(topology.getId());

			MonitorResult result=StormMonitor.getMonitorResult();
			
			System.out.println(result.toString());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
