package com.seu.wsn.Core.Pojo;

public class UwData {

	// field
	private String sourceId;		// 源节点id
	private String targetId;		// 目标节点id
	private String nodeType;		// 节点类型
	private String dataType;		// 数据类型
	private String sendOrReceive;	// 发送或者接收
	private String time;			// 接收数据时间
	private String note;			// 备注
	private double[] point;
	
	// method
	public String getSourceId() {
		return sourceId;
	}
	
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
	public String getTargetId() {
		return targetId;
	}
	
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	public String getNodeType() {
		return nodeType;
	}
	
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getSendOrReceive() {
		return sendOrReceive;
	}
	
	public void setSendOrReceive(String sendOrReceive) {
		this.sendOrReceive = sendOrReceive;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public double[] getPoint() {
		return point;
	}
	
	public void setPoint(double[] point) {
		this.point = point;
	}
	
}
