package orders;

import principal.OrderVisitor;

public interface Order{
  public abstract void accept(OrderVisitor v);
}
