/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Vector;
import principal.OrderVisitor;

/**
 *
 * @author gumo0
 */
public class HistoryCalOrder extends OrderComponent{
    Vector orderObjList = new Vector();

    public HistoryCalOrder() {
    }
    
    public void addOrder(OrderComponent o) throws Exception{
        orderObjList.add(o);
    }
    public Order getOrder(int i)throws Exception{
        return (Order) (OrderComponent) orderObjList.elementAt(i);
    }
}
