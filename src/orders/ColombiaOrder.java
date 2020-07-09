package orders;

import principal.OrderVisitor;

public class ColombiaOrder extends Order{
  private double orderAmount;
  private double additionalSH;

  public ColombiaOrder() {
  }
  public ColombiaOrder(double inp_orderAmount,
      double inp_additionalSH) {
    orderAmount = inp_orderAmount;
    additionalSH = inp_additionalSH;
  }
  public double getOrderAmount() {
    return orderAmount;
  }
  public double getAdditionalSH() {
    return additionalSH;
  }
  public void accept(OrderVisitor v) {
    v.visit(this);
  }

    @Override
    public Double getTotal() {
        return getOrderAmount()+getAdditionalSH();
    }
}
