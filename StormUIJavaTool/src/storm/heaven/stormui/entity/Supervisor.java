package storm.heaven.stormui.entity;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司 Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights
 * Reserved. BidPlanStructForm.java Created on 2015-3-23 Author: <a
 * href=mailto:wanghd@bonree.com>王厚达</a>
 * 
 * @Title: Topology.java
 * @Package bonree.utils Description: Version: 1.0
 ******************************************************************************/
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
