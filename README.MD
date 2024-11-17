# Illumio Technical Assessment
## Assumptions
- the program only supports default log format, not custom and the only version that is supported is 2
- data/iana.csv is used for mapping log record protocol number and tag protocol name
- log record with protocol number not available in data/iana.csv is ignored
- user is required to provide path to file with mappings as the first parameter
- log entry protocol is lower cased in the output as matches are case insensitive
- Any port/protocol can have only one tag in mapping
- tags are case sensitive(sample output contains sv_P1 tag)
- tags with invalid protocol names are ignored
- based on sample output not matched tags are not included in the output (no sv_P5,0 record)
- output is unsorted including Untagged count
## Run
```
mvn install
```
```
java -jar target/illumio-1.0-SNAPSHOT.jar data/iana.csv data/flow.log data/tags.csv data/output.csv
```

