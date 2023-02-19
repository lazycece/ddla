# DDLA

Domain driven design layered architecture .

## 架构依赖
![ddd-module](./document/puml/ddd-module.puml)

## 数据流向

![ddd-components](./document/puml/ddd-component.puml)


## 包规范

## 脚手架

```shell
mvn archetype:generate \
    -DgroupId=com.lazycece.ddlademo \
    -DartifactId=ddlademo \
    -Dversion=1.0.0-SNAPSHOT \
    -Dpackage=com.lazycece.ddlademo \
    -DarchetypeArtifactId=ddla-archetype \
    -DarchetypeGroupId=com.lazycece.ddla \
    -DarchetypeVersion=1.0.0-SNAPSHOT
```

## License

[Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0.html)