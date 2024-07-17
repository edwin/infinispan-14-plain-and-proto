# Comparing Result of different Infinispan cache encodings

## Concept
Running a remote hotrod client to a clustered Infinispan, doing 100.000 `replaceWithVersion` on each cycle. 

## How To Run
```
$ java -Xmx4G -Xms4G -XX:+UseG1GC -jar target/infinispan-14-plain-and-proto-1.0-SNAPSHOT.jar
```

## Cache Configuration
Cache with `Plain` encoding
```xml
<?xml version="1.0"?>
<replicated-cache name="balance_plain" mode="SYNC" remote-timeout="30000" statistics="true">
	<encoding media-type="text/plain"/>
	<locking concurrency-level="64" isolation="READ_COMMITTED" acquire-timeout="300000" striping="false"/>
	<transaction mode="NON_XA" auto-commit="true" stop-timeout="300000" locking="PESSIMISTIC" reaper-interval="300000" complete-timeout="300000" notifications="true"/>
	<memory storage="OFF_HEAP" max-size="30000000000"/>
	<state-transfer timeout="300000"/>
</replicated-cache>
```

Cache with `Proto` encoding
```xml
<?xml version="1.0"?>
<replicated-cache name="balance_proto" mode="SYNC" remote-timeout="30000" statistics="true">
	<encoding media-type="application/x-protostream"/>
	<locking concurrency-level="64" isolation="READ_COMMITTED" acquire-timeout="300000" striping="false"/>
	<transaction mode="NON_XA" auto-commit="true" stop-timeout="300000" locking="PESSIMISTIC" reaper-interval="300000" complete-timeout="300000" notifications="true"/>
	<memory storage="OFF_HEAP" max-size="30000000000"/>
	<state-transfer timeout="300000"/>
</replicated-cache>
```

## text/plain Encoding 
```
$ for i in (seq 1 10);curl -s -k http://localhost:8080/plain ;end
```

Result

 Cycle  | ms  
--- | ---
  1 | 80478  (lets ignore this since Infinispan is still warming up)
  2 | 60670  
  3 | 70428  
  4 | 64678  
  5 | 65255  
  6 | 64661
  7 | 64105  
  8 | 65625  
  9 | 66735  
  10 | 66124
  Total | 588281


## application/x-protostream Encoding 
```
$ for i in (seq 1 10);curl -s -k http://localhost:8080/proto ;end
```

Result

 Cycle  | ms  
--- | ---
  1 | 58735 (lets ignore this since Infinispan is still warming up)
  2 | 56852  
  3 | 56572  
  4 | 57957  
  5 | 60376  
  6 | 64303
  7 | 58387  
  8 | 57873  
  9 | 57904 
  10 | 59561
  Total | 529785

## Conclusion

method | total time
--- | ----
text/plain Encoding | 588.281
application/x-protostream Encoding | 529.785

Time differences between two `encodings` is `58496 ms` in 9 cycles. Cache with `protostream` encoding is around 10 percent faster for this use case.