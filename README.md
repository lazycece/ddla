# DDLA

[![Maven Central](https://img.shields.io/maven-central/v/com.lazycece.ddla/ddla-archetype)](https://search.maven.org/search?q=ddla-archetype)
[![License](https://img.shields.io/badge/license-Apache--2.0-green)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![GitHub release](https://img.shields.io/badge/release-download-orange.svg)](https://github.com/lazycece/ddla/releases)


DDLA (**D**omain **D**riven Design **L**ayered **A**rchitecture)， 结合了领域驱动设计思想和六边形架构思想，是DDD实践下的一种分层架构。

## DDLA 使用

使用如下maven命令，替换**groupId**、**artifactId**、**version**、**package**等参数为你期望的值。

```shell
mvn archetype:generate \
    -DgroupId=com.lazycece.ddlademo \
    -DartifactId=ddlademo \
    -Dversion=1.0.0-SNAPSHOT \
    -Dpackage=com.lazycece.ddlademo \
    -DarchetypeArtifactId=ddla-archetype \
    -DarchetypeGroupId=com.lazycece.ddla \
    -DarchetypeVersion=${ddla-archetype.version}
```


## 架构分层

![ddd-module](./document/puml/img/ddd-module.png)

<br/>
各模块职责如下：

- adapter：适配器层，是系统流量的入口，将请求分发给应用层去处理具体应用逻辑。该层涵盖业务接口请求、批处理、消息等。
- facade：门面层，作为系统的门面，用于表达系统对外暴露的能力。
- application：应用层，是系统的应用逻辑编排层，用于做跨系统或者跨聚合的业务编排，不表达核心控制能力。
- domain：领域层，作为领域驱动架构的内核，其承载的是系统的核心模型、规则与服务，是整个系统的大脑所在。
- infrastructure：基础设施层，是系统的基座，其支撑着领域层；该层的定位主要是系统基础能力（缓存、存储等）、下游支撑系统的集成、领域能力的防腐等。

## 架构依赖

![ddd-components](./document/puml/img/ddd-component.png)

<br/>

## 架构规范

### 工程结构

```bash
.
|-- app
|   |-- adapter
|   |-- application
|   |-- domain
|   |-- facade
|   `-- infrastructure
|       |-- acl
|       |-- dal
|       |-- integration
|-- bootstrap
|-- conf
`-- test
```

工程结构的各目录职责如下：
- app：工程应用存放代码的目录，其中子目录是前面架构分层中所说的不同模块
- bootstrap：应用的启动器，以及除环境之外的配置信息
- conf：环境相关的配置信息
- test：工程相关的测试代码

在工程结构上，将基础设施层按职责进行了再次拆分：
- dal: 数据库交互模块
- integration：系统集成模块，比如与下游系统的集成，其他基础能力的集成
- acl：系统防腐层，领域与基础设置之间的防腐隔离

### 工程规范

声明参数如下：

- package 表示指定业务包的父路径，如 com.lazycece.tradecore
- agg 表示业务聚合标识，如订单 order
- inm 表示集成业务模块

>除以下基本规范之外，实际实践中亦可以根据实际情况来添加或修改包路径，以便达到最佳实践效果。

|模块|模块描述|父package|子package|规范示例|规范说明|
|---|---|---|---|---|---|
|adapter|适配器|${package}.adapter|mobile|XxxAdapter|定义移动端服务适配器|
| | | |web.{agg}|XxxController|定义web接口适配器|
| | | |job|XxxJob|定义任务适配器|
| | | |consumer|XxxConsumer|定义消息消费者适配器|
|facade|应用门面|${package}.facade|${agg}.api|XxxCommandFacade <br/> XxxQueryFacade|使用CQRS架构模式定义系统门面服务，可用于暴露rpc/rest服务sdk|
| | | |{agg}.dto|XxxDTO|定义聚合内公用的实体DTO|
| | | |{agg}.enums|XxxEnum|定义聚合内需要供外部使用的枚举|
| | | |{agg}.request|XxxRequest|定义服务请求体|
| | | |{agg}.result|XxxResult|定义服务响应结果|
|application|应用层|${package}.application|${agg}|XxxCommandFacadeImpl <br/> XxxQueryFacadeImpl|定义业务聚合服务应用层实现,可对外暴露服务|
| | | |${agg}.assembler|XxxAssembler|定义数据编译器，实现应用层同下层（领域/基础设施）之间的数据编译构建|
| | | |${agg}.handler|XxxHandler|定义服务处理器|
| | | |${agg}.converter|XxxConverter|定义数据转换器，实现应用层同下层（领域/基础设施）之间的对象转换|
| | | |${agg}.validator|XxxValidator|定义业务请求校验器，实现复杂参数校验|
|domain|领域层|${package}.domain|${agg}.event|--|定义领域事件相关定义|
| | | |${agg}.factory|XxxFactory|定义聚合相关的工厂类|
| | | |${agg}.model|--|定义聚合、实体、值对象等信息|
| | | |${agg}.repository|XxxRepository|定义聚合内相关仓库接口|
| | | |${agg}.service|XxxService|定义聚合内相关领域服务接口|
| | | |${agg}.service.impl|XxxServiceImpl|定义聚合内相关领域服务接口实现|
| | | |${agg}.service.statemachine|--|定义聚合的状态机实现|
| | | |common.constants|--|定义应用域内的通用子域常量|
| | | |common.utils|--|定义应用域内的通用子域工具|
|infrastructure|基础设施层|--|--|--|--|
|acl|应用防腐层|${package}.infra.acl|service|XxxServiceImpl|定义防腐服务实现|
| | | |repository|XxxRepositoryImpl|定义领域仓储服务实现|
| | | |converter|XxxConverter|定义基础设施层对象到领域对象（实体、聚合、值对象）的转换器|
| | | |producer|--|定义消息发送实现|
| | | |cache|--|定义缓存服务实现|
|dal|数据库访问层|${package}.infra.dal|dto|XxxDTO|定义数据库访问层的dto|
| | | |mapper.auto|XxxMapper|定义工具自动生成的mapper|
| | | |mapper.udf|XxxUdfMapper|定义用户自定义的mapper|
| | | |po|XxxPO|定义数据库表字段映射的的PO实体|
|integration|业务系统集成层|${package}.infra.integration|${inm}|XxxClient|定义业务集成客户端|
| | | |${inm}.impl|XxxClientImpl|定义业务集成客户端实现|


## License

[Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0.html)