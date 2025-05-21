package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상품관리를 위한 Domain Layer (JavaBean) 
 * 객체 생성 패턴인 Builder 패턴 구현 단계
 * 1. 모든 프로퍼티를 한번에 받을 수 있는 생성자 정의 ex) @AllArgsConstructor
 * 2. 해당 생성자는 클래스 내부에서만 사용가능한 private 선언. ex) (access = AccessLevel.PRIVATE)
 * 3. 외부에서 객체 생성에 사용할 수 있도록 static 내부 클래스로 Builder 구현.
 *    ex) ProdVOBuilder
 * 4. Builder 내부에서 build() 메소드로 객체를 생성함.   
 * 5. build 대상이 되는 객체가 가진 프로퍼티를 결정할 수 있는 setter 역할 메소드 구현.
 *    -> 메소드 체이닝 구조를 위해 해당 메소드 내부에서 builder 가 다시 반환됨.
 * 
 */
@Data
@EqualsAndHashCode(of="prodId")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProdVO implements Serializable{
	private String prodId;
	private String prodName;
	private String lprodGu;
	private String buyerId;
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	@ToString.Exclude
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private LocalDate prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
}
