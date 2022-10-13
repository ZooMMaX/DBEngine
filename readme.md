# CryptoLib
[![GitHub license](https://img.shields.io/github/license/ZooMMaX/DBEngine?style=plastic)](https://github.com/ZooMMaX/DBEngine/blob/release/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/ZooMMaX/DBEngine?style=plastic)](https://github.com/ZooMMaX/DBEngine/issues)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ZooMMaX/DBEngine/Build?style=plastic)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/ZooMMaX/DBEngine/Maven%20Package?style=plastic)
[![Maven Central](https://img.shields.io/maven-central/v/ru.zoommax/DBEngine.svg?label=Maven%20Central&style=plastic)](https://search.maven.org/search?q=g:%22ru.zoommax%22%20AND%20a:%22DBEngine%22)


### Dependencies
![dependency maven](https://img.shields.io/badge/DEPENDENCY-Maven-C71A36?style=plastic&logo=apachemaven)

![Maven Central](https://img.shields.io/maven-central/v/ru.zoommax/DBEngine?color=blue&label=version&style=plastic)
```xml
<dependencies>
    <dependency>
        <groupId>ru.zoommax</groupId>
        <artifactId>DBEngine</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```

![dependency gradle](https://img.shields.io/badge/DEPENDENCY-Gradle-02303A?style=plastic&logo=gradle)

![Maven Central](https://img.shields.io/maven-central/v/ru.zoommax/DBEngine?color=blue&label=version&style=plastic)
```groovy
implementation 'ru.zoommax:DBEngine:VERSION'
```

### How to use

```java
import ru.zoommax.api.DB;
import ru.zoommax.api.DBType;
import ru.zoommax.core.DBInit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        new DBInit().init(DBType.SQLITE, "test");
        boolean ok = new DB(DBType.SQLITE, "CREATE TABLE IF NOT EXISTS test(t TEXT, s TEXT);", null).execSQL();
        if (ok) {
            List<Object> ins = new ArrayList<>();
            ins.add("te");
            ins.add("st");
            ok = new DB(DBType.SQLITE, "INSERT INTO test(t, s) VALUES(?, ?);", ins).execSQL();
            if (ok) {
                ArrayList<HashMap<String, Object>> result = new DB(DBType.SQLITE, "SELECT * FROM test;", null).getMultiResultSet();
                for (HashMap<String, Object> hashMap : result){
                    String s = (String) hashMap.get("s");
                    String t = (String) hashMap.get("t");
                    System.out.println(t+s);
                }
            }
        }
    }
}
```
### Supports databases

v1.0:
- SQLite;
- MySQL;

### Whitepaper

All databases must work locally.

Plans:
- add support for remote databases;
- add support for other types of databases;