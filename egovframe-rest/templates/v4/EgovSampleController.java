package egovframework.example.sample.web;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Sample REST 컨트롤러 (전자정부 표준프레임워크 4.x).
 * - @RestController + @RequestMapping
 * - 서비스는 인터페이스 타입으로 주입 (Impl 직접 주입 금지)
 * - 비즈니스 로직을 두지 않는다.
 */
@RestController
@RequestMapping("/sample")
public class EgovSampleController {

    @Resource(name = "sampleService")
    private EgovSampleService sampleService;

    /** 목록 조회 */
    @GetMapping
    public ResponseEntity<List<SampleVO>> selectSampleList(SampleDefaultVO searchVO) {
        return ResponseEntity.ok(sampleService.selectSampleList(searchVO));
    }

    /** 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<SampleVO> selectSample(@PathVariable("id") String id) {
        SampleVO vo = new SampleVO();
        vo.setId(id);
        return ResponseEntity.ok(sampleService.selectSample(vo));
    }

    /** 등록 */
    @PostMapping
    public ResponseEntity<String> insertSample(@Valid @RequestBody SampleVO sampleVO) {
        sampleService.insertSample(sampleVO);
        return ResponseEntity.ok("success");
    }

    /** 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSample(@PathVariable("id") String id,
                                               @Valid @RequestBody SampleVO sampleVO) {
        sampleVO.setId(id);
        sampleService.updateSample(sampleVO);
        return ResponseEntity.ok("success");
    }

    /** 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSample(@PathVariable("id") String id) {
        SampleVO vo = new SampleVO();
        vo.setId(id);
        sampleService.deleteSample(vo);
        return ResponseEntity.ok("success");
    }
}
