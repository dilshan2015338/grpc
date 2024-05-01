//package com.techprimers.grpc.client.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import io.etcd.jetcd.Client;
//
//@Configuration
//public class EtcdConfig {
//
//    @Value("${etcd.endpoint}")
//    private String etcdEndpoint;
//
//    @Bean
//    public Client etcdClient() {
//        return Client.builder().endpoints(etcdEndpoint).build();
//    }
//}