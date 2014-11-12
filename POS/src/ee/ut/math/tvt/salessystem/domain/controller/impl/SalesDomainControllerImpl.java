package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;
import ee.ut.math.tvt.salesystem.service.*;


/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private final Session session = HibernateUtil.currentSession();
	
	private static final Logger log = Logger.getLogger(SalesDomainControllerImpl.class);
	
	HibernateDataService service=new HibernateDataService();
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {

		saveEntities(goods);
	}
	
	//public List<Order> loadHistory() {
		//List<Order> orders = session.createQuery("from Order").list();

	//	for (Order order : orders) {
		//	order.setSoldItems(session.createQuery("from SoldItem where order_id = " + order.getId()).list());
	//	}
	//	return orders;
	//}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	public void endSession() {
	    HibernateUtil.closeSession();
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		//List<StockItem> dataset = new ArrayList<StockItem>();

		List<StockItem> dataset = service.getStockitem();
		//return session.createQuery("from StockItem").list();
		
		return dataset;
	}
	
	public void addNewStockItem(StockItem good) throws VerificationFailedException {
		List<StockItem> goods = new ArrayList<StockItem>();
		goods.add(good);
		saveEntities(goods);
	}
	
	public void modifyStockItem(StockItem good) throws VerificationFailedException {
		List<StockItem> goods = new ArrayList<StockItem>();
		goods.add(good);
		updateEntities(goods);
	}


	public void modifyStockItems(List<StockItem> goods) throws VerificationFailedException {
		updateEntities(goods);
	}

	
	private void saveEntities(List<? extends DisplayableItem> items) throws VerificationFailedException {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			for (DisplayableItem item : items) {
				session.persist(item);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
			throw new VerificationFailedException(e);
		}
	}
	
	private void updateEntities(List<? extends DisplayableItem> items) throws VerificationFailedException {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			for (DisplayableItem item : items) {
				session.merge(item);
			}
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.error(e);
			throw new VerificationFailedException(e);
		}
	}





	@Override
	public void addNewOrder(Order order) throws VerificationFailedException {
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		saveEntities(orders);
		
	}
}
