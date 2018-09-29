package bank;
import java.io.Serializable;


public class Customer implements Serializable{
    private final String firstName;
   	private final String lastName;
    private final String Username;
    private final String password;
    private final String Email;
    private final String phone;

    private final Account account;

    Customer(String firstName, String lastName, String Username, String password, String Email, String phone, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Username = Username;
        this.password = password;
        this.Email = Email;
        this.phone = phone;
        this.account = account;
    }



    public String basicInfo(){
        return " Account Number: " + account.getAccountNumber() + " - Name: " + getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return Email;
	}

	public String getPhone() {
		return phone;
	}

	public Account getAccount() {
		return account;
	}



	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName
				+ ", Username=" + Username + ", password=" + password
				+ ", Email=" + Email + ", phone=" + phone + ", account="
				+ account + "]";
	}



}
