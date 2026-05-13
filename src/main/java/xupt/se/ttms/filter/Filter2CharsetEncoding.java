package xupt.se.ttms.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns="/*", initParams=
{ @WebInitParam(name="encoding", value="UTF-8") })
public class Filter2CharsetEncoding implements Filter
{
    private String encoding=null;

    public void init(FilterConfig filterConfig) throws ServletException
    {
        encoding=filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest httpreq=(HttpServletRequest) request;
        if(httpreq.getMethod().equalsIgnoreCase("POST"))
        {
        	// post方式发送，直接设置字符集
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    } 

    public void destroy()
    {}
}