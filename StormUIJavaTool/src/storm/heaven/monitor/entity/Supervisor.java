package storm.heaven.monitor.entity;


public class Supervisor {
	private String id;
	private String host;
	private Integer slotsTotal;
	private Integer slotsUsed;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	

}
