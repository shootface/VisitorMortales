/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gumo0
 */
public class HistoryNonCalOrder extends OrderComponent{
    public static OrderComponent history;
    private HistoryNonCalOrder() {
        orderObjList = new Vector();
    }
    
    public static OrderComponent getOrderHistory(){
        if(history == null){
            history = new HistoryNonCalOrder();
        }
        return history;
    }
    
    public void addOrder(Order o) throws Exception{
        orderObjList.add(o);
    }
    public Order getOrder(int i)throws Exception{
        return (Order) (OrderComponent) orderObjList.elementAt(i);
    }
    public Double getTotal() {
        double total = 0.0;
        for(int i=0;i<orderObjList.size();i++){
            try {
                total = total+getOrder(i).getTotal();
            } catch (Exception ex) {
                Logger.getLogger(HistoryOverOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return total;
    }
    public void editOrder(Order oe,int i)throws Exception{
         orderObjList.setElementAt(oe, i);
     }
}
