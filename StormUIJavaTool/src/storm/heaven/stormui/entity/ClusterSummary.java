package storm.heaven.stormui.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司 Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights
 * Reserved. BidPlanStructForm.java Created on 2015-3-23 Author: <a
 * href=mailto:wanghd@bonree.com>王厚达</a>
 * 
 * @Title: StormCluster.java
 * @Package bonree.monitor.ui Description: Version: 1.0
 ******************************************************************************/
public class ClusterSummary extends UIEntity {
	private String stormVersion;
	private String nimbusUptime;
	private Integer supervisors;
	private Integer slotsTotal;
	private Integer slotsUsed;
	private Integer slotsFree;
	private Integer executorsTotal;
	private Integer tasksTotal;

	public String getStormVersion() {
		return stormVersion;
	}

	public void setStormVersion(String stormVersion) {
		this.stormVersion = stormVersion;
	}

	public String getNimbusUptime() {
		return nimbusUptime;
	}

	public void setNimbusUptime(String nimbusUptime) {
		this.nimbusUptime = nimbusUptime;
	}

	public Integer getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(Integer supervisors) {
		this.supervisors = supervisors;
	}

	public Integer getSlotsTotal() {
		return slotsTotal;
	}

	public void setSlotsTotal(Integer slotsTotal) {
		this.slotsTotal = slotsTotal;
	}

	public Integer getSlotsUsed() {
		return slotsUsed;
	}

	public void setSlotsUsed(Integer slotsUsed) {
		this.slotsUsed = slotsUsed;
	}

	public Integer getSlotsFree() {
		return slotsFree;
	}

	public void setSlotsFree(Integer slotsFree) {
		this.slotsFree = slotsFree;
	}

	public Integer getExecutorsTotal() {
		return executorsTotal;
	}

	public void setExecutorsTotal(Integer executorsTotal) {
		this.executorsTotal = executorsTotal;
	}

	public Integer getTasksTotal() {
		return tasksTotal;
	}

	public void setTasksTotal(Integer tasksTotal) {
		this.tasksTotal = tasksTotal;
	}
	@Override
	public void parse(String json) throws JsonParseException, JsonMappingException, IOException {
		super.parse(json, this);
	}

}
