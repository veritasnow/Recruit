package com.recruit.controller.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.recruit.router.Router;
import com.recruit.router.ViewConstants;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI); // 에러 발생한 요청 URI
        Router router = Router.getInstance();

        // 특정 경로일 경우 별도 처리 (예: .well-known 관련 요청)
        if (uri != null && uri.startsWith("/.well-known")) {
            log.info("Ignoring .well-known error for URI: {}", uri);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
            return null; // 뷰 렌더링 없이 응답 종료
        }

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            log.info("error 발생!!! -- Code: " + statusCode);

            switch (statusCode) {
                case 404:
                    log.info("error 발생!!! -- notFoundPage");
                    model.addAttribute("route", router.getRoute("notFoundPage"));
                    break;
                case 405:
                    log.info("error 발생!!! -- methodNotAllowedPage");
                    model.addAttribute("route", router.getRoute("methodNotAllowedPage"));
                    break;
                case 403:
                    log.info("error 발생!!! -- forbidden");
                    model.addAttribute("route", router.getRoute("errorPage"));
                    break;
                default:
                    log.info("error 발생!!! -- 기타 에러");
                    model.addAttribute("route", router.getRoute("errorPage"));
                    break;
            }
        }

        return ViewConstants.INDEX;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}