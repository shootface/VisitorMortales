/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author gumo0
 */
public abstract class OrderComponent{
    
    public Vector orderObjList;
    public static OrderComponent history;
    
    public void addOrder(Order o) throws Exception{
        throw new Exception("Invalid Operation. Not Supported");
    }
    public Order getOrder(int i)throws Exception{
        throw new Exception("Invalid Operation. Not Supported");
    }
    
    public abstract Double getTotal();
    
    public Enumeration getAllOrders(){
        return orderObjList.elements();
    }
    
    public Iterator getAllTypeOrder(String type){
        return new IteratorOrder(this);
    }
    
}
