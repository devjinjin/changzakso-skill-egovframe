package egovframework.example.sample.service;

import java.io.Serializable;

/**
 * 검색/페이징 조건 VO. 목록 조회 파라미터로 사용.
 */
public class SampleDefaultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String searchCondition = "";
    private String searchKeyword = "";

    private int pageIndex = 1;
    private int pageUnit = 10;
    private int pageSize = 10;
    private int firstIndex = 1;
    private int lastIndex = 1;
    private int recordCountPerPage = 10;

    public String getSearchCondition() { return searchCondition; }
    public void setSearchCondition(String v) { this.searchCondition = v; }
    public String getSearchKeyword() { return searchKeyword; }
    public void setSearchKeyword(String v) { this.searchKeyword = v; }
    public int getPageIndex() { return pageIndex; }
    public void setPageIndex(int v) { this.pageIndex = v; }
    public int getPageUnit() { return pageUnit; }
    public void setPageUnit(int v) { this.pageUnit = v; }
    public int getPageSize() { return pageSize; }
    public void setPageSize(int v) { this.pageSize = v; }
    public int getFirstIndex() { return firstIndex; }
    public void setFirstIndex(int v) { this.firstIndex = v; }
    public int getLastIndex() { return lastIndex; }
    public void setLastIndex(int v) { this.lastIndex = v; }
    public int getRecordCountPerPage() { return recordCountPerPage; }
    public void setRecordCountPerPage(int v) { this.recordCountPerPage = v; }
}
