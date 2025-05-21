package kr.or.ddit.mbti.service;

import java.util.List;

import kr.or.ddit.mapper.MbtiMapper;
import kr.or.ddit.mapper.impl.MbtiMapperImpl;
import kr.or.ddit.mbti.MbtiNotFoundException;
import kr.or.ddit.vo.MbtiVO;

public class MbtiServiceImpl implements MbtiService{
	private MbtiMapper mapper = new MbtiMapperImpl();

	@Override
	public boolean createMbti(MbtiVO mbti) {
		return mapper.insertMbti(mbti) > 0;
	}

	@Override
	public List<MbtiVO> readMbtiList() {
		return mapper.selectMbtiList();
	}

	@Override
	public MbtiVO readMbti(String mtType) throws MbtiNotFoundException {
		MbtiVO mbti = mapper.selectMbti(mtType);
		if(mbti==null)
			throw new MbtiNotFoundException(String.format("%s 타입은 없음.", mtType));
		return mbti;
	}

	@Override
	public boolean modify(MbtiVO mbti) {
		return mapper.updateMbti(mbti)>0;
	}

	@Override
	public boolean remove(String mtType) {
		return mapper.deleteMbti(mtType)>0;
	}

}
