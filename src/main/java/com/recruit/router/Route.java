package com.recruit.router;

import lombok.ToString;

@ToString
public class Route {

    private String header;
    private String container;
    private String footer;

    public Route(String container) {
        this.container = container;
    }


    public Route(String header, String container, String footer) {
        this.header = header;
        this.container = container;
        this.footer = footer;
    }

    // getter / setter

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}