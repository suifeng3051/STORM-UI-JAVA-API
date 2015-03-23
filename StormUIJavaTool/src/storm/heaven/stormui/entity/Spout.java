package storm.heaven.stormui.entity;
/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司
 * Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights Reserved.
 * BidPlanStructForm.java Created on 2015-3-23
 * Author: <a href=mailto:wanghd@bonree.com>王厚达</a>
 * @Title: Spout.java
 * @Package bonree.monitor.ui
 * Description:
 * Version: 1.0
 ******************************************************************************/
public class Spout {
	private Integer executors;
	private Integer emitted;
	private Integer errorLapsedSecs;
	private String completeLatency;
	private Integer transferred;
	private Integer acked;
	private String errorPort;
	private String spoutId;
	private Integer tasks;
	public Integer getExecutors() {
		return executors;
	}
	public void setExecutors(Integer executors) {
		this.executors = executors;
	}
	public Integer getEmitted() {
		return emitted;
	}
	public void setEmitted(Integer emitted) {
		this.emitted = emitted;
	}
	public Integer getErrorLapsedSecs() {
		return errorLapsedSecs;
	}
	public void setErrorLapsedSecs(Integer errorLapsedSecs) {
		this.errorLapsedSecs = errorLapsedSecs;
	}
	public String getCompleteLatency() {
		return completeLatency;
	}
	public void setCompleteLatency(String completeLatency) {
		this.completeLatency = completeLatency;
	}
	public Integer getTransferred() {
		return transferred;
	}
	public void setTransferred(Integer transferred) {
		this.transferred = transferred;
	}
	public Integer getAcked() {
		return acked;
	}
	public void setAcked(Integer acked) {
		this.acked = acked;
	}
	public String getErrorPort() {
		return errorPort;
	}
	public void setErrorPort(String errorPort) {
		this.errorPort = errorPort;
	}
	public String getSpoutId() {
		return spoutId;
	}
	public void setSpoutId(String spoutId) {
		this.spoutId = spoutId;
	}
	public Integer getTasks() {
		return tasks;
	}
	public void setTasks(Integer tasks) {
		this.tasks = tasks;
	}
	

}
