package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Domain Layer : 테이블의 스키마(엔터티 구조)를 반영함. 
 * JavaBean 규약에 따라 개발되어야 함. -> lombok 활용중.
 */
@Data
//@Getter
@EqualsAndHashCode(of="mtType")
@NoArgsConstructor
//@AllArgsConstructor
public class MbtiVO {
	
	public MbtiVO(String mtType) {
		super();
		this.mtType = mtType;
	}
	private Integer mtSort;
	private String mtType;
	private String mtTitle;
	private String mtContent;
	
	private MbtiVO(Integer mtSort, String mtType, String mtTitle, String mtContent) {
		super();
		this.mtSort = mtSort;
		this.mtType = mtType;
		this.mtTitle = mtTitle;
		this.mtContent = mtContent;
	}
	
	public static MbitVOBuilder builder() {
		return new MbitVOBuilder();
	}
	
	public static class MbitVOBuilder{
		private Integer mtSort;
		private String mtType;
		private String mtTitle;
		private String mtContent;
		
		public MbitVOBuilder mtSort(Integer mtSort){
			this.mtSort = mtSort;
			return this;
		}
		public MbitVOBuilder mtType(String mtType){
			this.mtType = mtType;
			return this;
		}
		public MbitVOBuilder mtTitle(String mtTitle){
			this.mtTitle = mtTitle;
			return this;
		}
		public MbitVOBuilder mtContent(String mtContent){
			this.mtContent = mtContent;
			return this;
		}
		
		public MbtiVO build() {
			return new MbtiVO(mtSort, mtType, mtContent, mtTitle);
		}
	}
}













