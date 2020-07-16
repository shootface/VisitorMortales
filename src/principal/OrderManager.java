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
  private JLabel lblOrderType;
  private JLabel lblTotal, lblTotalValue;
  private JLabel lblTotalOrders;
  private JScrollPane scOrderHistory;

  private OrderVisitor objVisitor;
  
  //Order history
  private OrderComponent californiaOH;
  private OrderComponent colombianOH;
  private OrderComponent noncaliforniaOH;
  private OrderComponent overseasOH;

  public OrderManager() {
    super("Visitor Pattern - Example");

    //Create the visitor instance
    objVisitor = new OrderVisitor();
    //History order
    californiaOH = HistoryCalOrder.getOrderHistory();
    colombianOH = HistoryColOrder.getOrderHistory();
    noncaliforniaOH = HistoryNonCalOrder.getOrderHistory();
    overseasOH = HistoryOverOrder.getOrderHistory();
    
    JTabbedPane tabbedPane = new JTabbedPane();
    cmbOrderType = new JComboBox();
    cmbOrderType.addItem("");
    cmbOrderType.addItem(OrderManager.CA_ORDER);
    cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
    cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
    cmbOrderType.addItem(OrderManager.CO_ORDER);
    
    pOrderCriteria = new JPanel();

    lblOrderType = new JLabel("Order Type:");

    lblTotal = new JLabel("Result:");
    lblTotalValue = new JLabel("Click Create or GetTotal Button");

    //Create the open button
    JButton getTotalButton = new JButton(OrderManager.GET_TOTAL);
    getTotalButton.setMnemonic(KeyEvent.VK_G);
    JButton createOrderButton = new JButton(OrderManager.CREATE_ORDER);
    getTotalButton.setMnemonic(KeyEvent.VK_C);
    JButton exitButton = new JButton(OrderManager.EXIT);
    exitButton.setMnemonic(KeyEvent.VK_X);
    ButtonHandler objButtonHandler = new ButtonHandler(this);
    
    //Action Listener 
    cmbOrderType.addActionListener(objButtonHandler);
    getTotalButton.addActionListener(objButtonHandler);
    createOrderButton.addActionListener(objButtonHandler);
    exitButton.addActionListener(new ButtonHandler());

    //For layout purposes, put the buttons in a separate panel
    JPanel buttonPanel = new JPanel();

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
    GridBagLayout gridbag = new GridBagLayout();
    buttonPanel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();

    buttonPanel.add(lblOrderType);
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
    gridbag.setConstraints(lblOrderType, gbc);
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
    
    editOrder.add(cmbOrderTypeHistory);
    editOrder.add(scOrderHistory);
    editOrder.add(pOrderCriteriaHistory);
    editOrder.add(editOrderButton);
    editOrder.add(exitButton);
    editOrder.add(lblTotalOrders);
    
    gbcEO.insets.top = 5;
    gbcEO.insets.bottom = 0;
    gbcEO.insets.left = 5;
    gbcEO.insets.right = 5;
    
    gbcEO.gridx = 0;
    gbcEO.gridy = 0;
    gridbagEditOrder.setConstraints(cmbOrderTypeHistory, gbcEO);
    gbcEO.gridx = 0;
    gbcEO.gridy = 1;
    gridbagEditOrder.setConstraints(pOrderCriteriaHistory, gbcEO);
    gbcEO.gridx = 1;
    gbcEO.gridy = 0;
    gridbagEditOrder.setConstraints(scOrderHistory, gbcEO);
    gbcEO.gridx = 1;
    gbcEO.gridy = 1;
    gridbagEditOrder.setConstraints(lblTotalOrders, gbcEO);
    
    gbcEO.gridx = 0;
    gbcEO.gridy = 2;
    gridbagEditOrder.setConstraints(editOrderButton, gbcEO);
    
    gbcEO.gridx = 1;
    gbcEO.gridy = 2;
    gridbagEditOrder.setConstraints(exitButton, gbcEO);
    
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
                System.out.println(c.getTotal()+"hola");
                JButton order = new JButton(i+"-"+c.getTotal());

                order.addActionListener(objButtonHandler);
                pOrderContainer.add(order);
        }
      }
      validate();
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
  UIOrderBuilder builder;
  String[] posOrder;
  
  public void actionPerformed(ActionEvent e) {
    String totalResult = null;
    
    if (e.getActionCommand().equals(OrderManager.EXIT)) {
      System.exit(1);
    }
    
    if (e.getSource() instanceof JComboBox) {
        String order = "";
        if(e.getSource() == objOrderManager.getCmbOrderType()){
            order = objOrderManager.getOrderType();
        }else if(e.getSource() == objOrderManager.getCmbOrderTypeHistory()){
            order = objOrderManager.getOrderHistory();
            objOrderManager.listOrderHistory(order,this);
            System.out.println(objOrderManager.getHistory(order).getTotal());
        }
      if (order.equals("") == false) {
        BuilderFactory factory = new BuilderFactory();
        //create an appropriate builder instance
        builder = factory.getUIBuilder(order);
        //configure the director with the builder
        UIOrderDirector director = new UIOrderDirector(builder);
        //director invokes different builder
        // methods
        director.build();
        //get the final build object
          System.out.println("cambio");
        JPanel UIObj = builder.getSearchUI();
        if(e.getSource() == objOrderManager.getCmbOrderType()){
            objOrderManager.displayNewUI(UIObj);
        }
      }
    }
    
    if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)) {
      //get input values
      String orderType = objOrderManager.getOrderType();
      String[] orderData = builder.getOrder();

      double dblOrderAmount = 0.0;
      double dblTax = 0.0;
      double dblSH = 0.0;
      
      String strOrderAmount = "0.0";
      String strTax = "0.0";
      String strSH = "0.0";
      
      int orderT = 0;
      
      if(builder instanceof CalOrderBuilder){
          strOrderAmount = orderData[0];
          strTax = orderData[1];
          orderT = 1;
      }else if(builder instanceof ColombianOrderBuilder){
          strOrderAmount = orderData[0];
          strSH = orderData[1];
          orderT = 2;
      }else if(builder instanceof OverseasOrderBuilder){
          strOrderAmount = orderData[0];
          strSH = orderData[1];
          orderT = 3;
      }else if(builder instanceof NonCalOrderBuilder){
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

      //Create the order
      Order order = createOrder(orderType, dblOrderAmount,
                    dblTax, dblSH);
      
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
        String[] orderData = builder.getOrder();
        String orderType = objOrderManager.getOrderHistory();

        double dblOrderAmount = 0.0;
        double dblTax = 0.0;
        double dblSH = 0.0;

        String strOrderAmount = "0.0";
        String strTax = "0.0";
        String strSH = "0.0";

        int orderT = 0;

        if(builder instanceof CalOrderBuilder){
            strOrderAmount = orderData[0];
            strTax = orderData[1];
            orderT = 1;
        }else if(builder instanceof ColombianOrderBuilder){
            strOrderAmount = orderData[0];
            strSH = orderData[1];
            orderT = 2;
        }else if(builder instanceof OverseasOrderBuilder){
            strOrderAmount = orderData[0];
            strSH = orderData[1];
            orderT = 3;
        }else if(builder instanceof NonCalOrderBuilder){
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

        //Create the order
        Order order = createOrder(orderType, dblOrderAmount,
                      dblTax, dblSH);
        try {
            System.out.println("POSSSS : "+ posOrder[0]);
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
                builder = factory.getUIBuilder(order);
                //configure the director with the builder
                UIOrderDirector director = new UIOrderDirector(builder);
                //director invokes different builder
                // methods
                director.build();
                //get the final build object
                System.out.println("cambio");
                
                JPanel UIObj = builder.getSearchUI();
                
                
                posOrder = e.getActionCommand().split("-");
              
                OrderComponent aux = objOrderManager.getHistory(objOrderManager.getOrderHistory());
                
                Order c = (Order) aux.orderObjList.get(Integer.parseInt(posOrder[0])-1);
                
                
                if (objOrderManager.getOrderHistory().equals(objOrderManager.CA_ORDER)){
                    CaliforniaOrder co = (CaliforniaOrder) c;
                    builder.inicialice(co.getOrderAmount() , co.getAdditionalTax(), 0);
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.NON_CA_ORDER)){
                    NonCaliforniaOrder nco = (NonCaliforniaOrder) c;
                    builder.inicialice(nco.getOrderAmount() , 0, 0);
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.OVERSEAS_ORDER)){
                    OverseasOrder oo = (OverseasOrder) c;
                    builder.inicialice(oo.getOrderAmount() , 0, oo.getAdditionalSH());
                } else  if (objOrderManager.getOrderHistory().equals(objOrderManager.CO_ORDER)){
                    ColombiaOrder col = (ColombiaOrder) c;
                    builder.inicialice(col.getOrderAmount() , 0, col.getAdditionalSH());
                }
                
                objOrderManager.displayNewUIHistory(UIObj);
                
            }
        }
    }
  }

  public Order createOrder(String orderType,double orderAmount, double tax, double SH) {
    if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
      return new CaliforniaOrder(orderAmount, tax);
    }
    if (orderType.equalsIgnoreCase(OrderManager.NON_CA_ORDER)) {
      return new NonCaliforniaOrder(orderAmount);
    }
    if (orderType.equalsIgnoreCase(OrderManager.OVERSEAS_ORDER)) {
      return new OverseasOrder(orderAmount, SH);
    }
    if(orderType.equalsIgnoreCase(OrderManager.CO_ORDER)){
        return new ColombiaOrder(orderAmount, SH);
    }
    return null;
  }

  public ButtonHandler() {
  }
  public ButtonHandler(OrderManager inObjOrderManager) {
    objOrderManager = inObjOrderManager;
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