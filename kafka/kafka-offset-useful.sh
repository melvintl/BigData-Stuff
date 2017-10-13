#old 

kafka-consumer-groups --zookeeper zookeeper:2181 --list    

kafka-consumer-offset-checker --group schema-registry-org-schema-registry-8082 --topic _schemas  --zookeeper zookeeper:2181

kafka-topics --zookeeper zookeeper:2181 --list


kafka-topics --create --zookeeper zookeeper:2181   --replication-factor 1 --partitions 5 --topic mel1

kafka-console-producer --broker-list org-kafka:9092 --topic mel1 

kafka-console-consumer --zookeeper zookeeper:2181 --topic mel1

kafka-consumer-offset-checker --group console-consumer-24379 --topic mel1 --zookeeper zookeeper:2181


kafka-consumer-groups --zookeeper zookeeper:2181 --describe -group console-consumer-83573

kafka-consumer-groups --new-consumer  --bootstrap-server localhost:9092 --list

kafka-console-consumer --zookeeper org-zookeeper:2181 --topic topic1 --from-beginning