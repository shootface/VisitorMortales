package builder;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julian Rueda
 */
public class CalOrderBuilder extends UIOrderBuilder {

    JTextField txtOrderAmount = new JTextField(10);
    JTextField txtAdditionalTax = new JTextField(10);
    JTextField txtAdditionalSH = new JTextField(10);

    public void addUIControls() {
        orderUI = new JPanel();
        
        JLabel lblOrderType = new JLabel("Order Type:");
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        JLabel lblAdditionalTax = new JLabel("Additional Tax(CA Orders Only):");
        JLabel lblAdditionalSH = new JLabel("Additional S & H(Overseas Orders Only):");

        JLabel lblTotal = new JLabel("Result:");
        JLabel lblTotalValue = new JLabel("Click Create or GetTotal Button");
        
    }

    @Override
    public int getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
