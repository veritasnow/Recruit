package com.recruit.security.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {

	public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return sanitize(super.getParameter(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) return null;

        String[] sanitized = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            sanitized[i] = sanitize(values[i]);
        }
        return sanitized;
    }

    private String sanitize(String input) {
        if (input == null) return null;
        return input
                .replaceAll("&" , "&amp;")
                .replaceAll("<" , "&lt;")
                .replaceAll(">" , "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'" , "&#039;");
    }
}
