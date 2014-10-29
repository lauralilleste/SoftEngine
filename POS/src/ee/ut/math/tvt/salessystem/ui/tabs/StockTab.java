package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;


public class StockTab {

  private JButton addItem;
  private JButton confirm;
  private JButton cancel;
  private JFrame addItemFrame;
  private JPanel addItemPanel;
  private SalesSystemModel model;
  private JTextField IdField;
  private JTextField NameField;
  private JTextField PriceField;
  private JTextField QuantityField;
  
  private static final Logger log = Logger.getLogger(PurchaseTab.class);

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    addItem = createAddItemButton();
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);
    
  

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }

  
  
  // Create "Add" button, to add new items
  private JButton createAddItemButton() {
	  JButton b = new JButton("Add");
	  b.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent ae){
			  addItemButtonClicked();
		  }
	  });
	  return b;
  }
  // Create "Confirm" button, to confirm adding items
  	private JButton createConfirmButton(){
  		JButton b = new JButton("Confirm");
  		b.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent ae){
  				confirmButtonClicked();
  			}
  			
  		});
  		
  		return b;
  }
  
    // Creates the "Cancel" button
    private JButton createCancelButton() {
      JButton b = new JButton("Cancel");
      b.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	cancelPurchaseButtonClicked();
       
        }
      });

      return b;
    }
 
  
  // Event handler for the "add" button
  protected void addItemButtonClicked(){
	  try{
		  addItemBox();
	  }
	  catch(Exception e){
		  JOptionPane.showMessageDialog(null, "Incorrect input","Warning",JOptionPane.WARNING_MESSAGE);
		  addItemFrame.dispose();
	  }
  }
  
  Long id= (long) 1;
  String name = null;
  double price =0.0;
  String desc =null;
  int quantity = 0;
  
  // Event handler for the "confirm" button
  protected void confirmButtonClicked(){
	  try{
		  id = Long.parseLong(IdField.getText());
		  name = NameField.getText();
		  price = Double.parseDouble(PriceField.getText());
		  quantity = Integer.parseInt(QuantityField.getText());
		  model.getWarehouseTableModel().addItem(new StockItem(id, name, desc, price, quantity));
		  
	  }
	  catch(Exception e){
		  JOptionPane.showMessageDialog(null, "Incorrect input","Warning",JOptionPane.WARNING_MESSAGE);
		  addItemFrame.dispose();
	  }
  }
  protected void cancelPurchaseButtonClicked() {
	    try {
	    	addItemFrame.dispose();
	    	
	    } catch (Exception e1) {
	      log.error(e1.getMessage());
	    }
	  }

  //Add new items
  private void addItemBox(){
	  addItemPanel = new JPanel(new MigLayout("nogrid"));
	  
	  addItemFrame = new JFrame("Add item");
	  addItemFrame.setSize(new Dimension(350,180));
	  addItemFrame.setLocationRelativeTo(null);
	  addItemFrame.setVisible(false);
	  addItemFrame.add(addItemPanel);
	  addItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  
	  IdField=new JTextField(6);
	  NameField=new JTextField(6);
	  PriceField=new JTextField(6);
	  QuantityField=new JTextField(6);
	  

	  
	  // item id
	  addItemPanel.add(new JLabel("Id: "),"newline");
	  addItemPanel.add(IdField);
	  // item name
	  addItemPanel.add(new JLabel("Name: "),"newline");
	  addItemPanel.add(NameField);
	  // item price
	  addItemPanel.add(new JLabel("Price: "),"newline");
	  addItemPanel.add(PriceField);
	  //item amount
	  addItemPanel.add(new JLabel("Quantity: "),"newline");
	  addItemPanel.add(QuantityField);
	  
	  confirm = createConfirmButton();
	  cancel = createCancelButton();
	  addItemPanel.add(confirm,"newline");
	  addItemPanel.add(cancel);
	  
	  addItemFrame.setVisible(true);
	  
	  
  }
  // table of the warehouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }
}


