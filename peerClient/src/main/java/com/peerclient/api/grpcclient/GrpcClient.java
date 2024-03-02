package com.peerclient.api.grpcclient;

import com.server.grpc.Empty;
import com.server.grpc.FileReport;
import com.server.grpc.serverServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    public String getServerMessage(){
        ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9091").usePlaintext().build();
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.hello(Empty.newBuilder().build()).getResponseMessage();
        channel.shutdown();

        return response;
    }

    public String reportFile(FileReport fileReport){
        ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9091").usePlaintext().build();
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.reportFile(fileReport).getResponseMessage();
        channel.shutdown();

        return response;
    }

}
