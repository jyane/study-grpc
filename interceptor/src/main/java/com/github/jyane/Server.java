package com.github.jyane;

import com.github.jyane.interceptor.LoggingInterceptor;
import com.github.jyane.service.TestServiceImpl;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.logging.Logger;

public class Server {
  private static final Logger logger = Logger.getLogger(Server.class.getName());

  private io.grpc.Server server;

  private void start() throws IOException {
    int port = 50051;
    server = ServerBuilder.forPort(port)
        .addService(new TestServiceImpl())
        .intercept(new LoggingInterceptor())
        .build()
        .start();
    logger.info("Start at port: " + port);
    Runtime.getRuntime().addShutdownHook(new Thread(Server.this::stop));
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
    Server server = new Server();
    server.start();
    server.await();
  }
}
