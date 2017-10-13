# Random /useful Spark-HDFS commands 


Kill process in port range (spark web ui)
```
 kill -9 $(lsof -t -i:4040-4050)
```


```
hdfs dfsadmin -report
```


```
hadoop distcp -Dfs.s3.awsAccessKeyId=<> -Dfs.s3.awsSecretAccessKey=<> -f hdfs://quickstart.cloudera:8020/tmp/SourceList s3://<>/
```


Common YARN commands 
```
yarn top
yarn application -list
yarn node -list
yarn logs -applicationId <>
```


Impala 
```
impala-shell -k -i node-name --ssl
```


Beeline coonnectivity 

```
!connect jdbc:hive2://localhost:10000
admin
admin
```

```
beeline -u "jdbc:hive2://nodename:10000/default;principal=hive/nodename@domainname" -d org.apache.hive.jdbc.HiveDriver

beeline -u "jdbc:hive2://nodename:10000/default;principal=hive/nodename@domainname;ssl=true;sslTrustStore=/opt/cloudera/security/jks/dev-trust-file.jks;trustStorePassword=pwd"


```

