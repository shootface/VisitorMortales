
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
  public abstract int getTotal();

  public JPanel getSearchUI() {
    return orderUI;
  }
    
}
