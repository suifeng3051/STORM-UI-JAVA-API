package storm.heaven.monitor.entity;

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
	private String errorHost;
	private String lastError;
	private String errorWorkerLogLink;
	private Integer failed;
	private String encodedSpoutId;
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
	public String getErrorHost() {
		return errorHost;
	}
	public void setErrorHost(String errorHost) {
		this.errorHost = errorHost;
	}
	public String getLastError() {
		return lastError;
	}
	public void setLastError(String lastError) {
		this.lastError = lastError;
	}
	public String getErrorWorkerLogLink() {
		return errorWorkerLogLink;
	}
	public void setErrorWorkerLogLink(String errorWorkerLogLink) {
		this.errorWorkerLogLink = errorWorkerLogLink;
	}
	public Integer getFailed() {
		return failed;
	}
	public void setFailed(Integer failed) {
		this.failed = failed;
	}
	public String getEncodedSpoutId() {
		return encodedSpoutId;
	}
	public void setEncodedSpoutId(String encodedSpoutId) {
		this.encodedSpoutId = encodedSpoutId;
	}
	

}
