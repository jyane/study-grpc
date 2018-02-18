package(default_visibility = ["//visibility:public"])

load("@grpc_java//:java_grpc_library.bzl", "java_grpc_library")

proto_library(
    name = "test_proto",
    srcs = ["test.proto"],
)

java_proto_library(
    name = "test_java_proto",
    deps = [":test_proto"],
)

java_grpc_library(
    name = "test_java_grpc",
    srcs = [":test_proto"],
    deps = [":test_java_proto"],
)
