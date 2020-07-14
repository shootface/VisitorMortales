package builder;


import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julian Rueda
 */
public abstract class UIOrderBuilder {
    
    protected JPanel orderUI;

  public abstract void addUIControls();
  public abstract String[] getOrder();
  public abstract void inicialice(double oa, double tax, double sh);

  public JPanel getSearchUI() {
    return orderUI;
  }
    
}
