java_library(
    name = "census",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@grpc_java//core",
        "@grpc_java//netty",
        "@io_grpc_grpc_services//jar",
        "@io_opencensus_opencensus_exporter_stats_prometheus//jar",
        "@io_opencensus_opencensus_api//jar",
        "//service",
    ],
    runtime_deps = [
        "@io_opencensus_opencensus_impl//jar",
        "@io_opencensus_opencensus_impl_core//jar",
    ],
)

java_binary(
    name = "census-server",
    main_class = "com.github.jyane.census.Server",
    runtime_deps = [":census"],
)

java_binary(
    name = "census-prometheus",
    main_class = "com.github.jyane.census.PrometheusServer",
    runtime_deps = [":census"],
)
