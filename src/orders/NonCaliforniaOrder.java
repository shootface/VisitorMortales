package orders;

import principal.OrderVisitor;

public class NonCaliforniaOrder extends Order {
  private double orderAmount;

  public NonCaliforniaOrder() {
  }
  public NonCaliforniaOrder(double inp_orderAmount) {
    orderAmount = inp_orderAmount;
  }
  public double getOrderAmount() {
    return orderAmount;
  }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }
  
  public void accept(OrderVisitor v) {
    v.visit(this);
  }

    @Override
    public Double getTotal() {
        return getOrderAmount();
    }
}
