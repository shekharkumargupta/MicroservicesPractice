package com.skcodify.gatewayservice.config;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> openEndpoints =
            List.of("/auth");

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

//    public boolean isSecured(ServerHttpRequest request){
//        String uriPath = request.getURI().getPath();
//        System.out.println("Request path: " + uriPath);
//        for (String uri : openEndpoints){
//            if(uriPath.contains(uri)){
//                return false;
//            }
//        }
//        return true;
//    }

}
