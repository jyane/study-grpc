java_binary(
    name = "census",
    main_class = "com.github.jyane.census.Server",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@grpc_java//core",
        "@grpc_java//netty",
        "@io_grpc_grpc_services//jar",
        "@com_google_instrumentation_instrumentation_api//jar",
        "@io_opencensus_opencensus_impl//jar",
        "@io_opencensus_opencensus_api//jar",
        "//service",
    ],
)

java_binary(
    name = "census-prometheus",
    main_class = "com.github.jyane.census.PrometheusServer",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "@grpc_java//core",
        "@grpc_java//netty",
        "@io_grpc_grpc_services//jar",
        "@com_google_instrumentation_instrumentation_api//jar",
        "@io_opencensus_opencensus_exporter_stats_prometheus//jar",
        "@io_opencensus_opencensus_impl//jar",
        "@io_opencensus_opencensus_api//jar",
        "//service",
    ],
)
