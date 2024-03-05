package com.peerclient.api.pservergrpc;

import com.peerclient.api.entities.FileRepository;
import com.peerclient.api.entities.Files;
import com.pserver.grpc.File;
import com.pserver.grpc.Response;
import com.pserver.grpc.pserverServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PServerGrpc extends pserverServiceGrpc.pserverServiceImplBase {

    private final FileRepository repository;

    @Override
    public void download(File request, StreamObserver<Response> responseObserver) {
        Files file = repository.findByName(request.getFileName()).orElse(Files.builder().name("Not found").build());
        responseObserver.onNext(Response.newBuilder().setMessage(file.getName()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void upload(File request, StreamObserver<Response> responseObserver) {
        if(!repository.existsByName(request.getFileName())){
            repository.save(Files.builder().name(request.getFileName()).build());
            System.out.println("Saved");
        }
        responseObserver.onNext(Response.newBuilder().setMessage("Uploaded succesfully").build());
        responseObserver.onCompleted();
    }
}
