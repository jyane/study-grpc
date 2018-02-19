package(default_visibility = ["//visibility:public"])

java_library(
    name = "service",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@grpc_java//core",
        "@grpc_java//protobuf",
        "@grpc_java//stub",
        "@grpc_java//netty",
        "@io_grpc_grpc_services//jar",
        "//proto:test_java_grpc",
        "//proto:test_java_proto",
    ],
)
