load("@grpc_java//:java_grpc_library.bzl", "java_grpc_library")

java_binary(
    name = "interceptor-server",
    main_class = "com.github.jyane.Server",
    srcs = glob(["src/main/java/**/*.java"]),
     deps = [
         "@grpc_java//core",
         "@grpc_java//protobuf",
         "@grpc_java//stub",
         "@grpc_java//netty",
         "//proto:test_java_grpc",
         "//proto:test_java_proto",
     ]
)

java_binary(
    name = "interceptor-client",
    main_class = "com.github.jyane.Client",
    srcs = glob(["src/main/java/**/*.java"]),
     deps = [
         "@grpc_java//core",
         "@grpc_java//protobuf",
         "@grpc_java//stub",
         "@grpc_java//netty",
         "//proto:test_java_grpc",
         "//proto:test_java_proto",
     ]
)
