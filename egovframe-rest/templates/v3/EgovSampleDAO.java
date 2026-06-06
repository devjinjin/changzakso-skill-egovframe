package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;

import org.egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

/**
 * Sample DAO (전자정부 3.x).
 * - EgovAbstractDAO 상속 (패키지 접두사 org.egovframework.rte)
 * - @Repository 로 등록
 * - SQL id는 매퍼 XML(EgovSample_SQL.xml)의 id와 일치
 */
@Repository("sampleDAO")
public class EgovSampleDAO extends EgovAbstractDAO {

    @SuppressWarnings("unchecked")
    public List<SampleVO> selectSampleList(SampleDefaultVO searchVO) {
        return (List<SampleVO>) list("EgovSample.selectSampleList", searchVO);
    }

    public int selectSampleListTotCnt(SampleDefaultVO searchVO) {
        return (Integer) selectByPk("EgovSample.selectSampleListTotCnt", searchVO);
    }

    public SampleVO selectSample(SampleVO sampleVO) {
        return (SampleVO) selectByPk("EgovSample.selectSample", sampleVO);
    }

    public void insertSample(SampleVO sampleVO) {
        insert("EgovSample.insertSample", sampleVO);
    }

    public void updateSample(SampleVO sampleVO) {
        update("EgovSample.updateSample", sampleVO);
    }

    public void deleteSample(SampleVO sampleVO) {
        delete("EgovSample.deleteSample", sampleVO);
    }
}
