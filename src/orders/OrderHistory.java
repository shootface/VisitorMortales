/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Enumeration;
import java.util.Vector;
import principal.OrderVisitor;

/**
 *
 * @author gumo0
 */
public class OrderHistory extends Order{
    private Vector orders;
    
    public OrderHistory() {
        orders = new Vector();
    }

    @Override
    public void accept(OrderVisitor v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getOrderAmount() {
        double ordersamount = 0.0;
        Enumeration e = orders.elements();
        while(e.hasMoreElements()){
        }
        return ordersamount;
    }
    
    public double getOrderAmount(String s) {
        double ordersamount = 0.0;
        Enumeration e = orders.elements();
        return ordersamount;
    }
    
    @Override
    public void addOrder(Order o) throws Exception{
      orders.add(o);
    }
    
    @Override
    public Order getOrder(int i)throws Exception{
        return (Order) orders.elementAt(i);
    }
    
}
