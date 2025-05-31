package kr.or.ddit.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.mapper.PersonMapper;
import kr.or.ddit.mapper.PersonMapperImpl;
import kr.or.ddit.vo.PersonVO;

public class PersonServiceImpl implements PersonService {
	private PersonMapper mapper = new PersonMapperImpl();
	
	@Override
	public void createPerson(PersonVO person) {
		mapper.updatePerson(person);

	}

	@Override
	public List<PersonVO> readPersonList() {
		return mapper.selectPersonList();
	}

	@Override
	public Optional<PersonVO> readPerson(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void modifyPerson(PersonVO person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String person) {
		// TODO Auto-generated method stub
		
	}

}
