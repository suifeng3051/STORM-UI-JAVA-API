package storm.heaven.monitor.entity;

public class MonitorResult {
	private String topologyError;
	private String boltError;
	private String topologyInnerError;
	public String getTopologyError() {
		return topologyError;
	}
	public void setTopologyError(String topologyError) {
		this.topologyError = topologyError;
	}
	public String getBoltError() {
		return boltError;
	}
	public void setBoltError(String boltError) {
		this.boltError = boltError;
	}
	public String getTopologyInnerError() {
		return topologyInnerError;
	}
	public void setTopologyInnerError(String topologyInnerError) {
		this.topologyInnerError = topologyInnerError;
	}
	@Override
	public String toString() {
		return "MonitorResult ["+"\n"+"topologyError=" + topologyError +"\n"+"boltError=" + boltError  +"\n"+ "topologyInnerError=" + topologyInnerError +"\n"+ "]";
	}
	

}
