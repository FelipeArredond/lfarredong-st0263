package com.peerclient.api.grpcclient;

import com.peerclient.api.entities.ResponseDTO;
import com.pserver.grpc.File;
import com.pserver.grpc.Response;
import com.pserver.grpc.pserverServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PeerServerClient {

    @Value("${service.grpc.neighbor}")
    private String neighbor;

    public ResponseDTO download(String fileName, String peerName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(peerName + ":9091").usePlaintext().build();
        pserverServiceGrpc.pserverServiceBlockingStub stub = pserverServiceGrpc.newBlockingStub(channel);
        Response response = stub.download(File.newBuilder().setFileName(fileName).build());
        channel.shutdown();

        return ResponseDTO.builder().message(response.getMessage()).build();
    }

    public ResponseDTO upload(String fileName){
        ManagedChannel channel = NettyChannelBuilder.forTarget(neighbor + ":9091").usePlaintext().build();
        pserverServiceGrpc.pserverServiceBlockingStub stub = pserverServiceGrpc.newBlockingStub(channel);
        Response response = stub.upload(File.newBuilder().setFileName(fileName).build());
        channel.shutdown();

        return ResponseDTO.builder().message(response.getMessage()).build();
    }

}
