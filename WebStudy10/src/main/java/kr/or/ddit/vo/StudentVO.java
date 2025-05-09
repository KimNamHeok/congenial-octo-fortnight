package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * VO(valueObject) => 값을 담기 위해서, DTO(DataTransferObject)=> 데이터 전송을 위한 객체
 * 값은 왜 담아야해? 중프를 하고있는 상황이었다면 최종적으로 DB에 넣었겠지
 * 계층과 계층 사이의 전달이 가능하도록 javabean 규약에 따라 정의함
 * 패키지명에 대문자를 넣지 않는다
 * 클래스 명에 처음은 대문자로 시작한다
 * 프로퍼티의 이름은 반드시 소문자로 시작한다. 단어와 단어가 2개이상되면 카멜케이스를 쓴다.
 * get[set]프로퍼티 명 -> camel case표기 이거 때문에!
 * 
 * 1. 값을 저장할 프로퍼티 정의
 * 2. 프로퍼티 캡슐화(private)
 * 3. 캡슐화된 프로퍼티에 접근할 수 있는 인터페이스 제공(getter/setter).
 * 		get[set]프로퍼티 명 -> camel case표기
 * 4. vo 상태를 비교할 수 있는 인터페이스 제공
 * 	obj1 == obj2 해시코드 비교
 * 	obj.equals(obj2) => 적절하게 오버라이딩해라 hashcode & equals
 * 
 * 5. vo의 상태를 확인 할 수 있는 인터페이스 제공(toString)
 * 
 * 6. 직렬화가 가능한 객체로 선언
 */
public class StudentVO implements Serializable{
	
	private String id;
	private String name;
	private Integer age;
	private String address;
	private String birthday;
	private String[] license;
	private String career;
	private String[] hobbies;
	private String grade;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String[] getLicense() {
		return license;
	}
	public void setLicense(String[] license) {
		this.license = license;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentVO other = (StudentVO) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "StudentVO [name=" + name + ", age=" + age + ", address=" + address + ", birthday=" + birthday
				+ ", license=" + Arrays.toString(license) + ", hobbies=" + Arrays.toString(hobbies) + ", grade=" + grade
				+ "]";
	}
	
	
}
