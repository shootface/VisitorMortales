package principal;

import orders.CaliforniaOrder;
import orders.OverseasOrder;
import orders.NonCaliforniaOrder;
import orders.ColombiaOrder;
import java.util.*;
import orders.CaliforniaOrder;
import orders.ColombiaOrder;
import orders.NonCaliforniaOrder;
import orders.OverseasOrder;

public class OrderVisitor implements VisitorInterface {
  //private Vector orderObjList;
  private double orderTotal;

  public OrderVisitor() {
    //orderObjList = new Vector();
  }
  public void visit(NonCaliforniaOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount();
  }
  public void visit(CaliforniaOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalTax();
  }
  public void visit(OverseasOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalSH();
  }
  public void visit(ColombiaOrder inp_order) {
    orderTotal = orderTotal + inp_order.getOrderAmount() +
                 inp_order.getAdditionalSH();
  }
  public double getOrderTotal() {
    return orderTotal;
  }
}
