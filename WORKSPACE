workspace(name = "study_grpc")

http_archive(
    name = "grpc_java",
    strip_prefix = "grpc-java-1.10.0",
    urls = ["https://github.com/grpc/grpc-java/archive/v1.10.0.zip"],
)

load("@grpc_java//:repositories.bzl", "grpc_java_repositories")

grpc_java_repositories()
