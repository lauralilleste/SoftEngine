package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class Order implements DisplayableItem {
	
	private static long ID = 1;
	private final Long id;
	private final List<SoldItem> soldItem;
	private final String date;
	private final String time;
	
	public Order(List<SoldItem> soldItem, String date, String time){
		this.soldItem=soldItem;
		this.date=date;
		this.time=time;
		this.id=ID;
		ID += 1;
	}

	public Long getId() {
		return id;
	}

	public List<SoldItem> getSoldItem() {
		return soldItem;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}
	
	public String getTotalSum(){
		Double purchaseSum = 0.0;
		for(final SoldItem item: soldItem){
			purchaseSum += item.getSum();
		} return purchaseSum.toString();
	}
	

}
