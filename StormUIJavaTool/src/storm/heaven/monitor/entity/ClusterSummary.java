package storm.heaven.monitor.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

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
