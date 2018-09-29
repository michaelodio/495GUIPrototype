package bank;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;


public class CustomerMenu extends javax.swing.JFrame {

    private Bank bank;
    private String saveLocation = null;
    private final DefaultTableModel model;

    /**
     * Creates new form MainMenu
     */
    public CustomerMenu() {
        initComponents();
        setLocationRelativeTo(null);
        bank = new Bank();
        model = (DefaultTableModel) accountTable.getModel();
        reloadTable();

    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        depositButton = new javax.swing.JButton();
        withdrawButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bank Application");



        depositButton.setText("Deposit");
        depositButton.setEnabled(false);
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });

        withdrawButton.setText("Withdrawal");
        withdrawButton.setEnabled(false);
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });

        accountTable.setAutoCreateRowSorter(true);
        accountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Account Number", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
			public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accountTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        accountTable.getTableHeader().setReorderingAllowed(false);
        accountTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(accountTable);
        if (accountTable.getColumnModel().getColumnCount() > 0) {
            accountTable.getColumnModel().getColumn(0).setResizable(false);
            accountTable.getColumnModel().getColumn(1).setResizable(false);
            accountTable.getColumnModel().getColumn(2).setResizable(false);
            accountTable.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanelLayout.setHorizontalGroup(
        	contentPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(contentPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(contentPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        				.addGroup(contentPanelLayout.createSequentialGroup()
        					.addGap(162)
        					.addComponent(depositButton)
        					.addGap(18)
        					.addComponent(withdrawButton)
        					.addGap(0, 0, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
        	contentPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(contentPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(contentPanelLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(depositButton)
        				.addComponent(withdrawButton))
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        			.addContainerGap())
        );
        contentPanel.setLayout(contentPanelLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {
        depositOrWithdraw("deposit");
    }//GEN-LAST:event_depositButtonActionPerformed

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {
        depositOrWithdraw("withdrawal");
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void depositOrWithdraw(String action) {
        int selectedRow = accountTable.getSelectedRow();
        Customer customer = getSelectedCustomer(selectedRow);
        if (customer != null) {
            javax.swing.JDialog window = null;
            if (action.equals("deposit")) {
                window = new DepositForm(bank, customer);
            }
            else if (action.equals("withdrawal")) {
                window = new WithdrawalForm(bank, customer);
            }
            if (window != null) {
                window.setVisible(true);
            }
            reloadCustomerRowData(selectedRow, customer.getAccount().getAccountNumber());
        }
    }

    private void accountTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountTableMouseClicked
        setAccountButtonsActive(true);

        if (evt.getClickCount() == 2) {
            int selectedRow = accountTable.getSelectedRow();
            Customer customer = getSelectedCustomer(selectedRow);
            if (customer != null) {
                AccountInfo page = new AccountInfo(bank, customer);
                page.setVisible(true);
            }
        }
    }//GEN-LAST:event_accountTableMouseClicked

    private Customer getSelectedCustomer(int selectedRow) {
        Customer customer = null;
        if (selectedRow >= 0) {
            int accountNumber = (int) accountTable.getValueAt(selectedRow, 2);
            customer = bank.getCustomer(accountNumber);
        }
        return customer;
    }

    private void addCustomerToTable(Customer customer) {
        model.addRow(new Object[]{});
        reloadCustomerRowData(model.getRowCount() - 1, customer.getAccount().getAccountNumber());
    }

    private void removeCustomerFromTable(int row) {
        model.removeRow(row);
    }

    private void reloadTable() {
        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (Customer c :  bank.getCustomers()) {
            addCustomerToTable(c);
        }
    }

    private void reloadCustomerRowData(int selectedRow, int accountId) {
        Customer customer = bank.getCustomer(accountId);
        model.setValueAt(customer.getFirstName(), selectedRow, 0);
        model.setValueAt(customer.getLastName(), selectedRow, 1);
        model.setValueAt(customer.getAccount().getAccountNumber(), selectedRow, 2);
        model.setValueAt(String.format("%.2f", customer.getAccount().getBalance()), selectedRow, 3);
    }



    private void setAccountButtonsActive(boolean active) {
        depositButton.setEnabled(active);
        withdrawButton.setEnabled(active);

    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountTable;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton depositButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton withdrawButton;
    // End of variables declaration//GEN-END:variables

}