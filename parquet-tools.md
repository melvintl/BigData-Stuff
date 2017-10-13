# parquet-tools cli usage 


https://github.com/Parquet/parquet-mr/tree/master/parquet-tools

Eg

```
parquet-tools meta  part-r-00001-f7ffca6b-5892-44f2-9fbc-5b6827bc91a8.gz.parquet | less
```
In the output:
RC refers to Row Count and VC refers to Value Count.
SZ:{x}/{y}/{z} - x = Compressed total, y=uncompressedtotal, z = y:x ratio 
Metadata information contains the compression used and Encoding used

Also ref:

parquet-tools meta
parquet-tools schema 
parquet-tools head
parquet-tools dump