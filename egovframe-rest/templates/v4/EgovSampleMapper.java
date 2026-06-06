package egovframework.example.sample.service.impl;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * Sample MyBatis Mapper 인터페이스 (전자정부 4.x).
 * - @Mapper 선언 (전자정부 실행환경의 @Mapper 또는 MyBatis @Mapper)
 * - SQL은 매퍼 XML(EgovSample_SQL.xml)에 분리
 */
@Mapper("sampleMapper")
public interface EgovSampleMapper {

    List<SampleVO> selectSampleList(SampleDefaultVO searchVO);

    int selectSampleListTotCnt(SampleDefaultVO searchVO);

    SampleVO selectSample(SampleVO sampleVO);

    void insertSample(SampleVO sampleVO);

    void updateSample(SampleVO sampleVO);

    void deleteSample(SampleVO sampleVO);
}
