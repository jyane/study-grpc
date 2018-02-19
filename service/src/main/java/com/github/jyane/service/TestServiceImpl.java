package com.github.jyane.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.logging.Logger;
import jyane.grpc.testing.Test.SimpleRequest;
import jyane.grpc.testing.Test.SimpleResponse;
import jyane.grpc.testing.TestServiceGrpc;

public class TestServiceImpl extends TestServiceGrpc.TestServiceImplBase {
  private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());

  @Override
  public void unaryCall(SimpleRequest request, StreamObserver<SimpleResponse> responseObserver) {
    logger.info("unary request: " + request);
    responseObserver.onNext(SimpleResponse.newBuilder().setStr(request.getStr()).build());
    responseObserver.onCompleted();
    logger.info("unary call completed.");
  }

  @Override
  public StreamObserver<SimpleRequest> clientStreamCall(
      StreamObserver<SimpleResponse> responseObserver) {
    logger.info("client stream call start.");
    return new StreamObserver<SimpleRequest>() {
      @Override
      public void onNext(SimpleRequest value) {
        logger.info("client stream onNext: " + value);
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("client stream call error: " + status);
        responseObserver.onError(t);
      }

      @Override
      public void onCompleted() {
        logger.info("client stream call completed.");
      }
    };
  }

  @Override
  public void serverStreamCall(SimpleRequest request,
      StreamObserver<SimpleResponse> responseObserver) {
    logger.info("server stream request: " + request);
    responseObserver.onNext(SimpleResponse.newBuilder().setStr("1").build());
    responseObserver.onNext(SimpleResponse.newBuilder().setStr("2").build());
//    responseObserver.onError(
//        Status.INTERNAL.withDescription("server stream error").asException());
//    responseObserver.onNext(SimpleResponse.newBuilder().setStr("3").build());
    responseObserver.onCompleted();
    logger.info("server stream completed.");
  }

  @Override
  public StreamObserver<SimpleRequest> bidiStreamCall(
      StreamObserver<SimpleResponse> responseObserver) {
    logger.info("bidi stream call start.");
    return new StreamObserver<SimpleRequest>() {
      @Override
      public void onNext(SimpleRequest value) {
        responseObserver.onNext(SimpleResponse.newBuilder().setStr(value.getStr()).build());
      }

      @Override
      public void onError(Throwable t) {
        Status status = Status.fromThrowable(t);
        logger.warning("client stream call error: " + status);
        responseObserver.onError(t);
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
        logger.info("bidi stream call completed.");
      }
    };
  }
}
