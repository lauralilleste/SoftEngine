package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ORDER")
public class Order implements DisplayableItem {
	
	private static long ID = 1;
	
	@Id
	@Column(name="id")
	private final Long id;
	
	@OneToMany(mappedBy="order")
	private List<SoldItem> soldItem;
	
	@Column(name="date")
	private final String date;
	@Column(name="time")
	private final String time;
	
	public Order(List<SoldItem> soldItem, String date, String time){
		this.soldItem=soldItem;
		this.date=date;
		this.time=time;
		this.id=ID;
		ID += 1;
	}
	
	public void Order() {
		ID+=1;
	}

	public Long getId() {
		return id;
	}

	public List<SoldItem> getSoldItem() {
		return soldItem;
	}
	
	public void setSoldItems(List<SoldItem> soldItems) {
		this.soldItem = soldItems;
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
	
	public void addSoldItem(SoldItem item) {
		soldItem.add(item);
	}
	
	public String getTotalSumInString() {
		return getTotalSum().toString();
	}


	

}
