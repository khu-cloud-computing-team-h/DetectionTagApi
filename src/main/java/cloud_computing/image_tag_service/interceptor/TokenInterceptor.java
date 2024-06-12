package cloud_computing.image_tag_service.interceptor;

import com.google.api.Http;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.HandlerInterceptor;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    private static final String NOT_TOKEN_RESPONSE = "{\"error\":\"토큰을 찾을수 없습니다.\"}";
    private final String AuthServerUrl;
    private final String AuthApiURL;

    public record UserDetail(String id){}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null){
            response.setStatus(401);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(NOT_TOKEN_RESPONSE);
            return false;
        }

        try{
            ResponseEntity<UserDetail> authResponse = RestClient.builder()
                    .baseUrl(AuthServerUrl)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, token)
                    .build()
                    .get()
                    .uri(AuthApiURL)
                    .retrieve()
                    .toEntity(UserDetail.class);

            request.setAttribute("userId", new BigDecimal(authResponse.getBody().id));
        }catch(HttpClientErrorException errorException){
            response.setStatus(errorException.getStatusCode().value());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(errorException.getResponseBodyAsString());

            return false;
        }
        return true;
    }
}
