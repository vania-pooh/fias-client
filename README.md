# FIAS Client
This repository contains an implementation of FIAS (Federal Address Information System) of Russian Fiscal Authorities (FNS) database client. It supports only XML-based version of the database. **This library gives you NO performance guarantee and should NOT be used in performance sensitive environments. Its main purpose is to transfer data from FIAS XML files to any real database.**

## Usage
### Dependencies
Use your build tool of choice and add dependency below to your project. For Maven it looks like:
```xml
<dependency>
    <groupId>org.meridor</groupId>
    <artifactId>fias-client</artifactId>
    <version>${fias-client-latest.version}</version> <!-- Substitute latest release version here -->
</dependency>
```

### Loading data
You load data in two forms - **raw data** and **domain objects**. **Raw data** is a one-to-one mapping to XML structure whereas domain objects give you a set of XML-agnostic objects like **Region**, **City**, **Address** and so on. Loading raw data is as simple as specifying a predicate to e.g. **AddressObjects.Object** class:
```java
Path xmlDirectory = Paths.get("/my/xml/directory");
FiasClient fiasClient = new FiasClient(xmlDirectory);
fiasClient.load(o -> o.getAOLEVEL().equals(AddressLevel.REGION.getAddressLevel()));
```
To load data as domain objects - simply use one of **Fias** class methods like the following:
```java
Path xmlDirectory = Paths.get("/my/xml/path");
List<Region> regions = Fias
        .withXMLDirectory(xmlDirectory)
        .getRegions();
regions.forEach(r -> System.out.println(r.getName() + " " + r.getCode()));
```

## Building
In order to build the client from source code you need:
* Java 1.8+
* Maven 3
To run the build type:
```bash
$ mvn clean install
```