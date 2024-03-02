package com.server.api.grpcserviceimpl;

import com.server.grpc.Empty;
import com.server.grpc.FileReport;
import com.server.grpc.ResponseServer;
import com.server.grpc.serverServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerServiceImpl extends serverServiceGrpc.serverServiceImplBase {

    @Override
    public void hello(Empty request, StreamObserver<ResponseServer> responseObserver) {
        ResponseServer responseServer = ResponseServer.newBuilder().setResponseMessage("Hello from Grpc service!").build();
        responseObserver.onNext(responseServer);
        responseObserver.onCompleted();
    }

    @Override
    public void reportFile(FileReport request, StreamObserver<ResponseServer> responseObserver) {
        System.out.println("Received file " + request.getFileName());
        ResponseServer responseServer = ResponseServer.newBuilder().setResponseMessage("File " + request.getFileName() + " saved succesfully").build();
        responseObserver.onNext(responseServer);
        responseObserver.onCompleted();
    }
}
