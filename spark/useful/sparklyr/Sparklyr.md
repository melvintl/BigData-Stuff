# Example of running Sparklyr

```
system("echo melvin | kinit melvin") #this is only if environment is using kerberos, change to a keytab file in the real world :) 

library(sparklyr)
library(dplyr)

Sys.setenv(SPARK_HOME = "/opt/cloudera/parcels/SPARK2-2.0.0.cloudera2-1.cdh5.7.0.p0.118100/lib/spark2")
#Add optional config 
config <- spark_config()
config$`sparklyr.shell.driver-memory` <- "4G"

sc <- spark_connect(master="yarn-client",config=config,version = '2.0.0') 
iris_tbl <- copy_to(sc, iris)
head(iris_tbl)
```

## Working with SQL data and Hive 

```
library(DBI)
results <-  dbGetQuery(sc, "SELECT * FROM iris LIMIT 10")
results
```

## Working with Data on HDFS 

Sample csv data (this was the first google page so i took it from there :) ) 

```
wget http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv
hadoop fs -put Sample-Spreadsheet-10-rows.csv /user/melvin/
```

```
res <- spark_read_csv(sc, name="some_data", path="hdfs:///user/melvin/Sample-Spreadsheet-10-rows.csv", header=FALSE)
```


# Other useful commands

To see the spark Web UI for the application, run the below. In YARN mode have seen this be a little flaky and doesnt always point to YARN application (ie points to default spark UI on port 4040 etc) in that case go to the YARN RM page on port 8090
```
spark_web(sc)
```

For debugging purp/see the spark logs 
```
spark_log(sc, n=15)
```

To disconnect from the spark session 
```
spark_disconnect(sc)
```

