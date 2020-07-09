/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Iterator;
import java.util.Vector;
import principal.OrderVisitor;

/**
 *
 * @author gumo0
 */
public class HistoryCalOrder extends OrderComponent implements Iterator{
    
    Vector orderObjList;
    private static OrderComponent history;

    private HistoryCalOrder() {
        orderObjList = new Vector();
    }
    
    public static OrderComponent getOrderHistory(){
        if(history == null){
            history = new HistoryCalOrder();
        }
        return history;
    }
    
    public void addOrder(Order o) throws Exception{
        orderObjList.add(o);
    }
    public Order getOrder(int i)throws Exception{
        return (Order) (OrderComponent) orderObjList.elementAt(i);
    }

    @Override
    public Double getTotal() {
        return 0.0;
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
