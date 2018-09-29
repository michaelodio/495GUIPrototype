package bank;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fNametextField;
	private JTextField lNametextField;
	private JTextField usernameTextField;
	private JTextField passwordTxtField;
	private JTextField emailTxtField;
	private JTextField phoneTxtField;
	private JTextField initialDepositTxtField;

	private Bank bank;
	private Customer customer;

	public void close() {
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}

	Customer getCustomer() {
        return customer;
    }

	public AddAccount(Bank bank) {
		this.bank = bank;
        customer = null;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setBounds(10, 11, 67, 14);
			contentPanel.add(lblFirstName);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setBounds(10, 36, 67, 14);
			contentPanel.add(lblLastName);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(10, 61, 67, 14);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(10, 86, 67, 14);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(10, 111, 46, 14);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblPhone = new JLabel("Phone Number:");
			lblPhone.setBounds(10, 136, 103, 14);
			contentPanel.add(lblPhone);
		}
		{
			fNametextField = new JTextField();
			fNametextField.setBounds(198, 8, 226, 20);
			contentPanel.add(fNametextField);
			fNametextField.setColumns(10);
		}
		{
			lNametextField = new JTextField();
			lNametextField.setColumns(10);
			lNametextField.setBounds(198, 33, 226, 20);
			contentPanel.add(lNametextField);
		}
		{
			usernameTextField = new JTextField();
			usernameTextField.setColumns(10);
			usernameTextField.setBounds(198, 58, 226, 20);
			contentPanel.add(usernameTextField);
		}
		{
			passwordTxtField = new JTextField();
			passwordTxtField.setColumns(10);
			passwordTxtField.setBounds(198, 83, 226, 20);
			contentPanel.add(passwordTxtField);
		}
		{
			emailTxtField = new JTextField();
			emailTxtField.setColumns(10);
			emailTxtField.setBounds(198, 108, 226, 20);
			contentPanel.add(emailTxtField);
		}
		{
			phoneTxtField = new JTextField();
			phoneTxtField.setColumns(10);
			phoneTxtField.setBounds(198, 133, 226, 20);
			contentPanel.add(phoneTxtField);
		}
		{
			JLabel lblAccountType = new JLabel("Account Type:");
			lblAccountType.setBounds(10, 161, 92, 14);
			contentPanel.add(lblAccountType);
		}

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Checking", "Savings"}));
		comboBox.setBounds(198, 157, 226, 22);
		contentPanel.add(comboBox);
		{
			JLabel lblInitialDeposit = new JLabel("Initial Deposit:");
			lblInitialDeposit.setBounds(10, 185, 92, 14);
			contentPanel.add(lblInitialDeposit);
		}
		{
			initialDepositTxtField = new JTextField();
			initialDepositTxtField.setColumns(10);
			initialDepositTxtField.setBounds(198, 182, 226, 20);
			contentPanel.add(initialDepositTxtField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton registerBtn = new JButton("Register");
				registerBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						StringBuilder warnings = new StringBuilder();
						String firstname = "";
						String lastname = "";
						String username = "";
						String password = "";
						String email = "";
						String phone = "";

						double amount = 0;

						if(fNametextField.getText().isEmpty()) {
							warnings.append("First name must not be empty\n");

						} else {
					           firstname = fNametextField.getText();
					        }

						if(lNametextField.getText().isEmpty()) {
							warnings.append("Last name must not be empty\n");
						} else {
					            lastname = lNametextField.getText();
					        }
						if(usernameTextField.getText().isEmpty()) {
							warnings.append("Username must not be empty\n");
						} else {
					            username = usernameTextField.getText();
					        }
						if(passwordTxtField.getText().isEmpty()) {
							warnings.append("Password must not be empty\n");
						} else {
					            password = passwordTxtField.getText();
					        }
						if(emailTxtField.getText().isEmpty()) {
							warnings.append("Email must not be empty\n");
						} else {
					            email = emailTxtField.getText();
					        }
						if(phoneTxtField.getText().isEmpty()) {
							warnings.append("Phone number must not be empty\n");
						} else {
					            phone = phoneTxtField.getText();
					        }
						if(initialDepositTxtField.getText().isEmpty()) {
							warnings.append("Initial deposit must not be empty");
						}else {
							try {
								amount = Double.parseDouble(initialDepositTxtField.getText());
							}catch(NumberFormatException ex) {
								warnings.append("Initial deposit must be a number");
							}
						}
						if(warnings.length()>0) {
							JOptionPane.showMessageDialog(null, warnings.toString());
						}else {
							 AccountTypes act = AccountTypes.Undefined;
					            if (comboBox.getSelectedItem().toString() == "Checking") {
					                if (amount >= 50) {
					                    act = AccountTypes.Checking;
					                } else {
					                    warnings.append("Initial deposit must be at least $50 for Checking accounts.");
					                }
					            } else if (comboBox.getSelectedItem().toString() == "Savings") {
					                if (amount >= 10) {
					                    act = AccountTypes.Savings;
					                } else {
					                    warnings.append("Initial deposit must be at least $10 for Savings accounts.");
					                }
					            }
					            if (act != AccountTypes.Undefined) {
					                customer = bank.openAccount(firstname, lastname, username, password, email, phone,  act, amount);


					            } else {
					                JOptionPane.showMessageDialog(null, warnings.toString());
					            }
						}

					}
				});
				buttonPane.add(registerBtn);
				getRootPane().setDefaultButton(registerBtn);
			}
		}
	}
}
