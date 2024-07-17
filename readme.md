# Comparing Result of different Infinispan cache encodings

## How To

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
	<encoding media-type="text/plain"/>
	<locking concurrency-level="64" isolation="READ_COMMITTED" acquire-timeout="300000" striping="false"/>
	<transaction mode="NON_XA" auto-commit="true" stop-timeout="300000" locking="PESSIMISTIC" reaper-interval="300000" complete-timeout="300000" notifications="true"/>
	<memory storage="OFF_HEAP" max-size="30000000000"/>
	<state-transfer timeout="300000"/>
</replicated-cache>
```

## text/plain Encoding 

## application/x-protostream Encoding 