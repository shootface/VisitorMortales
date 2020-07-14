package orders;

import principal.OrderVisitor;

public class CaliforniaOrder extends Order{
  private double orderAmount;
  private double additionalTax;

  public CaliforniaOrder() {
  }
  public CaliforniaOrder(double inp_orderAmount,
      double inp_additionalTax) {
    orderAmount = inp_orderAmount;
    additionalTax = inp_additionalTax;
  }
  public double getOrderAmount() {
    return orderAmount;
  }
  public double getAdditionalTax() {
    return additionalTax;
  }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setAdditionalTax(double additionalTax) {
        this.additionalTax = additionalTax;
    }

  
  
  @Override
  public void accept(OrderVisitor v) {
    v.visit(this);
  }

  @Override
    public Double getTotal() {
        return getOrderAmount()+getAdditionalTax();
    }
}

