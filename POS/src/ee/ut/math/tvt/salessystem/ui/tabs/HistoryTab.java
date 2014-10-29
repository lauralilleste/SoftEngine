package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.DetailedHistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
    private static final Logger log = Logger.getLogger(PurchaseTab.class);
    private final SalesSystemModel model;
    

    public HistoryTab(SalesSystemModel model) {
    	this.model=model;
    } 
    
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
        gc.weighty = 1.0d;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);
        return panel;
    }
    
    // history table
    private JPanel drawHistoryMainPane(){
		 final JPanel panel = new JPanel();
		 final JTable table = new JTable(model.getHistoryTableModel());
		 table.addMouseListener(new MouseAdapter (){
			 public void mouseReleased(MouseEvent e){
				 int rowNr = table.getSelectedRow();
				 Order order = model.getHistoryTableModel().getOrder(rowNr);
				 JFrame info = new JFrame();
				 info.setTitle("Info " + order.getId());
				 info.setVisible(true);
			
				 int width = 600;
				 int height=400;
				 info.setSize(width, height);
				 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				 info.setLocation((screen.width-width) / 2+10,(screen.height-height)/2 +50);
				 log.debug("Opened info about the sale number" + order.getId());
				 info.add(drawDetailedHistoryPane(order.getSoldItem()));
			 }
		 });
		 
		 JScrollPane scrollPane = new JScrollPane();
		 JTableHeader header = table.getTableHeader(); 
		 header.setReorderingAllowed(false);
		 GridBagConstraints gc = new GridBagConstraints();
    	 GridBagLayout gb = new GridBagLayout();
    	 gc.fill = GridBagConstraints.BOTH;
    	 gc.weightx = 1.0;
    	 gc.weighty = 1.0;
    	 panel.setLayout(gb);
 
    	 panel.add(scrollPane, gc);

    	 panel.setBorder(BorderFactory.createTitledBorder("Purchase History"));
       return panel;
    }
    private Component drawDetailedHistoryPane(List<SoldItem> items){
    	 final JPanel panel = new JPanel();
    	 DetailedHistoryTableModel dhtm=model.getDetailedHistoryTableModel();
    	 dhtm.addItems(items);
    	 final JTable table = new JTable(dhtm);
    	 JTableHeader header = table.getTableHeader(); 
    	 header.setReorderingAllowed(false);
    	 JScrollPane scrollPane = new JScrollPane();
    	 GridBagConstraints gc = new GridBagConstraints();
    	 GridBagLayout gb = new GridBagLayout();
    	 gc.fill = GridBagConstraints.BOTH;
    	 gc.weightx = 1.0;
    	 gc.weighty = 1.0;
    	 panel.setLayout(gb);
    	 panel.add(scrollPane, gc);
    	 return panel;
    }
    
}