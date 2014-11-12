package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salesystem.service.HibernateDataService;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {
	
	HibernateDataService service=new HibernateDataService();
    /**
     * Load the current state of the warehouse.
     * 
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> loadWarehouseState();
    
    public List<Order> loadHistory();

    // business processes
    /**
     * Initiate new business transaction - purchase of the goods.
     * 
     * @throws VerificationFailedException
     */
    public void startNewPurchase() throws VerificationFailedException;

    /**
     * Rollback business transaction - purchase of goods.
     * 
     * @throws VerificationFailedException
     */
    public void cancelCurrentPurchase() throws VerificationFailedException;

    /**
     * Commit business transaction - purchsae of goods.
     * 
     * @param goods
     *            Goods that the buyer has chosen to buy.
     * @throws VerificationFailedException
     */
    public void submitCurrentPurchase(List<SoldItem> goods)
            throws VerificationFailedException;

	public void endSession();
	
	public void addNewStockItem(StockItem good) throws VerificationFailedException;

	public void modifyStockItem(StockItem good) throws VerificationFailedException;

	public void modifyStockItems(List<StockItem> goods) throws VerificationFailedException;

	public void addNewOrder(Order order) throws VerificationFailedException;

    
}
