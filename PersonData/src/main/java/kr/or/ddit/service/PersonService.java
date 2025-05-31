package kr.or.ddit.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.vo.PersonVO;

public interface PersonService {
	public void createPerson(PersonVO person);
	public List<PersonVO> readPersonList();
	public Optional<PersonVO> readPerson(String id);
	public void modifyPerson(PersonVO person);
	public void remove(String person);
}
