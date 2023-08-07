
public class UserAccount {
	private static String username;
	private static String password;
	private String name;
	private String phone;
	public String[] vehicles;
	
	public UserAccount(String name,String username,String password,String phone) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.phone = phone;
		
		System.out.println("User Account Create Successfully..");
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
