hoxton版本的zipkin-server需要从这里下载
[root@localhost ~]# curl -sSL https://zipkin.io/quickstart.sh | bash -s
部署方法
指定收集器，指定存储
[root@localhost ~]# STORAGE_TYPE=elasticsearch ES_HOSTS=http://47.99.88.28:9200 KAFKA_BOOTSTRAP_SERVERS=47.99.88.28:9092  java -jar zipkin.jar

                  oo
                 oooo
                oooooo
               oooooooo
              oooooooooo
             oooooooooooo
           ooooooo  ooooooo
          oooooo     ooooooo
         oooooo       ooooooo
        oooooo   o  o   oooooo
       oooooo   oo  oo   oooooo
     ooooooo  oooo  oooo  ooooooo
    oooooo   ooooo  ooooo  ooooooo
   oooooo   oooooo  oooooo  ooooooo
  oooooooo      oo  oo      oooooooo
  ooooooooooooo oo  oo ooooooooooooo
      oooooooooooo  oooooooooooo
          oooooooo  oooooooo
              oooo  oooo

     ________ ____  _  _____ _   _
    |__  /_ _|  _ \| |/ /_ _| \ | |
      / / | || |_) | ' / | ||  \| |
     / /_ | ||  __/| . \ | || |\  |
    |____|___|_|   |_|\_\___|_| \_|

:: version 2.20.0 :: commit 700907f ::

2020-03-03 20:09:23.044  INFO 17012 --- [           main] z.s.ZipkinServer                         : Starting ZipkinServer on localhost.localdomain with PID 17012 (/root/zipkin.jar started by root in /root)
2020-03-03 20:09:23.051  INFO 17012 --- [           main] z.s.ZipkinServer                         : The following profiles are active: shared
2020-03-03 20:09:24.501  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.verboseExceptions: rate-limit=10 (default)
2020-03-03 20:09:24.527  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.verboseSocketExceptions: false (default)
2020-03-03 20:09:24.527  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.verboseResponses: false (default)
2020-03-03 20:09:24.589  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.useEpoll: true (default)
2020-03-03 20:09:24.591  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.maxNumConnections: 2147483647 (default)
2020-03-03 20:09:24.592  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.numCommonWorkers: 4 (default)
2020-03-03 20:09:24.593  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.numCommonBlockingTaskThreads: 200 (default)
2020-03-03 20:09:24.595  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultMaxRequestLength: 10485760 (default)
2020-03-03 20:09:24.595  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultMaxResponseLength: 10485760 (default)
2020-03-03 20:09:24.596  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultRequestTimeoutMillis: 10000 (default)
2020-03-03 20:09:24.598  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultResponseTimeoutMillis: 15000 (default)
2020-03-03 20:09:24.599  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultConnectTimeoutMillis: 3200 (default)
2020-03-03 20:09:24.600  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultWriteTimeoutMillis: 1000 (default)
2020-03-03 20:09:24.600  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultServerIdleTimeoutMillis: 15000 (default)
2020-03-03 20:09:24.602  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultClientIdleTimeoutMillis: 10000 (default)
2020-03-03 20:09:24.603  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp2InitialConnectionWindowSize: 1048576 (default)
2020-03-03 20:09:24.605  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp2InitialStreamWindowSize: 1048576 (default)
2020-03-03 20:09:24.606  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp2MaxFrameSize: 16384 (default)
2020-03-03 20:09:24.607  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp2MaxStreamsPerConnection: 2147483647 (default)
2020-03-03 20:09:24.608  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp2MaxHeaderListSize: 8192 (default)
2020-03-03 20:09:24.609  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp1MaxInitialLineLength: 4096 (default)
2020-03-03 20:09:24.610  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp1MaxHeaderSize: 8192 (default)
2020-03-03 20:09:24.611  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultHttp1MaxChunkSize: 8192 (default)
2020-03-03 20:09:24.611  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultUseHttp2Preface: true (default)
2020-03-03 20:09:24.612  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultUseHttp1Pipelining: false (default)
2020-03-03 20:09:24.612  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultBackoffSpec: exponential=200:10000,jitter=0.2 (default)
2020-03-03 20:09:24.614  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.defaultMaxTotalAttempts: 10 (default)
2020-03-03 20:09:24.615  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.routeCache: maximumSize=4096 (default)
2020-03-03 20:09:24.615  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.routeDecoratorCache: maximumSize=4096 (default)
2020-03-03 20:09:24.615  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.compositeServiceCache: maximumSize=256 (default)
2020-03-03 20:09:24.616  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.parsedPathCache: maximumSize=4096 (default)
2020-03-03 20:09:24.616  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.headerValueCache: maximumSize=4096 (default)
2020-03-03 20:09:24.617  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.fileServiceCache: maximumSize=1024 (default)
2020-03-03 20:09:24.618  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.cachedHeaders: :authority,:scheme,:method,accept-encoding,content-type (default)
2020-03-03 20:09:24.620  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.annotatedServiceExceptionVerbosity: unhandled (default)
2020-03-03 20:09:24.621  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.useJdkDnsResolver: false (default)
2020-03-03 20:09:24.621  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.reportBlockedEventLoop: true (default)
2020-03-03 20:09:24.621  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.validateHeaders: true (default)
2020-03-03 20:09:24.621  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.useLegacyMeterNames: false (default)
2020-03-03 20:09:24.629  INFO 17012 --- [           main] c.l.a.c.Flags                            : Using /dev/epoll
2020-03-03 20:09:25.004  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.useOpenSsl: true (default)
2020-03-03 20:09:25.209  INFO 17012 --- [           main] c.l.a.c.Flags                            : Using OpenSSL: BoringSSL, 0x1010007f
2020-03-03 20:09:25.209  INFO 17012 --- [           main] c.l.a.c.Flags                            : com.linecorp.armeria.dumpOpenSslInfo: false (default)
2020-03-03 20:09:25.838  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.ConsumerConfig                 : ConsumerConfig values: 
        allow.auto.create.topics = true
        auto.commit.interval.ms = 5000
        auto.offset.reset = earliest
        bootstrap.servers = [47.99.88.28:9092]
        check.crcs = true
        client.dns.lookup = default
        client.id = 
        client.rack = 
        connections.max.idle.ms = 540000
        default.api.timeout.ms = 60000
        enable.auto.commit = true
        exclude.internal.topics = true
        fetch.max.bytes = 52428800
        fetch.max.wait.ms = 500
        fetch.min.bytes = 1
        group.id = zipkin
        group.instance.id = null
        heartbeat.interval.ms = 3000
        interceptor.classes = []
        internal.leave.group.on.close = true
        isolation.level = read_uncommitted
        key.deserializer = class org.apache.kafka.common.serialization.ByteArrayDeserializer
        max.partition.fetch.bytes = 1048576
        max.poll.interval.ms = 300000
        max.poll.records = 500
        metadata.max.age.ms = 300000
        metric.reporters = []
        metrics.num.samples = 2
        metrics.recording.level = INFO
        metrics.sample.window.ms = 30000
        partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor]
        receive.buffer.bytes = 65536
        reconnect.backoff.max.ms = 1000
        reconnect.backoff.ms = 50
        request.timeout.ms = 30000
        retry.backoff.ms = 100
        sasl.client.callback.handler.class = null
        sasl.jaas.config = null
        sasl.kerberos.kinit.cmd = /usr/bin/kinit
        sasl.kerberos.min.time.before.relogin = 60000
        sasl.kerberos.service.name = null
        sasl.kerberos.ticket.renew.jitter = 0.05
        sasl.kerberos.ticket.renew.window.factor = 0.8
        sasl.login.callback.handler.class = null
        sasl.login.class = null
        sasl.login.refresh.buffer.seconds = 300
        sasl.login.refresh.min.period.seconds = 60
        sasl.login.refresh.window.factor = 0.8
        sasl.login.refresh.window.jitter = 0.05
        sasl.mechanism = GSSAPI
        security.protocol = PLAINTEXT
        security.providers = null
        send.buffer.bytes = 131072
        session.timeout.ms = 10000
        ssl.cipher.suites = null
        ssl.enabled.protocols = [TLSv1.2, TLSv1.1, TLSv1]
        ssl.endpoint.identification.algorithm = https
        ssl.key.password = null
        ssl.keymanager.algorithm = SunX509
        ssl.keystore.location = null
        ssl.keystore.password = null
        ssl.keystore.type = JKS
        ssl.protocol = TLS
        ssl.provider = null
        ssl.secure.random.implementation = null
        ssl.trustmanager.algorithm = PKIX
        ssl.truststore.location = null
        ssl.truststore.password = null
        ssl.truststore.type = JKS
        value.deserializer = class org.apache.kafka.common.serialization.ByteArrayDeserializer

2020-03-03 20:09:25.954  INFO 17012 --- [           main] c.l.a.s.d.DocStringExtractor             : Using com.linecorp.armeria.thrift.jsonDir: META-INF/armeria/thrift
2020-03-03 20:09:26.185  INFO 17012 --- [pool-2-thread-1] o.a.k.c.u.AppInfoParser                  : Kafka version: 2.4.0
2020-03-03 20:09:26.185  INFO 17012 --- [pool-2-thread-1] o.a.k.c.u.AppInfoParser                  : Kafka commitId: 77a89fcf8d7fa018
2020-03-03 20:09:26.186  INFO 17012 --- [pool-2-thread-1] o.a.k.c.u.AppInfoParser                  : Kafka startTimeMs: 1583460566183
2020-03-03 20:09:26.192  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.KafkaConsumer                  : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Subscribed to topic(s): zipkin
2020-03-03 20:09:26.193  INFO 17012 --- [pool-2-thread-1] z.c.k.KafkaCollectorWorker               : Kafka consumer starting polling loop.
2020-03-03 20:09:26.278  INFO 17012 --- [           main] c.l.a.c.u.SystemInfo                     : Hostname: localhost.localdomain (from /proc/sys/kernel/hostname)
2020-03-03 20:09:27.149  INFO 17012 --- [pool-2-thread-1] o.a.k.c.Metadata                         : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Cluster ID: qSzzlvMpQn-w1t8n5LzVbw
2020-03-03 20:09:27.150  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.AbstractCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Discovered group coordinator izbp1jc2amxbl3xjw02s2xz:9092 (id: 2147483647 rack: null)
2020-03-03 20:09:27.365  INFO 17012 --- [oss-http-*:9411] c.l.a.s.Server                           : Serving HTTP at /0:0:0:0:0:0:0:0%0:9411 - http://127.0.0.1:9411/
2020-03-03 20:09:27.371  INFO 17012 --- [           main] c.l.a.s.ArmeriaAutoConfiguration         : Armeria server started at ports: {/0:0:0:0:0:0:0:0%0:9411=ServerPort(/0:0:0:0:0:0:0:0%0:9411, [http])}
2020-03-03 20:09:27.410  INFO 17012 --- [           main] z.s.ZipkinServer                         : Started ZipkinServer in 6.073 seconds (JVM running for 7.896)
2020-03-03 20:09:29.181  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.AbstractCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] (Re-)joining group
2020-03-03 20:09:29.351  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.ConsumerCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Finished assignment for group at generation 5: {consumer-zipkin-1-3f922489-1d87-41e9-a622-cff70d4526f8=org.apache.kafka.clients.consumer.ConsumerPartitionAssignor$Assignment@58dcc722}
2020-03-03 20:09:29.389  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.AbstractCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Successfully joined group with generation 5
2020-03-03 20:09:29.398  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.ConsumerCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Adding newly assigned partitions: zipkin-0
2020-03-03 20:09:29.429  INFO 17012 --- [pool-2-thread-1] o.a.k.c.c.i.ConsumerCoordinator          : [Consumer clientId=consumer-zipkin-1, groupId=zipkin] Setting offset for partition zipkin-0 to the committed offset FetchPosition{offset=12, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=izbp1jc2amxbl3xjw02s2xz:9092 (id: 0 rack: null), epoch=-1}}