package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Objects;

/**
 * VO(ValueObject), DTO(DataTransferObject)
 * 계층과 계층 사이의 전달이 가능하도록 java bean 규약에 따라 정의함.
 * 
 * java bean 규약
 * 1. 값을 저장할 프로퍼티 정의
 * 2. 프로퍼티 캡슐화 (private)
 * 3. 캡슐화된 프로퍼티에 접근할 수 있는 인터페이스 제공(getter/setter).
 *  get[set]프로퍼티명 -> camel case 표기
 * 4. vo의 상태를 비교할 수 있는 인터페이스 제공
 *  obj1 == obj2 obj1.equals(obj2)
 * 5. vo 의 상태를 확일할 수 있는 인터페이스 제공(toString)
 * 6. 직렬화 가능 선언
 */
public class StudentVO implements Serializable{
	private String id;
	private String name;
	private Integer age;
	private String birthday;
	private String address;
	private String career;
	private String[] hobbies;
	private String[] license;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String[] getLicense() {
		return license;
	}
	public void setLicense(String[] license) {
		this.license = license;
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
	
	

	
	
}
