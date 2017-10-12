# Working with Sparklyr

Sparklyr is an R interface to Spark, it allows using Spark as the backend for dplyr – one of the most popular data manipulation packages. sparklyr also allows user to query data in Spark using SQL

```
system("echo melvin | kinit melvin") #this is only if environment is using kerberos, change to a keytab file in the real world :) 

library(sparklyr)
library(dplyr)

Sys.setenv(SPARK_HOME = "/opt/cloudera/parcels/SPARK2-2.0.0.cloudera2-1.cdh5.7.0.p0.118100/lib/spark2")
#Add optional config 
config <- spark_config()
config$`sparklyr.shell.driver-memory` <- "4G"  # Ref to this link for more options https://spark.rstudio.com/deployment.html#package_options, alternatively use config$spark.driver.memory <- "4G"

sc <- spark_connect(master="yarn-client",config=config,version = '2.0.0') 
# To run in local mode use: sc <- spark_connect(master = "local")
# To run in spark standalone cluster mode: sc <- spark_connect(master = "spark://local:7077")

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

Sample csv data (this was the first Google result so i took it from there, theres bound to be better datasets elsewhere ) 

```
wget http://www.sample-videos.com/csv/Sample-Spreadsheet-10-rows.csv
hadoop fs -put Sample-Spreadsheet-10-rows.csv /user/melvin/
```

```
res <- spark_read_csv(sc, name="some_data", path="hdfs:///user/melvin/Sample-Spreadsheet-10-rows.csv", header=FALSE)
```


# Other useful commands

To see the spark Web UI for the application, run the below. In YARN mode have seen this be a little flaky and doesnt always point to YARN application (ie points to default spark UI on port 4040 etc) in that case go to the YARN RM page on port 8090.
If using RStudio on the top right Spark tab click on Spark UI which works even in YARN mode
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

# Deploy and run sparkly apps
On a spark edgenode/ gateway note run the following, ideally have a bash script that does the kinit etc and then run the RScript within it
```
Rscript <script.r file>
```

## Other useful links 

⋅⋅* https://spark.rstudio.com/deployment.html#package_options
⋅⋅* https://github.com/rstudio/config  - good when working with RStudio
⋅⋅* https://github.com/rstudio/sparklyr
⋅⋅* https://aws.amazon.com/blogs/big-data/running-sparklyr-rstudios-r-interface-to-spark-on-amazon-emr/
⋅⋅* http://www.cureffi.org/2014/01/15/running-r-batch-mode-linux/ - may want to try to use the shebang 

