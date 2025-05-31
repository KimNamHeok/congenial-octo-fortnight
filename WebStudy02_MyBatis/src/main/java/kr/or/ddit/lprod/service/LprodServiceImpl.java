package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.mapper.LprodMapper;
import kr.or.ddit.mapper.impl.LprodMapperImpl;
import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements LprodService {
	private LprodMapper mapper = LprodMapperImpl.getInstance();
	@Override
	public List<LprodVO> readLprodList() {
		return mapper.selectLprodList();
	}

}
