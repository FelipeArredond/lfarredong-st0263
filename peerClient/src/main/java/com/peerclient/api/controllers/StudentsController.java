package com.peerclient.api.controllers;

import com.peerclient.api.grpcclient.GrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/server")
@RequiredArgsConstructor
public class StudentsController {

    private final GrpcClient grpcClient;

    @GetMapping("/state")
    public String getStudentCompleteInfo(){
        return grpcClient.getServerMessage();
    }

}
