package egovframework.example.sample.service;

import java.io.Serializable;

/** Sample VO (계층 간 데이터 운반 객체) - 전자정부 3.x */
public class SampleVO extends SampleDefaultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private String useYn;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUseYn() { return useYn; }
    public void setUseYn(String useYn) { this.useYn = useYn; }
}
