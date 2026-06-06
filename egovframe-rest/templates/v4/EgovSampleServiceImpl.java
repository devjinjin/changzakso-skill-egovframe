package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;
import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;

/**
 * Sample 서비스 구현 (전자정부 4.x).
 * - @Service("sampleService")
 * - EgovAbstractServiceImpl 상속 (필수)
 * - 비즈니스 로직/트랜잭션 경계가 위치하는 계층
 */
@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl
        implements EgovSampleService {

    @Resource(name = "sampleMapper")
    private EgovSampleMapper sampleMapper;

    /** 채번 서비스가 필요한 경우 주입하여 사용 */
    @Resource(name = "egovIdGnrService")
    private EgovIdGnrService egovIdGnrService;

    @Override
    public List<SampleVO> selectSampleList(SampleDefaultVO searchVO) {
        return sampleMapper.selectSampleList(searchVO);
    }

    @Override
    public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
        return sampleMapper.selectSampleListTotCnt(searchVO);
    }

    @Override
    public SampleVO selectSample(SampleVO sampleVO) {
        SampleVO result = sampleMapper.selectSample(sampleVO);
        if (result == null) {
            throw processException("info.nodata.msg");
        }
        return result;
    }

    @Override
    public String insertSample(SampleVO sampleVO) {
        // 필요 시: String id = egovIdGnrService.getNextStringId();
        sampleMapper.insertSample(sampleVO);
        return sampleVO.getId();
    }

    @Override
    public void updateSample(SampleVO sampleVO) {
        sampleMapper.updateSample(sampleVO);
    }

    @Override
    public void deleteSample(SampleVO sampleVO) {
        sampleMapper.deleteSample(sampleVO);
    }
}
