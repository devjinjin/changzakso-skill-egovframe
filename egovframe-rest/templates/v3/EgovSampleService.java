package egovframework.example.sample.service;

import java.util.List;

/** Sample 서비스 인터페이스 (전자정부 3.x). ServiceImpl은 1:1로 구현한다. */
public interface EgovSampleService {
    List<SampleVO> selectSampleList(SampleDefaultVO searchVO);
    int selectSampleListTotCnt(SampleDefaultVO searchVO);
    SampleVO selectSample(SampleVO sampleVO);
    String insertSample(SampleVO sampleVO);
    void updateSample(SampleVO sampleVO);
    void deleteSample(SampleVO sampleVO);
}
