package ee.ut.math.tvt.salesystem.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.ui.model.*;
import ee.ut.math.tvt.salessystem.util.*;
import ee.ut.math.tvt.salessystem.domain.data.*;

@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStockitem() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}

	public List<SoldItem> getSolditem() {
		List<SoldItem> result = session.createQuery("from SoldItem").list();
		return result;
	}

}