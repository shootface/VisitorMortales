/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.Vector;

/**
 *
 * @author gumo0
 */
public abstract class OrderComponent{
    
    public void addOrder(Order o) throws Exception{
        throw new Exception("Invalid Operation. Not Supported");
    }
    public Order getOrder(int i)throws Exception{
        throw new Exception("Invalid Operation. Not Supported");
    }
}
