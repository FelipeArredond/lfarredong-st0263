package com.peerclient.api.controllers;

import com.peerclient.api.entities.FileReportDTO;
import com.peerclient.api.grpcclient.GrpcClient;
import com.server.grpc.FileReport;
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

    @PostMapping("/reportFile")
    public String reportFile(@RequestBody FileReportDTO fileReport){
        return grpcClient.reportFile(fileReport.getFileName());
    }

}
