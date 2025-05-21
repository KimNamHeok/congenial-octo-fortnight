package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.mapper.ProdMapper;
import kr.or.ddit.mapper.impl.ProdMapperImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	private ProdMapper mapper = new ProdMapperImpl();
	
	@Override
	public void createProd(ProdVO prod) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProdVO> readProdList() {
		return mapper.selectProdList();
	}

	@Override
	public Optional<ProdVO> readProd(String prodId) {
		return Optional.ofNullable(mapper.selectProd(prodId));
	}

	@Override
	public void modifyProd(ProdVO prod) {
		// TODO Auto-generated method stub

	}

}
