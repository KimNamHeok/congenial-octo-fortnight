package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.PersonVO;

public interface PersonMapper {
	public List<PersonVO> selectPersonList();
	
	public int insertPerson(PersonVO person);
	
	public int updatePerson(PersonVO person);
	
	public int deletePerson(String person);
	
	public PersonVO selectPerson(String person);
}
