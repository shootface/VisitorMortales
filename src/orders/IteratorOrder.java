/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Julian Rueda
 */
public class IteratorOrder implements Iterator {
    
    Order nextOrder;
    OrderComponent oc;
    Enumeration ec;
    
    public IteratorOrder(OrderComponent orderComponent){
        oc = orderComponent;
        ec = orderComponent.getAllOrders();
    }
    

    public boolean hasNext() {
        boolean matchFound = false;
        
        while(ec.hasMoreElements()){
            Order auxOrder = (Order) ec.nextElement();
            
            matchFound = true;
            nextOrder = auxOrder;
            break;
            
        }
        
        if (matchFound == true){
        } else { 
            nextOrder = null;
        }
        
        return matchFound;
    }


    public Object next() {
        if (nextOrder == null){
            throw new NoSuchElementException();
        } else {
            return nextOrder;
        }
        
    }
    
}
