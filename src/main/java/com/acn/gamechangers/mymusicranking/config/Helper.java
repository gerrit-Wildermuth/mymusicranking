package com.acn.gamechangers.mymusicranking.config;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Helper {

    public static Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
