package builder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
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
public class ColombianOrderBuilder extends UIOrderBuilder{
    
    JTextField txtOrderAmount = new JTextField(10);
    JTextField txtAdditionalSH = new JTextField(10);

    @Override
    public void addUIControls() {
        orderUI = new JPanel();
        JLabel lblOrderAmount = new JLabel("Order Amount:");
        JLabel lblAdditionalSH = new JLabel("Additional S & H(Overseas Orders Only):");
        GridBagLayout gridbag = new GridBagLayout();
        orderUI.setLayout(gridbag);
        GridBagConstraints gbc = new GridBagConstraints();
        
        orderUI.add(lblOrderAmount);
        orderUI.add(lblAdditionalSH);
        orderUI.add(txtOrderAmount);
        orderUI.add(txtAdditionalSH);
        
        gbc.insets.top = 5;
        gbc.insets.bottom = 5;
        gbc.insets.left = 5;
        gbc.insets.right = 5;
        
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
        gridbag.setConstraints(lblAdditionalSH, gbc);
        //gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gridbag.setConstraints(txtAdditionalSH, gbc);
    }

    @Override
    public String[] getOrder() {
        String[] s = new String[2];
        s[0] = txtOrderAmount.getText();
        s[1] = txtAdditionalSH.getText();
        return s;
    }

    @Override
    public void inicialice(double oa, double tax, double sh) {
        txtOrderAmount.setText(String.valueOf(oa));
        txtAdditionalSH.setText(String.valueOf(sh));
    }
}
