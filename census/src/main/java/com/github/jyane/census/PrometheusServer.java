package com.github.jyane.census;

import com.github.jyane.service.TestServiceImpl;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import java.io.IOException;
import java.util.logging.Logger;

public class PrometheusServer {
  private static final Logger logger = Logger.getLogger(PrometheusServer.class.getName());

  private io.grpc.Server server;

  private void start() throws IOException {
    int port = 50051;
    server = ServerBuilder.forPort(port)
        .addService(new TestServiceImpl())
        .addService(ProtoReflectionService.newInstance())
        .build()
        .start();
    logger.info("Start at port: " + port);
    Runtime.getRuntime().addShutdownHook(new Thread(PrometheusServer.this::stop));
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  private void await() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  public static void main(String args[]) throws InterruptedException, IOException {
    PrometheusServer server = new PrometheusServer();
    server.start();
    server.await();
  }
}
