package roberts.inventory.business.configuration.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import roberts.inventory.data.repositories.UserRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@WebFilter
public class AuthFilter {
    private final ArrayList EXEMPT_URI = new ArrayList<>(Arrays.asList("/login.html", "/recover", "/register.html", "/reset"));

    private UserRepository userRepository;

    @Autowired
    AuthFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void destroy() {

    }

    /**
     * Filter body
     *
     * @param request  ServletRequest from browser
     * @param response ServletResponse sent to browser
     * @param chain    Filters to go through
     * @throws ServletException if something goes wrong
     * @throws IOException      if there is an IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {

        var httpServletRequest = (HttpServletRequest) request;
        var httpServletResponse = (HttpServletResponse) response;

		/*
		!!IMPORTANT!!

		Filters out CORS preflight requests. Without this every request will fail
		 */
        if (httpServletRequest.getHeader("Access-Control-Request-Method") != null)
        {
            chain.doFilter(request, response);
            return;
        }

        System.out.println(httpServletRequest.getRequestURI());

        //Exempt /login, /recover, and /register URI's from filtering (user does not need auth to access these)
        if (EXEMPT_URI.contains(httpServletRequest.getRequestURI()))
        {
            chain.doFilter(request, response);
            return;

        } else
        {

            //Grab token from http header
            var userToken = httpServletRequest.getHeader("Authorization");
            //if token does not exist in db and user is not authorized, skip rest of filters, and set HTTP response
            //code to 401 unauthorized

            if (!userRepository.existsUserEntityByToken(userToken) || userToken == null)
            {

                System.err.print("Request at URL " + httpServletRequest.getRequestURI() + " refused with token: " + userToken);
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException
    {

    }
}
