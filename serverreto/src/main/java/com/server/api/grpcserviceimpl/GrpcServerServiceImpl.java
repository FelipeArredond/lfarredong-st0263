package com.server.api.grpcserviceimpl;

import com.server.api.repository.FileEntity;
import com.server.api.repository.FileRepository;
import com.server.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GrpcServerServiceImpl extends serverServiceGrpc.serverServiceImplBase {

    private final FileRepository fileRepository;

    @Override
    public void hello(Empty request, StreamObserver<ResponseServer> responseObserver) {
        ResponseServer responseServer = ResponseServer.newBuilder().setResponseMessage("Hello from Grpc service!").build();
        responseObserver.onNext(responseServer);
        responseObserver.onCompleted();
    }

    @Override
    public void reportFile(FileReport request, StreamObserver<ResponseServer> responseObserver) {
        System.out.println(fileRepository.save(FileEntity.builder().fileName(request.getFileName()).peerName(request.getPeerName()).build()));
        ResponseServer responseServer = ResponseServer.newBuilder().setResponseMessage("File " + request.getFileName() + " saved succesfully").build();
        responseObserver.onNext(responseServer);
        responseObserver.onCompleted();
    }

    @Override
    public void getFileLocation(FileName request, StreamObserver<PeerWithFile> responseObserver) {
        FileEntity fileData = fileRepository.findByFileName(request.getFileName()).orElse(FileEntity.builder().peerName("No peer listed with this file").build());
        responseObserver.onNext(PeerWithFile.newBuilder().setPeerName(fileData.getPeerName()).build());
        responseObserver.onCompleted();
    }
}
