package orders;

import principal.OrderVisitor;

public abstract class Order {
  public void addOrder(Order o) throws Exception{
      throw new Exception("Invalid Operation. Not Supported");
  }
  public Order getOrder(int i)throws Exception{
      throw new Exception("Invalid Operation. Not Supported");
  }
  public abstract void accept(OrderVisitor v);
  public abstract double getOrderAmount();
}
