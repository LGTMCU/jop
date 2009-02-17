/**
 * 
 */
package com.jopdesign.wcet08.analysis;

import java.io.Serializable;

public class WcetCost implements Serializable {
	private static final long serialVersionUID = 1L;
	private long localCost = 0;
	private long cacheCost = 0;
	private long nonLocalCost = 0;
	public WcetCost() {
		
	}
	public WcetCost(long wcet) {
		localCost = wcet;
	}

	public long getCost() { return localCost+cacheCost+nonLocalCost; }
	
	public long getLocalCost() { return localCost; }
	public long getCacheCost() { return cacheCost; }
	public long getNonLocalCost() { return nonLocalCost; }
	
	public void addLocalCost(long c) { this.localCost += c; }
	public void addNonLocalCost(long c) { this.nonLocalCost += c; }
	public void addCacheCost(long c) { this.cacheCost += c; }
	
	@Override public String toString() {
		if(getCost() == 0) return "0";
		if(localCost == 0) return "(cost: "+getCost()+", execution: "+nonLocalCost+", cache: "+cacheCost+")";
		return ""+getCost()+" (local: "+localCost+",cache: "+cacheCost+",non-local: "+nonLocalCost+")";
	}
	public WcetCost getFlowCost(Long flow) {
		WcetCost flowcost = new WcetCost();
		if(this.getCost() == 0 || flow == 0) return flowcost;
		flowcost.localCost = localCost*flow;
		flowcost.cacheCost = cacheCost*flow;
		flowcost.nonLocalCost = nonLocalCost*flow;
		if(flowcost.getCost() / flow != getCost()) {
			throw new ArithmeticException("getFlowCost: Arithmetic Error");
		}
		return flowcost;
	}
	public void moveLocalToGlobalCost() {
		this.nonLocalCost += this.localCost;
		this.localCost = 0;
	}
	public long getNonCacheCost() {
		return this.localCost + this.nonLocalCost;
	}
}