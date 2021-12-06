package com.yuan.configer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuan.utils.ResStatus;
import com.yuan.utils.ResultVo;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }

        String token = request.getHeader("token");
        System.out.println("----------------" + token);
        if (token == null) {
            ResultVo resultVo = new ResultVo(ResStatus.NOT_LOGIN, "请先登录", null);
//            提示请先登录
            doResponse(response, resultVo);
            return false;
        } else {
            try {
//                验证token
                JwtParser parser = Jwts.parser();
//                验证生成token时的密码
                parser.setSigningKey("syx12138asdf");
//                如果token true放行，反之提示相关信息
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) {
                ResultVo resultVo = new ResultVo(ResStatus.LOGIN_TIMEOUT, "登录过期，请重新登录", null);
                doResponse(response, resultVo);
            } catch (UnsupportedJwtException e) {
                ResultVo resultVo = new ResultVo(ResStatus.LOGIN_WRONGFUL, "token不合法", null);
                doResponse(response, resultVo);
            } catch (Exception e) {
                ResultVo resultVo = new ResultVo(ResStatus.NO, "请先登录吧", null);
                doResponse(response, resultVo);

            }

        }
        return false;
    }

    private void doResponse(HttpServletResponse response, ResultVo resultVo) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVo);
        writer.print(s);
        writer.flush();
        writer.close();

    }
}
