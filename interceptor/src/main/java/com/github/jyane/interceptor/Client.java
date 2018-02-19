package com.github.jyane.interceptor;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;
import jyane.grpc.testing.Test.SimpleRequest;
import jyane.grpc.testing.Test.SimpleResponse;
import jyane.grpc.testing.TestServiceGrpc;
import jyane.grpc.testing.TestServiceGrpc.TestServiceStub;

public class Client {
  private static final Logger logger = Logger.getLogger(Client.class.getName());

  static private void unaryCall(TestServiceStub stub) {
    StreamObserver<SimpleResponse> responseObserver = new StreamObserver<SimpleResponse>() {
      @Override
      public void onNext(SimpleResponse value) {
        logger.info("unary, server response : " + value);
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("unary, server error: " + status);
      }

      @Override
      public void onCompleted() {
        logger.info("unary, completed.");
      }
    };

    stub.unaryCall(SimpleRequest.newBuilder().setStr("1").build(), responseObserver);
  }

  static private void clientStream(TestServiceStub stub) {
    StreamObserver<SimpleResponse> responseObserver = new StreamObserver<SimpleResponse>() {
      @Override
      public void onNext(SimpleResponse value) {
        logger.info("client stream, server response : " + value);
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("client stream, server error: " + status);
      }

      @Override
      public void onCompleted() {
        logger.warning("client stream completed.");
      }
    };
    StreamObserver<SimpleRequest> request = stub.clientStreamCall(responseObserver);
    request.onNext(SimpleRequest.newBuilder().setStr("1").build());
    request.onNext(SimpleRequest.newBuilder().setStr("2").build());
    request.onCompleted();
  }

  static private void serverStream(TestServiceStub stub) {
    StreamObserver<SimpleResponse> responseObserver = new StreamObserver<SimpleResponse>() {
      @Override
      public void onNext(SimpleResponse value) {
        logger.info("server stream, server response : " + value);
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("server stream, server error: " + status);
      }

      @Override
      public void onCompleted() {
        logger.info("server stream, completed.");
      }
    };
    stub.serverStreamCall(SimpleRequest.newBuilder().setStr("1").build(), responseObserver);
  }

  static private void bidiStream(TestServiceStub stub) {
    StreamObserver<SimpleResponse> responseObserver = new StreamObserver<SimpleResponse>() {
      @Override
      public void onNext(SimpleResponse value) {
        logger.info("bidi stream, server response : " + value);
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("bidi stream, server error: " + status);
      }

      @Override
      public void onCompleted() {
        logger.info("bidi stream, completed.");
      }
    };
    StreamObserver<SimpleRequest> request = stub.bidiStreamCall(responseObserver);
    request.onNext(SimpleRequest.newBuilder().setStr("1").build());
    request.onNext(SimpleRequest.newBuilder().setStr("2").build());
    request.onCompleted();
  }

  public static void main(String args[]) {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("0.0.0.0", 50051)
        .usePlaintext(true)
        .build();
    logger.info("connected.");
    TestServiceStub stub = TestServiceGrpc.newStub(channel);
    try {
      unaryCall(stub);
      Thread.sleep(3000);
      clientStream(stub);
      Thread.sleep(3000);
      serverStream(stub);
      Thread.sleep(3000);
      bidiStream(stub);
      Thread.sleep(3000);
      channel.shutdown();
      logger.info("end.");
    } catch (InterruptedException e) {
      channel.shutdownNow();
    }
  }
}
