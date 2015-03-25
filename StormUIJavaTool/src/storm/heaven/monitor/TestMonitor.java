package storm.heaven.monitor;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import storm.heaven.monitor.entity.MonitorResult;


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
