package com.peerclient.api.grpcclient;

import com.server.grpc.Empty;
import com.server.grpc.serverServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9090").usePlaintext().build();

    public String getServerMessage(){
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.hello(Empty.newBuilder().build()).getResponseMessage();
        channel.shutdown();

        return response;
    }
}
