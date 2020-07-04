/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julian Rueda
 */
public class UIOrderDirector {
    
    private UIOrderBuilder builder;

  public UIOrderDirector(UIOrderBuilder bldr) {
    builder = bldr;
  }
  public void build() {
    builder.addUIControls();
  }

}

