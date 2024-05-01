package com.techprimers.grpc.client.service;

import com.techprimers.grpc.GreetingRequest;
import com.techprimers.grpc.GreetingResponse;
import com.techprimers.grpc.GreetingServiceGrpc;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import pbentity.Member;
import pbentity.MemberServiceGrpc;
import pbentity.TokenRequest;

import java.util.concurrent.CompletableFuture;

@Service
public class TestService {

    @GrpcClient("com-member-grpc")
    MemberServiceGrpc.MemberServiceBlockingStub memberServiceBlockingStub;
    @GrpcClient("com-techprimers-grpc")
    GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceBlockingStub;

    public void isLogin(String token) {
        GreetingRequest request = GreetingRequest.newBuilder().setMessage(token).build();
        GreetingResponse response = greetingServiceBlockingStub.greeting(request);
        int i = 10;
    }

    public void loginTest(String token) {

        // create client using endpoints
        Client client = Client.builder().endpoints("http://localhost:2379").build();

        try {
            KV kvClient = client.getKVClient();
            ByteSequence key = ByteSequence.from("test_key".getBytes());
            ByteSequence value = ByteSequence.from("test_value".getBytes());

// put the key-value
            kvClient.put(key, value).get();

// get the CompletableFuture
            CompletableFuture<GetResponse> getFuture = kvClient.get(key);

// get the value from CompletableFuture
            GetResponse response = getFuture.get();

// delete the key
            kvClient.delete(key).get();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        TokenRequest tokenRequest = TokenRequest.newBuilder().setToken(token).build();
        try {
            Member member = memberServiceBlockingStub.isLogin(tokenRequest);
            System.out.println(member.getNickname());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}