workspace(name = "study_grpc")

http_archive(
    name = "grpc_java",
    strip_prefix = "grpc-java-1.10.0",
    urls = ["https://github.com/grpc/grpc-java/archive/v1.10.0.zip"],
)

load("@grpc_java//:repositories.bzl", "grpc_java_repositories")

grpc_java_repositories()

maven_jar(
    name = "io_grpc_grpc_services",
    artifact = "io.grpc:grpc-services:1.10.0",
    sha1 = "ae898f12418429c9d1396aaf5ac2377bf8ecb25b",
)

maven_jar(
    name = "com_google_instrumentation_instrumentation_api",
    artifact = "com.google.instrumentation:instrumentation-api:0.4.3",
    sha1 = "41614af3429573dc02645d541638929d877945a2",
)
