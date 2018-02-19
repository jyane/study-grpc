package com.github.jyane.interceptor.interceptor;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import java.util.logging.Logger;

public class LoggingInterceptor implements ServerInterceptor {
  private static final Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

  class LoggingServerCall<ReqT, RespT> extends SimpleForwardingServerCall<ReqT, RespT> {
    LoggingServerCall(ServerCall<ReqT, RespT> delegate) {
      super(delegate);
    }

    @Override
    public void sendMessage(RespT message) {
      logger.info("server send a message: " + message);
      super.sendMessage(message);
    }

    @Override
    public void close(Status status, Metadata trailers) {
      logger.info(String.format("server close status: %s, %s", status, trailers));
      super.close(status, trailers);
    }
  }

  class LoggingServerCallListener<ReqT> extends ForwardingServerCallListener<ReqT> {
    private final Listener<ReqT> delegate;

    LoggingServerCallListener(Listener<ReqT> listener) {
     this.delegate = listener ;
    }

    @Override
    protected Listener<ReqT> delegate() {
      return delegate;
    }

    @Override
    public void onMessage(ReqT message) {
      logger.info("client send a message: " + message);
      super.onMessage(message);
    }

    @Override
    public void onComplete() {
      logger.info("client send a complete.");
      super.onComplete();
    }
  }

  @Override
  public <ReqT, RespT> Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers,
      ServerCallHandler<ReqT, RespT> next) {
    return new LoggingServerCallListener<>(
        next.startCall(new LoggingServerCall<>(call), headers));
  }
}
