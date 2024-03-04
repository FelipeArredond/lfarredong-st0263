package com.peerclient.api.grpcclient;

import com.server.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    @Value("${service.grpc.host}")
    private String hostName;

    public String getServerMessage(){
        ManagedChannel channel = NettyChannelBuilder.forTarget(hostName + ":9091").usePlaintext().build();
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.hello(Empty.newBuilder().build()).getResponseMessage();
        channel.shutdown();

        return response;
    }

    public String reportFile(FileReport fileReport){
        ManagedChannel channel = NettyChannelBuilder.forTarget(hostName + ":9091").usePlaintext().build();
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.reportFile(fileReport).getResponseMessage();
        channel.shutdown();

        return response;
    }

    public PeerWithFile peerWithFile(FileName fileName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(hostName +  ":9091").usePlaintext().build();
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        PeerWithFile response = stub.getFileLocation(fileName);
        channel.shutdown();

        return response;
    }

}
