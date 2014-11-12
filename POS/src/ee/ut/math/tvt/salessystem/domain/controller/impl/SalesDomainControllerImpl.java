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
	
	public void endSession() {
		HibernateUtil.closeSession();
	}

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// XXX - Submit current purchase

	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {

		@SuppressWarnings("unchecked")
		List<StockItem> dataset = HibernateUtil.currentSession()
				.createQuery("from StockItem").list();

		return dataset;
	}

	public List<SoldItem> loadSaleHistoryState() {

		@SuppressWarnings("unchecked")
		List<SoldItem> dataset = HibernateUtil.currentSession()
				.createQuery("from SoldHistoryItem").list();
		return dataset;
	}

	@Override
	public void addNewStockItem(StockItem good)
			throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyStockItem(StockItem good)
			throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyStockItems(List<StockItem> goods)
			throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNewOrder(Order order) throws VerificationFailedException {
		// TODO Auto-generated method stub
		
	}
}
