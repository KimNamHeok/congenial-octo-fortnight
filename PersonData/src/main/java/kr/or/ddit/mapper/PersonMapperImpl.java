package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.mapper.util.MapperProxyTemplate;
import kr.or.ddit.vo.PersonVO;

public class PersonMapperImpl implements PersonMapper {
	private MapperProxyTemplate<PersonMapper> template =
			new MapperProxyTemplate<>(PersonMapper.class);
	
	@Override
	public List<PersonVO> selectPersonList() {
		return template.execute((mp)->mp.selectPersonList());
	}

	@Override
	public int insertPerson(PersonVO person) {
		return template.execute((mp)->mp.insertPerson(person));
	}

	@Override
	public int updatePerson(PersonVO person) {
		return template.execute((mp)->mp.updatePerson(person));
	}

	@Override
	public int deletePerson(String person) {
		return template.execute((mp)->mp.deletePerson(person));
	}

	@Override
	public PersonVO selectPerson(String person) {
		return template.execute((mp)->mp.selectPerson(person));
	}

}
