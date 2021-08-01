package com.lti.models;

public class BidList {
	private int itemId;
	private int buyerId;
	private double offerPrice;
	private double paymentTotal;
	private String itemStatus;
	
	
	public BidList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BidList(int itemId, int buyerId, double offerPrice, double paymentTotal, String itemStatus) {
		super();
		this.itemId = itemId;
		this.buyerId = buyerId;
		this.offerPrice = offerPrice;
		this.paymentTotal = paymentTotal;
		this.itemStatus = itemStatus;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public double getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}
	public double getPaymentTotal() {
		return paymentTotal;
	}
	public void setPaymentTotal(double paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	@Override
	public String toString() {
		return "[itemId=" + itemId + ", buyerId=" + buyerId + ", offerPrice=" + offerPrice + ", paymentTotal="
				+ paymentTotal + ", itemStatus=" + itemStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buyerId;
		result = prime * result + itemId;
		result = prime * result + ((itemStatus == null) ? 0 : itemStatus.hashCode());
		long temp;
		temp = Double.doubleToLongBits(offerPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paymentTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BidList other = (BidList) obj;
		if (buyerId != other.buyerId)
			return false;
		if (itemId != other.itemId)
			return false;
		if (itemStatus == null) {
			if (other.itemStatus != null)
				return false;
		} else if (!itemStatus.equals(other.itemStatus))
			return false;
		if (Double.doubleToLongBits(offerPrice) != Double.doubleToLongBits(other.offerPrice))
			return false;
		if (Double.doubleToLongBits(paymentTotal) != Double.doubleToLongBits(other.paymentTotal))
			return false;
		return true;
	}
	
	
	
}
