package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import java.text.DateFormat;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab {

  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  private final SalesDomainController domainController;

  private JButton newPurchase;

  private JButton submitPurchase;

  private JButton cancelPurchase;

  private JButton makeP;
  
  private JButton cancelP;
  
  private PurchaseItemPanel purchasePane;

  private SalesSystemModel model;
  
  private JPanel confPanel;
  
  private JFrame confFrame;


  public PurchaseTab(SalesDomainController controller,
      SalesSystemModel model)
  {
    this.domainController = controller;
    this.model = model;
  }


  /**
   * The purchase tab. Consists of the purchase menu, current purchase dialog and
   * shopping cart table.
   */
  public Component draw() {
    JPanel panel = new JPanel();

    // Layout
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridBagLayout());

    // Add the purchase menu
    panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

    // Add the main purchase-panel
    purchasePane = new PurchaseItemPanel(model);
    panel.add(purchasePane, getConstraintsForPurchasePanel());

    return panel;
  }




  // The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
  private Component getPurchaseMenuPane() {
    JPanel panel = new JPanel();

    // Initialize layout
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gc = getConstraintsForMenuButtons();

    // Initialize the buttons
    newPurchase = createNewPurchaseButton();
    submitPurchase = createConfirmButton();
    cancelPurchase = createCancelButton();

    // Add the buttons to the panel, using GridBagConstraints we defined above
    panel.add(newPurchase, gc);
    panel.add(submitPurchase, gc);
    panel.add(cancelPurchase, gc);

    return panel;
  }


  // Creates the button "New purchase"
  private JButton createNewPurchaseButton() {
    JButton b = new JButton("New purchase");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        newPurchaseButtonClicked();
      }
    });

    return b;
  }

  // Creates the "Confirm" button
  private JButton createConfirmButton() {
    JButton b = new JButton("Confirm");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        submitPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }
  
//Creates the "Make purchase" button
  private JButton createMakePButton(){
	  JButton b = new JButton("Make purchase"); 
	  b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        makePButtonClicked();
	      }
	    });
	    return b;
  }
  //Creates the "Cancel purchase" button
  private JButton createCancelPButton(){
	  JButton b = new JButton("Cancel");
	  b.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			  cancelPButtonClicked();
		  }
	  }); return b;
  }
  
  // Creates the "Cancel" button
  private JButton createCancelButton() {
    JButton b = new JButton("Cancel");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        cancelPurchaseButtonClicked();
      }
    });
    b.setEnabled(false);

    return b;
  }

private void ConfirmationBox(){
	showConfBox();
	confPanel = new JPanel(new MigLayout("nogrid"));
	confFrame = new JFrame("Confirm");
	confFrame.setSize(new Dimension(350,180));
	confFrame.setLocationRelativeTo(null);
	confFrame.setVisible(false);
	confFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	//asking payment amount
	String pm = (String) JOptionPane.showInputDialog(confFrame,"Sum: "
	+model.getCurrentPurchaseTableModel().getPurchaseSum(),"",
	JOptionPane.PLAIN_MESSAGE, null, null, "Payment amount");
	Double rm = Double.valueOf(new DecimalFormat("0.00").format(
			Double.parseDouble(pm)-Double.parseDouble(model.getCurrentPurchaseTableModel().getPurchaseSum())));
	if(rm<0){
		JOptionPane.showMessageDialog(null, "Entered amount is too small","Warning",JOptionPane.WARNING_MESSAGE);
		continuePurchase();
		return;
	}
	
	confPanel.add(new JLabel("Sum: "+ model.getCurrentPurchaseTableModel().getPurchaseSum()));
	confPanel.add(new JLabel("Payment amount: "+ pm),"newline");
	confPanel.add(new JLabel("Return: " + rm),"newline");
	makeP =  createMakePButton();
	cancelP = createCancelPButton();
	confPanel.add(makeP,"newline");
	confPanel.add(cancelP);
	confFrame.add(confPanel);
	confFrame.setVisible(true);
}



  /* === Event handlers for the menu buttons
   *     (get executed when the buttons are clicked)
   */


  /** Event handler for the <code>new purchase</code> event. */
  protected void newPurchaseButtonClicked() {
    log.info("New sale process started");
    try {
      domainController.startNewPurchase();
      startNewSale();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /**  Event handler for the <code>cancel purchase</code> event. */
  protected void cancelPurchaseButtonClicked() {
    log.info("Sale cancelled");
    try {
      domainController.cancelCurrentPurchase();
      endSale();
      model.getCurrentPurchaseTableModel().clear();
    } catch (VerificationFailedException e1) {
      log.error(e1.getMessage());
    }
  }


  /** Event handler for the <code>submit purchase</code> event. */
  protected void submitPurchaseButtonClicked() {
    log.info("Sale complete");
    try {
      ConfirmationBox();
      
    } catch (Exception e1) {
      JOptionPane.showMessageDialog(null,"Incorrect input","Warning",JOptionPane.WARNING_MESSAGE);
      continuePurchase();
    }
  }
  
  protected void makePButtonClicked(){
	  try{
		  
		  log.debug("Current basket: "+model.getCurrentPurchaseTableModel());
		  domainController.submitCurrentPurchase(model.getCurrentPurchaseTableModel().getTableRows());
		  endSale();
		  savePurchase();
		  log.info("Sale completed");
		  model.getCurrentPurchaseTableModel().clear();
		  
	  } catch(VerificationFailedException e){
		  log.error(e.getMessage());
	  }
  }
  protected void cancelPButtonClicked(){
	  log.info("Return");
	  continuePurchase();
  }
  
  
  protected void savePurchase(){
	  Order order = new Order(model.getCurrentPurchaseTableModel().getTableRows(),
			  ((DateFormat)new SimpleDateFormat("yyyy/MM/dd")).format(Calendar.getInstance().getTime()),
			  ((DateFormat)new SimpleDateFormat("HH:mm:ss")).format(Calendar.getInstance().getTime()));
	  model.getHistoryTableModel().AddOrder(order);
	  model.getWarehouseTableModel().ItemsQuantity(order.getSoldItem());
  }


  /* === Helper methods that bring the whole purchase-tab to a certain state
   *     when called.
   */

  // switch UI to the state that allows to proceed with the purchase
  private void startNewSale() {
    purchasePane.reset();
    purchasePane.setEnabled(true);
    submitPurchase.setEnabled(true);
    cancelPurchase.setEnabled(true);
    newPurchase.setEnabled(false);
  }

  // switch UI to the state that allows to initiate new purchase
  private void endSale() {
	  
    purchasePane.reset();
    confFrame.dispose();
    cancelPurchase.setEnabled(false);
    submitPurchase.setEnabled(false);
    newPurchase.setEnabled(true);
    purchasePane.setEnabled(false);
  }
  
  //switch UI to the state that allows to continue 
  private void continuePurchase(){
	  confFrame.dispose();
	  purchasePane.setEnabled(true);
	  submitPurchase.setEnabled(true);
	  cancelPurchase.setEnabled(true);
	  newPurchase.setEnabled(false);
  }
  //switch UI to ths state that allows to show confirmation box
  private void showConfBox(){
	  purchasePane.setEnabled(false);
	  submitPurchase.setEnabled(false);
	  cancelPurchase.setEnabled(false);
	  newPurchase.setEnabled(false);
  }



  /* === Next methods just create the layout constraints objects that control the
   *     the layout of different elements in the purchase tab. These definitions are
   *     brought out here to separate contents from layout, and keep the methods
   *     that actually create the components shorter and cleaner.
   */

  private GridBagConstraints getConstraintsForPurchaseMenu() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    return gc;
  }


  private GridBagConstraints getConstraintsForPurchasePanel() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.fill = GridBagConstraints.BOTH;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 1.0;

    return gc;
  }


  // The constraints that control the layout of the buttons in the purchase menu
  private GridBagConstraints getConstraintsForMenuButtons() {
    GridBagConstraints gc = new GridBagConstraints();

    gc.weightx = 0;
    gc.anchor = GridBagConstraints.CENTER;
    gc.gridwidth = GridBagConstraints.RELATIVE;

    return gc;
  }

}
