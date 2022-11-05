package com.github.notes.api.common.config.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.notes.api.common.config.constant.ApiConstants.NODE_ID;

@WebFilter("/*")
@RefreshScope
public class AdditionalResponseInfoFilter implements Filter {

    @Value("${spring.cloud.consul.discovery.instance-id}")
    private String nodeId;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse) response).setHeader(NODE_ID, nodeId);
        chain.doFilter(request, response);
    }
}