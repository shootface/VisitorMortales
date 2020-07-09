package orders;

import principal.OrderVisitor;

public abstract class Order extends OrderComponent{
  public abstract void accept(OrderVisitor v);
  @Override
  public abstract Double getTotal();
}
