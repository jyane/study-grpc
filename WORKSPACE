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
    name = "io_opencensus_opencensus_exporter_stats_prometheus",
    artifact = "io.opencensus:opencensus-exporter-stats-prometheus:0.12.0",
)

maven_jar(
    name = "io_opencensus_opencensus_impl_core",
    artifact = "io.opencensus:opencensus-impl-core:0.11.1",
    sha1 = "36fa020457c3f2e7fc94e1ef61953ec3ba286868",
)

maven_jar(
    name = "io_opencensus_opencensus_impl",
    artifact = "io.opencensus:opencensus-impl:0.11.1",
    sha1 = "fbb5db40760d9ae685de9ec2cc75eb0c058837ca",
)
