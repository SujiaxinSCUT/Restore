package com.pojo.responsePOJO;

import com.pojo.Block;
import java.util.HashSet;
import java.util.Set;

public class PageableResponseBlock {

    private Set<Block> contents = new HashSet<>();
    private Integer page;
    private Integer size;
    private Integer total_elements;
    private Integer total_pages;

    public Set<Block> getContents() {
        return contents;
    }

    public void setContents(Set<Block> contents) {
        this.contents = contents;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal_elements() {
        return total_elements;
    }

    public void setTotal_elements(Integer total_elements) {
        this.total_elements = total_elements;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }
}
