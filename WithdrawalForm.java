package bank;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class WithdrawalForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Customer customer;
    private Bank bank;
    private JTextField withdrawTxtField;

    public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}


	public WithdrawalForm(Bank bank, Customer customer) {
	    this.bank = bank;
	    this.customer = customer;
		setBounds(100, 100, 284, 142);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Withdraw Amount:");
			lblNewLabel.setBounds(10, 11, 107, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			withdrawTxtField = new JTextField();
			withdrawTxtField.setBounds(119, 8, 133, 20);
			contentPanel.add(withdrawTxtField);
			withdrawTxtField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton withBtn = new JButton("Withdraw");
				withBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						  StringBuilder warnings = new StringBuilder();
					        if (withdrawTxtField.getText().isEmpty()) {
					            warnings.append("Withdrawal amount is required.\n");
					        } else {
					            double amount = 0;
					            try {
					                amount = Bank.round(Double.parseDouble(withdrawTxtField.getText()), 2);
					                JOptionPane.showMessageDialog(null, "Withdraw $" + String.format("%.2f", amount) + " from the account?\n");

					                    try {
					                        bank.withdraw(customer.getAccount().getAccountNumber(), amount);
					                    } catch (Exception ex) {
					                        warnings.append("Insufficient funds to complete transaction.\n");
					                    }

					                }
					             catch (NumberFormatException ex) {
					                warnings.append("Withdrawal amount must be a number.\n");
					            }
					        }
					        if (warnings.length() > 0) {
					            JOptionPane.showMessageDialog(null, warnings.toString(), "Withdrawal Warnings", JOptionPane.WARNING_MESSAGE);
					        }
					}
				});

				buttonPane.add(withBtn);
				getRootPane().setDefaultButton(withBtn);
			}
		}
	}

}


