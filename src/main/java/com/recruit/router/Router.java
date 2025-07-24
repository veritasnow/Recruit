package com.recruit.router;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Router {

    private static Router instance;

    private final Map<String, Route> routeMap;

    private Router() {
        Map<String, Route> map = new HashMap<>();
        map.put("main"      , new Route("header", "main/main", "footer"));
        map.put("validation", new Route("header", "validation/validation", "footer"));
        map.put("security"  , new Route("header", "security/security", "footer"));
        map.put("popup"     , new Route("header", "popup/popup", "footer"));
        map.put("pdf"       , new Route("header", "sample/pdf/pdf", "footer"));
        map.put("excel"     , new Route("header", "sample/excel/excel", "footer"));
                
        
        //map.put("login", new Route("main/login"));
        //map.put("join" , new Route("main/join"));
        //map.put("redirect", new Route("redirect/redirect"));
        
        
        
        map.put("notFoundPage", new Route("header", "error/notFoundPage", "footer"));

        routeMap = Collections.unmodifiableMap(map);
    }

    public static synchronized Router getInstance() {
        if (instance == null) {
            instance = new Router();
        }
        return instance;
    }

    
    public Route getRoute(String key) {
        return routeMap.get(key);
    }
}
