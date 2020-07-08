package builder;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

    public void addUIControls() {
        orderUI = new JPanel();
       
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        JLabel lblAdditionalTax = new JLabel("Additional Tax(CA Orders Only):");
        GridBagLayout gridbag = new GridBagLayout();
        orderUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();

        orderUI.add(lblOrderAmount);
        orderUI.add(lblAdditionalTax);
        orderUI.add(txtOrderAmount);
        orderUI.add(txtAdditionalTax);

        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;

//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//
//        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gridbag.setConstraints(lblOrderAmount, gbc);
        //gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gridbag.setConstraints(txtOrderAmount, gbc);

        //gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gridbag.setConstraints(lblAdditionalTax, gbc);
        //gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(txtAdditionalTax, gbc);
        
        
    }

    @Override
    public String[] getOrder() {
        String[] s = new String[2];
        s[0] = txtOrderAmount.getText();
        s[1] = txtAdditionalTax.getText();
        return s;
    }

}
