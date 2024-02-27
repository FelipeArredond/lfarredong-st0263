package com.schools.api.grpcserviceimpl;

import com.server.grpc.Empty;
import com.server.grpc.ResponseServer;
import com.server.grpc.serverServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcSchoolsServiceImpl extends serverServiceGrpc.serverServiceImplBase {

    @Override
    public void hello(Empty request, StreamObserver<ResponseServer> responseObserver) {
        ResponseServer responseServer = ResponseServer.newBuilder().setResponseMessage("Hello from Grpc service!").build();
        responseObserver.onNext(responseServer);
        responseObserver.onCompleted();
    }
}
