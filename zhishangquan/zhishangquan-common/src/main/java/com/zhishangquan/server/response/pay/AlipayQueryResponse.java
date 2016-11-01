package com.zhishangquan.server.response.pay;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {})  
public class AlipayQueryResponse {

	private  AlipayQueryResponseTrade trade;

	public AlipayQueryResponseTrade getTrade() {
		return trade;
	}

	public void setTrade(AlipayQueryResponseTrade trade) {
		this.trade = trade;
	}
	
	
}
