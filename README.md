# Spring Debezium Kafka

## Installation

- `Docker compose`

```shell
docker compose up
````

- `Debezium Postgresql Connector`

```shell
curl -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d '
{
   "name":"accounts-connector",
   "config":{
      "connector.class":"io.debezium.connector.postgresql.PostgresConnector",
      "plugin.name":"pgoutput",
      "tasks.max":"1",
      "database.hostname":"postgres",
      "database.port":"5432",
      "database.user":"debezium",
      "database.password":"123456",
      "database.dbname":"demo-debezium",
      "database.server.name":"postgres",
      "table.include.list":"public.accounts",
      "database.history.kafka.bootstrap.servers":"kafka:9092",
      "database.history.kafka.topic":"schema-changes.accounts",
      "topic.prefix":"demo-debezium",
      "topic.creation.enable":"true",
      "topic.creation.default.replication.factor":"1",
      "topic.creation.default.partitions":"1",
      "topic.creation.default.cleanup.policy":"delete",
      "topic.creation.default.retention.ms":"604800000",
      "decimal.handling.mode":"double",
      "transforms.unwrap.type":"io.debezium.transforms.ExtractNewRecordState",
      "transforms.unwrap.add.fields":"op,table,lsn,source.ts_ms",
      "transforms.unwrap.add.headers":"db",
      "transforms.unwrap.delete.handling.mode":"rewrite"
   }
}'
```

- `Postgres Debezium does not publish the previous state of a record` https://stackoverflow.com/a/59820210

```sql
ALTER TABLE public.accounts REPLICA IDENTITY FULL;
```

- Thanks to https://www.iamninad.com/posts/docker-compose-for-your-next-debezium-and-postgres-project/ and https://medium.com/@htyesilyurt/spring-boot-debezium-for-change-data-capture-cdc-kafka-mysql-redis-cacheable-all-in-one-708ef5298cba

