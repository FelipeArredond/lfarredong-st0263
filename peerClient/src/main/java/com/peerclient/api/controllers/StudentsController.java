package com.peerclient.api.controllers;

import com.peerclient.api.configuration.Token;
import com.peerclient.api.entities.*;
import com.peerclient.api.grpcclient.GrpcClient;
import com.peerclient.api.grpcclient.PeerServerClient;
import com.server.grpc.Empty;
import com.server.grpc.FileName;
import com.server.grpc.FileReport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/server")
@RequiredArgsConstructor
public class StudentsController {

    private final PeerServerClient peerServerClient;
    private final GrpcClient grpcClient;
    private final Token token;
    private final FileRepository repository;
    @Value("${service.grpc.peerName}")
    private String peerName;
    @Value("${service.grpc.neighbor}")
    private String neighbor;

    @GetMapping("/state")
    public ResponseEntity<String> getStudentCompleteInfo() {
        return ResponseEntity.ok(grpcClient.getServerMessage());
    }

    @PostMapping("/reportFile")
    public String reportFile(@RequestBody FileReportDTO fileReport) {
        return grpcClient.reportFile(FileReport.newBuilder().setFileName(fileReport.getFileName()).setPeerName(peerName).build());
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login() {
        String token = grpcClient.login(Empty.newBuilder().build()).getToken();
        this.token.setValue(token);
        System.out.println(this.token.getValue());
        return ResponseEntity.ok().body(this.token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        String response = grpcClient.logout(peerName);
        this.token.setValue("");
        System.out.println(response);
        System.out.println("The token is : " + this.token.getValue());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/download")
    public ResponseEntity<ResponseDTO> download(@RequestBody FileNameDTO fileName) {
        ResponseDTO responseFromPeer = peerServerClient.download(fileName.getFileName(), neighbor);
        if(!responseFromPeer.getMessage().equals("Not found")){
            return ResponseEntity.ok().body(responseFromPeer);
        }
        PeerWithFileDTO fileDTO = PeerWithFileDTO.builder().peerName(grpcClient.peerWithFile(FileName.newBuilder().setFileName(fileName.getFileName()).build()).getPeerName()).build();
        if(!fileDTO.getPeerName().equals("No peer listed with this file")){
            responseFromPeer.setMessage(peerServerClient.download(fileName.getFileName(), fileDTO.getPeerName()).getMessage());
        }
        return ResponseEntity.ok().body(responseFromPeer);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDTO> upload(@RequestBody FileNameDTO fileName) {
        return ResponseEntity.ok().body(peerServerClient.upload(fileName.getFileName()));
    }

}
