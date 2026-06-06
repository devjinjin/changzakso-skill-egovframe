package egovframework.example.sample.web;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Sample REST 컨트롤러 (전자정부 표준프레임워크 3.x).
 * - @Controller + @ResponseBody 로 JSON 응답 (3.x 관례)
 *   (dispatcher-servlet 에 Jackson 메시지 컨버터가 구성되어 있어야 함)
 * - 서비스는 @Resource(name=...) 로 인터페이스 타입 주입
 */
@Controller
@RequestMapping("/sample")
public class EgovSampleController {

    @Resource(name = "sampleService")
    private EgovSampleService sampleService;

    @GetMapping
    @ResponseBody
    public List<SampleVO> selectSampleList(SampleDefaultVO searchVO) {
        return sampleService.selectSampleList(searchVO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SampleVO selectSample(@PathVariable("id") String id) {
        SampleVO vo = new SampleVO();
        vo.setId(id);
        return sampleService.selectSample(vo);
    }

    @PostMapping
    @ResponseBody
    public String insertSample(@RequestBody SampleVO sampleVO) {
        sampleService.insertSample(sampleVO);
        return "success";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updateSample(@PathVariable("id") String id, @RequestBody SampleVO sampleVO) {
        sampleVO.setId(id);
        sampleService.updateSample(sampleVO);
        return "success";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteSample(@PathVariable("id") String id) {
        SampleVO vo = new SampleVO();
        vo.setId(id);
        sampleService.deleteSample(vo);
        return "success";
    }
}
