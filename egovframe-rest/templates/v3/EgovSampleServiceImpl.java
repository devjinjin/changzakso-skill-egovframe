package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;
import javax.annotation.Resource;

import org.egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Sample 서비스 구현 (전자정부 3.x).
 * - @Service("sampleService")
 * - EgovAbstractServiceImpl 상속 (필수, 패키지 접두사 org.egovframework.rte)
 */
@Service("sampleService")
public class EgovSampleServiceImpl extends EgovAbstractServiceImpl
        implements EgovSampleService {

    @Resource(name = "sampleDAO")
    private EgovSampleDAO sampleDAO;

    @Override
    public List<SampleVO> selectSampleList(SampleDefaultVO searchVO) {
        return sampleDAO.selectSampleList(searchVO);
    }

    @Override
    public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
        return sampleDAO.selectSampleListTotCnt(searchVO);
    }

    @Override
    public SampleVO selectSample(SampleVO sampleVO) {
        SampleVO result = sampleDAO.selectSample(sampleVO);
        if (result == null) {
            throw processException("info.nodata.msg");
        }
        return result;
    }

    @Override
    public String insertSample(SampleVO sampleVO) {
        sampleDAO.insertSample(sampleVO);
        return sampleVO.getId();
    }

    @Override
    public void updateSample(SampleVO sampleVO) {
        sampleDAO.updateSample(sampleVO);
    }

    @Override
    public void deleteSample(SampleVO sampleVO) {
        sampleDAO.deleteSample(sampleVO);
    }
}
