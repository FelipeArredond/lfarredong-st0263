package com.peerclient.api.grpcclient;

import com.server.grpc.Empty;
import com.server.grpc.FileReport;
import com.server.grpc.serverServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcClient {

    ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9091").usePlaintext().build();

    public String getServerMessage(){
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.hello(Empty.newBuilder().build()).getResponseMessage();
        channel.shutdown();

        return response;
    }

    public String reportFile(String fileName){
        serverServiceGrpc.serverServiceBlockingStub stub = serverServiceGrpc.newBlockingStub(channel);
        String response = stub.reportFile(FileReport.newBuilder().setFileName(fileName).build()).getResponseMessage();
        channel.shutdown();

        return response;
    }

}
