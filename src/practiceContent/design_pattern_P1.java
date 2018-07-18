package practiceContent;

public class design_pattern_P1 {
	
	public String name = "original name";
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		design_pattern_P1 ref = new design_pattern_P1();
		User user = new User.UserBuilder("Bell", ref).setAddress("my address").build();
		
		System.out.println(user.getRef().name);
		user.getRef().name = "new name";
		System.out.println(user.getRef().name);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

class User {
	private String name;
	private String address;
	private design_pattern_P1 ref1;
	
	private User(UserBuilder ub) {
		this.name = ub.name;
		this.address = ub.address;
		this.ref1 = ub.ref1;
	}
	
	public design_pattern_P1 getRef() {
		return ref1;
	}
	
	// nested class
	public static class UserBuilder {
		private final String name;
		private String address = "default";
		private design_pattern_P1 ref1;
		
		public UserBuilder(String n, design_pattern_P1 r) {
			this.name = n;
			this.ref1 = r;
		}
		
		public UserBuilder setAddress(String add) {
			this.address = add;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}
}




