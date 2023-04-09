package spring.SpringBoot.entiry;

/**
 * 学生实体类
 * 
 * @author pang_gqing
 *
 */
public class Student {
	private String name;
	private String password;
	private String is_valid;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	
}