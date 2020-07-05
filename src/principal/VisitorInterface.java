package principal;


import orders.CaliforniaOrder;
import orders.CaliforniaOrder;
import orders.OverseasOrder;
import orders.NonCaliforniaOrder;
import orders.NonCaliforniaOrder;
import orders.OverseasOrder;

public interface VisitorInterface {
  public void visit(NonCaliforniaOrder nco);
  public void visit(CaliforniaOrder co);
  public void visit(OverseasOrder oo);
}
