package principal;

import builder.UIOrderDirector;
import builder.UIOrderBuilder;
import builder.OverseasOrderBuilder;
import builder.NonCalOrderBuilder;
import builder.ColombianOrderBuilder;
import builder.CalOrderBuilder;
import orders.Order.*;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.util.*;
import java.io.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.java.swing.plaf.windows.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import orders.*;

public class OrderManager extends JFrame {
  public static final String newline = "\n";
  public static final String GET_TOTAL = "Get Total";
  public static final String CREATE_ORDER = "Create Order";
  public static final String EXIT = "Exit";
  public static final String CA_ORDER = "California Order";
  public static final String CO_ORDER = "Colombia Order";
  public static final String NON_CA_ORDER = 
    "Non-California Order";

  public static final String OVERSEAS_ORDER = "Overseas Order";

  public static final String EDIT_ORDER = "Edit order";
  
  private JComboBox cmbOrderType,cmbOrderTypeHistory;
  private JPanel pOrderCriteria,pOrderCriteriaHistory,pOrderContainer;
  private JLabel lblOrderTypeCreate, lblOrderTypeEdit;
  private JLabel lblTotal, lblTotalValue;
  private JLabel lblTotalOrders;
  private JScrollPane scOrderHistory;

  private OrderVisitor objVisitor;
  
  //Order history
  private HistoryCalOrder californiaOH;
  private HistoryColOrder colombianOH;
  private HistoryNonCalOrder noncaliforniaOH;
  private HistoryOverOrder overseasOH;

  public OrderManager() {
    super("Visitor Pattern - Example");

    //Create the visitor instance
    objVisitor = new OrderVisitor();
    //History order
    californiaOH = (HistoryCalOrder) HistoryCalOrder.getOrderHistory();
    colombianOH = (HistoryColOrder) HistoryColOrder.getOrderHistory();
    noncaliforniaOH = (HistoryNonCalOrder) HistoryNonCalOrder.getOrderHistory();
    overseasOH = (HistoryOverOrder) HistoryOverOrder.getOrderHistory();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    cmbOrderType = new JComboBox();
    cmbOrderType.addItem("");
    cmbOrderType.addItem(OrderManager.CA_ORDER);
    cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
    cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
    cmbOrderType.addItem(OrderManager.CO_ORDER);
    
    pOrderCriteria = new JPanel();

    lblOrderTypeCreate = new JLabel("Order Type:");

    lblTotal = new JLabel("Result:");
    lblTotalValue = new JLabel("Click Create or GetTotal Button");

    //Create the open button
    JButton getTotalButton = new JButton(OrderManager.GET_TOTAL);
    getTotalButton.setMnemonic(KeyEvent.VK_G);
    JButton createOrderButton = new JButton(OrderManager.CREATE_ORDER);
    createOrderButton.setMnemonic(KeyEvent.VK_C);
    JButton exitButton = new JButton(OrderManager.EXIT);
    exitButton.setMnemonic(KeyEvent.VK_X);
    ButtonHandler objButtonHandler = new ButtonHandler(this);
    
    //Action Listener 
    cmbOrderType.addActionListener(objButtonHandler);
    getTotalButton.addActionListener(objButtonHandler);
    createOrderButton.addActionListener(objButtonHandler);
    exitButton.addActionListener(new ButtonHandler());

    //For layout purposes, put the buttons in a separate panel
    

    JPanel panel = new JPanel();
    GridBagLayout gridbag2 = new GridBagLayout();
    panel.setLayout(gridbag2);
    GridBagConstraints gbc2 = new GridBagConstraints();
    panel.add(getTotalButton);
    panel.add(createOrderButton);
    panel.add(exitButton);
    gbc2.insets.top = 0;
    
    gbc2.anchor = GridBagConstraints.EAST;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gridbag2.setConstraints(createOrderButton, gbc2);
    gbc2.gridx = 1;
    gbc2.gridy = 0;
    gridbag2.setConstraints(getTotalButton, gbc2);
    gbc2.gridx = 2;
    gbc2.gridy = 0;
    gridbag2.setConstraints(exitButton, gbc2);

    //****************************************************
    JPanel buttonPanel = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    buttonPanel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();

    buttonPanel.add(lblOrderTypeCreate);
    buttonPanel.add(cmbOrderType);
    buttonPanel.add(pOrderCriteria);
    buttonPanel.add(lblTotal);
    buttonPanel.add(lblTotalValue);

    gbc.insets.top = 5;
    gbc.insets.bottom = 0;
    gbc.insets.left = 5;
    gbc.insets.right = 5;

    gbc.anchor = GridBagConstraints.WEST;
    
    gbc.gridx = 0;
    gbc.gridy = 0;
    gridbag.setConstraints(lblOrderTypeCreate, gbc);
    gbc.gridx = 1;
    gbc.gridy = 0;
    gridbag.setConstraints(cmbOrderType, gbc);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gridbag.setConstraints(pOrderCriteria, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gridbag.setConstraints(lblTotal, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 3;
    gridbag.setConstraints(lblTotalValue, gbc);
    
    gbc.insets.left = 2;
    gbc.insets.right = 2;
    gbc.insets.top = 2;

    //****************************************************
    //TAB create order
    JPanel createOrder = new JPanel();
    GridBagLayout gridbagCreateOrder = new GridBagLayout();
    createOrder.setLayout(gridbagCreateOrder);
    GridBagConstraints gbcCO = new GridBagConstraints();
    
    createOrder.add(buttonPanel);
    createOrder.add(panel);
    
    gbcCO.gridx = 0;
    gbcCO.gridy = 0;
    gridbagCreateOrder.setConstraints(buttonPanel, gbcCO);
    gbcCO.gridx = 0;
    gbcCO.gridy = 1;
    gridbagCreateOrder.setConstraints(panel, gbcCO);
    
   //***************************************************************
   //TAB Edit order
    JPanel editOrder = new JPanel();
    GridBagLayout gridbagEditOrder = new GridBagLayout();
    editOrder.setLayout(gridbagEditOrder);
    GridBagConstraints gbcEO = new GridBagConstraints();
    lblOrderTypeEdit = new JLabel("Order Type:");
    lblTotalOrders = new JLabel("Total: 0.0");
    cmbOrderTypeHistory = new JComboBox();
    cmbOrderTypeHistory.addItem("");
    cmbOrderTypeHistory.addItem(OrderManager.CA_ORDER);
    cmbOrderTypeHistory.addItem(OrderManager.NON_CA_ORDER);
    cmbOrderTypeHistory.addItem(OrderManager.OVERSEAS_ORDER);
    cmbOrderTypeHistory.addItem(OrderManager.CO_ORDER);
    cmbOrderTypeHistory.addActionListener(objButtonHandler);
    
    pOrderCriteriaHistory = new JPanel();
    //Este panel debe contener los JLabels que se deben crear al iterar sobre la colección
    pOrderContainer = new JPanel();
    
    pOrderContainer.add(new JLabel("Orders"));
    pOrderContainer.setLayout(new BoxLayout(pOrderContainer, BoxLayout.PAGE_AXIS));
    
    scOrderHistory = new JScrollPane(pOrderContainer);
    
    JButton editOrderButton = new JButton(OrderManager.EDIT_ORDER);
    editOrderButton.setMnemonic(KeyEvent.VK_E);
    editOrderButton.addActionListener(objButtonHandler);
    
    JButton exitButtonEdit = new JButton(OrderManager.EXIT);
    exitButtonEdit.setMnemonic(KeyEvent.VK_X);
    exitButtonEdit.addActionListener(new ButtonHandler());
    editOrder.add(lblOrderTypeEdit);
    editOrder.add(cmbOrderTypeHistory);
    editOrder.add(scOrderHistory);
    editOrder.add(pOrderCriteriaHistory);
    editOrder.add(editOrderButton);
    editOrder.add(exitButtonEdit);
    editOrder.add(lblTotalOrders);
    
    gbcEO.insets.top = 5;
    gbcEO.insets.bottom = 0;
    gbcEO.insets.left = 5;
    gbcEO.insets.right = 5;
    
    gbcEO.gridx = 0;
    gbcEO.gridy = 0;
    gridbagEditOrder.setConstraints(lblOrderTypeEdit, gbcEO);
    gbcEO.gridx = 0;
    gbcEO.gridy = 1;
    gridbagEditOrder.setConstraints(cmbOrderTypeHistory, gbcEO);
    gbcEO.gridx = 0;
    gbcEO.gridy = 2;
    gridbagEditOrder.setConstraints(pOrderCriteriaHistory, gbcEO);
    gbcEO.gridx = 1;
    gbcEO.gridy = 1;
    gridbagEditOrder.setConstraints(scOrderHistory, gbcEO);
    gbcEO.gridx = 1;
    gbcEO.gridy = 2;
    gridbagEditOrder.setConstraints(lblTotalOrders, gbcEO);
    
    gbcEO.gridx = 0;
    gbcEO.gridy = 3;
    gridbagEditOrder.setConstraints(editOrderButton, gbcEO);
    
    gbcEO.gridx = 1;
    gbcEO.gridy = 3;
    gridbagEditOrder.setConstraints(exitButtonEdit, gbcEO);
    
    //****************************************************
    // Add tabs 
    tabbedPane.addTab("Create order", createOrder);
    tabbedPane.addTab("Edit Order", editOrder);
    //Add the buttons and the log to the frame
    Container contentPane = getContentPane();
    contentPane.add(tabbedPane);

    
    //contentPane.add(panel,BorderLayout.CENTER);
    try {
      UIManager.setLookAndFeel(new WindowsLookAndFeel());
      SwingUtilities.updateComponentTreeUI(
        OrderManager.this);
    } catch (Exception ex) {
      System.out.println(ex);
    }

  }
  
  public void displayNewUI(JPanel panel) {
    pOrderCriteria.removeAll();
    pOrderCriteria.add(panel);
    pOrderCriteria.validate();
    validate();
  }
  public void displayNewUIHistory(JPanel panel){
      pOrderCriteriaHistory.removeAll();
      pOrderCriteriaHistory.add(panel);
      pOrderCriteriaHistory.validate();
      validate();
  }
  public boolean saveOrder(Order order, int type){
      try{
          switch(type){
          case 1:
              californiaOH.addOrder(order);
              break;
          case 2:
              colombianOH.addOrder(order);
              break;
          case 3:
              overseasOH.addOrder(order);
              break;
          case 4:
              noncaliforniaOH.addOrder(order);
              break;
         }
          return true;
      }catch(Exception e){
          System.out.println(e);
          return false;
      }
  }
  public void listOrderHistory(String orderType, ButtonHandler objButtonHandler){
      if(orderType.length()>0){
        int i=0;
        Iterator orderIter = null;
        pOrderContainer.removeAll();
        JLabel lblOrder = new JLabel("Orders");
        pOrderContainer.add(lblOrder);

        if(orderType.equals(OrderManager.CA_ORDER)){
            orderIter = californiaOH.getAllTypeOrder();
            lblTotalOrders.setText("Total: "+ californiaOH.getTotal());
        } else if(orderType.equals(OrderManager.NON_CA_ORDER)){
            orderIter = noncaliforniaOH.getAllTypeOrder();  
            lblTotalOrders.setText("Total: "+ noncaliforniaOH.getTotal());
        } else if(orderType.equals(OrderManager.OVERSEAS_ORDER)){
            orderIter = overseasOH.getAllTypeOrder();  
            lblTotalOrders.setText("Total: "+ overseasOH.getTotal());
        } else if(orderType.equals(OrderManager.CO_ORDER)){
            orderIter = colombianOH.getAllTypeOrder(); 
            lblTotalOrders.setText("Total: "+ colombianOH.getTotal());
        }
        while (orderIter.hasNext()){
                i++;
                Order c = (Order) orderIter.next();
                //System.out.println(c.getTotal()+"hola");
                JButton order = new JButton(i+"-"+c.getTotal());

                order.addActionListener(objButtonHandler);
                pOrderContainer.add(order);
        }
      }
      validate();
  }

  public void updateCriteria(){
      pOrderCriteriaHistory.removeAll();
      pOrderCriteriaHistory.validate();
  }
  
  
  public static void main(String[] args) {
    JFrame frame = new OrderManager();

    frame.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        }
                           );

    //frame.pack();
    frame.setSize(500, 400);
    frame.setVisible(true);
  }

  public JComboBox getCmbOrderType() {
    return cmbOrderType;
  }
  public JComboBox getCmbOrderTypeHistory() {
        return cmbOrderTypeHistory;
    }
  
  public void setTotalValue(String msg) {
    lblTotalValue.setText(msg);
  }
  public OrderVisitor getOrderVisitor() {
    return objVisitor;
  }
  public String getOrderType() {
    return (String) cmbOrderType.getSelectedItem();
  }
  public String getOrderHistory(){
      return (String) cmbOrderTypeHistory.getSelectedItem();
  }
  
    public OrderComponent getHistory(String history){
        OrderComponent o = null;
        
        if(history.equals(OrderManager.CA_ORDER)){
            o = californiaOH;
        } else if (history.equals(OrderManager.NON_CA_ORDER)){
            o = noncaliforniaOH;
        } else if (history.equals(OrderManager.OVERSEAS_ORDER)){
            o = overseasOH;
        } else if (history.equals(OrderManager.CO_ORDER)){
            o = colombianOH;
        }
        
       return o;
    }
  
} // End of class OrderManager

class ButtonHandler implements ActionListener {
  OrderManager objOrderManager;
  UIOrderBuilder builderCreate, builderEdit;
  String[] posOrder;
  int orderT = 0;
  
  public void actionPerformed(ActionEvent e) {
    String totalResult = null;
    String orderType = "";
    
    if (e.getActionCommand().equals(OrderManager.EXIT)) {
      System.exit(1);
    }  
    if(e.getSource() == objOrderManager.getCmbOrderType()){
        orderType = objOrderManager.getOrderType();
        if (orderType.equals("") == false) {
            assignBuilder(orderType, e);
        }
    }
    if(e.getSource() == objOrderManager.getCmbOrderTypeHistory()){
            orderType = objOrderManager.getOrderHistory();
            objOrderManager.updateCriteria();
            objOrderManager.listOrderHistory(orderType,this);
        }
    
    
    if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)) {
      //get input values
      orderType = objOrderManager.getOrderType();
      String[] orderData = builderCreate.getOrder();

      
      double dblOrderAmount = 0.0;
      double dblTax = 0.0;
      double dblSH = 0.0;
      
      double[] dateCreate = getOrder(builderCreate);
      //Create the order
      Order order = createOrder(orderType, dateCreate[0],
                    dateCreate[1], dateCreate[2]);
      
      boolean ifsave = objOrderManager.saveOrder(order, orderT);
      
      if(ifsave){
          //Get the Visitor
        OrderVisitor visitor =
          objOrderManager.getOrderVisitor();

        // accept the visitor instance
        order.accept(visitor);

        objOrderManager.setTotalValue(
          " Order Created Successfully");
      }else{
          objOrderManager.setTotalValue(
          "Error");
      }
    }else if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
      //Get the Visitor
      OrderVisitor visitor =
        objOrderManager.getOrderVisitor();
      totalResult = new Double(
                      visitor.getOrderTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setTotalValue(totalResult);
      
      
      /*
      Iterator orderIter = objOrderManager.getCaliforniaOH().getAllTypeOrder();
        while (orderIter.hasNext()){
            Order c = (Order) orderIter.next();
            System.out.println(c.getTotal());
        }
        */
    }else if (e.getActionCommand().equals(OrderManager.EDIT_ORDER)) {
        OrderComponent orderHistory = objOrderManager.getHistory(objOrderManager.getOrderHistory());
        String[] orderData = builderEdit.getOrder();
        orderType = objOrderManager.getOrderHistory();

        double dblOrderAmount = 0.0;
        double dblTax = 0.0;
        double dblSH = 0.0;

        double[] date = getOrder(builderEdit);

        //Create the order
        Order order = createOrder(orderType, date[0],
                      date[1], date[2]);
        try {
            orderHistory.editOrder(order,Integer.parseInt(posOrder[0])-1);
        } catch (Exception ex) {
            Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        objOrderManager.listOrderHistory(orderType,this);
    }else{
            //Toma el numero (1,2,3,...) que acompaña al valor total y con eso usando el metodo getElement()
            //lo obtine de la coleccion y usa el inicial para meter los valores en el builder que se crea
            if(e.getSource() instanceof JButton){
                String orderPosition = e.getActionCommand();
                String order = objOrderManager.getOrderHistory();
                if (order.equals("") == false) {
                BuilderFactory factory = new BuilderFactory();
                //create an appropriate builder instance
                builderEdit = factory.getUIBuilder(order);
                //configure the director with the builder
                UIOrderDirector director = new UIOrderDirector(builderEdit);
                //director invokes different builder
                // methods
                director.build();
                //get the final build object
                
                JPanel UIObj = builderEdit.getSearchUI();
                
                
                posOrder = e.getActionCommand().split("-");
              
                OrderComponent aux = objOrderManager.getHistory(objOrderManager.getOrderHistory());
                
                Order c = (Order) aux.orderObjList.get(Integer.parseInt(posOrder[0])-1);
                
                
                if (objOrderManager.getOrderHistory().equals(objOrderManager.CA_ORDER)){
                    CaliforniaOrder co = (CaliforniaOrder) c;
                    builderEdit.inicialice(co.getOrderAmount() , co.getAdditionalTax(), 0);
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.NON_CA_ORDER)){
                    NonCaliforniaOrder nco = (NonCaliforniaOrder) c;
                    builderEdit.inicialice(nco.getOrderAmount() , 0, 0);
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.OVERSEAS_ORDER)){
                    OverseasOrder oo = (OverseasOrder) c;
                    builderEdit.inicialice(oo.getOrderAmount() , 0, oo.getAdditionalSH());
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.CO_ORDER)){
                    ColombiaOrder col = (ColombiaOrder) c;
                    builderEdit.inicialice(col.getOrderAmount() , 0, col.getAdditionalSH());
                }
                
                objOrderManager.displayNewUIHistory(UIObj);
                
            }
        }
    }
  }

  public Order createOrder(String orderT,double orderAmount, double tax, double SH) {
    if (orderT.equalsIgnoreCase(OrderManager.CA_ORDER)) {
      return new CaliforniaOrder(orderAmount, tax);
    }
    if (orderT.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
      return new NonCaliforniaOrder(orderAmount);
    }
    if (orderT.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
      return new OverseasOrder(orderAmount, SH);
    }
    if(orderT.equalsIgnoreCase(OrderManager.CO_ORDER)){
        return new ColombiaOrder(orderAmount, SH);
    }
    return null;
  }
  
  public ButtonHandler() {
  }
  public ButtonHandler(OrderManager inObjOrderManager) {
    objOrderManager = inObjOrderManager;
  }
  
  public void assignBuilder(String order,ActionEvent e){
    BuilderFactory factory = new BuilderFactory();
    //create an appropriate builder instance
    builderCreate = factory.getUIBuilder(order);
    //configure the director with the builder
    UIOrderDirector director = new UIOrderDirector(builderCreate);
    //director invokes different builder
    // methods
    director.build();
    //get the final build object
    JPanel UIObj = builderCreate.getSearchUI();
    objOrderManager.displayNewUI(UIObj);
  }
public double[] getOrder(UIOrderBuilder ob){
      double dblOrderAmount = 0.0;
      double dblTax = 0.0;
      double dblSH = 0.0;
      
      String strOrderAmount = "0.0";
      String strTax = "0.0";
      String strSH = "0.0";
      String[] orderData = ob.getOrder();
      
      if(ob instanceof CalOrderBuilder){
          strOrderAmount = orderData[0];
          strTax = orderData[1];
          orderT = 1;
      }else if(ob instanceof ColombianOrderBuilder){
          strOrderAmount = orderData[0];
          strSH = orderData[1];
          orderT = 2;
      }else if(ob instanceof OverseasOrderBuilder){
          strOrderAmount = orderData[0];
          strSH = orderData[1];
          orderT = 3;
      }else if(ob instanceof NonCalOrderBuilder){
          strOrderAmount = orderData[0];
          orderT = 4;
      }

      dblOrderAmount =
        new Double(strOrderAmount).doubleValue();
      dblTax = new Double(strTax).doubleValue();
      dblSH = new Double(strSH).doubleValue();
      
        System.out.println("-----------------------");
        System.out.println(dblOrderAmount);
        System.out.println(dblTax);
        System.out.println(dblSH);
        System.out.println("-----------------------");
        
        double[] dates = new double[3];
        dates[0] = dblOrderAmount;
        dates[1] = dblTax;
        dates[2] = dblSH;
        return dates;
    
}

    public String[] getPosOrder() {
        return posOrder;
    }
  
} // End of class ButtonHandler

class BuilderFactory {
  public UIOrderBuilder getUIBuilder(String str) {
    UIOrderBuilder builder = null;
    if (str.equals(OrderManager.CA_ORDER)) {
      builder = new CalOrderBuilder();
    } else if (str.equals(OrderManager.NON_CA_ORDER)) {
      builder = new NonCalOrderBuilder();
    } else if (str.equals(OrderManager.OVERSEAS_ORDER)) {
      builder = new OverseasOrderBuilder();
    } else if (str.equals(OrderManager.CO_ORDER)) {
      builder = new ColombianOrderBuilder();
    }
    return builder;
  }
}