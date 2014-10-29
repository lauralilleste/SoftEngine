package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.Order;

public class HistoryTableModel extends SalesSystemTableModel<Order> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	private final List<Order> orders= new ArrayList<>();
	
	public HistoryTableModel(){
		super(new String[] {"Id","Date","Time","Total Price"});
	}


	@Override
	protected Object getColumnValue(Order item, int columnIndex) {
		switch(columnIndex){
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getTotalSum();
		}
		throw new IllegalArgumentException("Column index out of range");
		
	}


	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		for(int i = 0; i < headers.length; i++){
			sb.append(headers[i]+"\t");
		} sb.append("\n");
		for(final Order order: rows){
			sb.append(order.getId()+"\t");
			sb.append(order.getDate()+"\t");
			sb.append(order.getTime()+"\t");
			sb.append(order.getTotalSum()+"\t");
			sb.append("\n");
		}
		return sb.toString();
	}
	public void AddOrder(final Order order){
		orders.add(order);
		rows.add(order);
		log.debug("Added new item " + order.getId()+ " with total sum of " + order.getTotalSum());
		fireTableDataChanged();
		
	}
	
	public Order getOrder(int rowNr){
		return orders.get(rowNr);
	}
	
	

}
