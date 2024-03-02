package com.server.api.grpcserviceimpl;

import com.server.api.repository.FileEntity;
import com.server.api.repository.FileRepository;
import com.server.grpc.Empty;
import com.server.grpc.FileReport;
import com.server.grpc.ResponseServer;
import com.server.grpc.serverServiceGrpc;
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
}
