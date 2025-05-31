package kr.or.ddit.mapper.impl;

import java.util.List;

import kr.or.ddit.mapper.LprodMapper;
import kr.or.ddit.mapper.util.MapperProxyTemplate;
import kr.or.ddit.vo.LprodVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LprodMapperImpl implements LprodMapper {
	private static LprodMapperImpl self;
	public static LprodMapperImpl getInstance() {
		if(self==null) {
			self = new LprodMapperImpl();
		}
		return self;
	}
	
	MapperProxyTemplate<LprodMapper> template =
			new MapperProxyTemplate<>(LprodMapper.class);
	
	@Override
	public List<LprodVO> selectLprodList() {
		return template.execute((mp)->mp.selectLprodList());
	}

}
