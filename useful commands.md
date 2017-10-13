# Random /useful Spark-HDFS commands


Kill process in port range (spark web ui)
```
kill -9 $(lsof -t -i:4040-4050)
```

```
hdfs dfsadmin -report
```
List all encryption zones
```
hdfs crypto -listZones
```
See file encryption details
```
hdfs crypto -getFileEncryptionInfo -path /<hdfs-file-path>
```

```
hdfs fsck /hdfs-file-path/ -blocks -racks -files
```





```
hadoop distcp -Dfs.s3.awsAccessKeyId=<> -Dfs.s3.awsSecretAccessKey=<> -f hdfs://quickstart.cloudera:8020/tmp/SourceList s3://<>/
```


Common YARN commands 
```
yarn top
yarn application -list
yarn node -list
yarn node -list -all
yarn logs -applicationId <>
yarn application -kill <applicationId>
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

Diff options to access Beeline
```
beeline -u "jdbc:hive2://nodename:10000/default;principal=hive/nodename@domainname" -d org.apache.hive.jdbc.HiveDriver

beeline -u "jdbc:hive2://nodename:10000/default;principal=hive/nodename@domainname;ssl=true;sslTrustStore=/opt/cloudera/security/jks/dev-trust-file.jks;trustStorePassword=pwd"

beeline -u "jdbc:hive2://nodename:10000/default;principal=hive/nodename@domainname;ssl=true;sslTrustStore=/usr/lib/jvm/java-8-oracle/jre/lib/security/cacerts" --color=true

```

Hive handy parameters 
```
set hive.exec.max.dynamic.partitions=5000;set hive.exec.max.dynamic.partitions.pernode=1000;set mapreduce.map.memory.mb=9000;set mapreduce.map.java.opts=-Xmx7200m;set mapreduce.reduce.memory.mb=9000;set mapreduce.reduce.java.opts=-Xmx7200m;set hive.exec.dynamic.partition.mode=nonstrict;

```


Spark - view client mode log for scheduler delays (streaming)
```
cat archiver.log | grep delay: | awk -F'[^0-9.]*' '{printf "%s/%s/%s %s:%s:%s,%s,%s,%s \n", $1, $2,$3 ,$4,$5, $6, $8 , $10, $8-$10 }'

```



PySpark + CDH + Python3
```
Step 1) kinit to get valid kerberos token
Step 2) you can set the environment variables in .bash_profile to change the default behaviour of pyspark to use python3
export PYTHONHASHSEED=0
export SPARK_YARN_USER_ENV=PYTHONHASHSEED=0
export PYSPARK_PYTHON=<python3 path/anaconda python path>
export PYSPARK_DRIVER_PYTHON=<python3 path/anaconda python path>
Step 3) pyspark2
```

