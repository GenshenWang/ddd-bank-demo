## 阿里技术专家详解DDD系列 第二弹 - 应用架构

链接：<a href="https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ%3D%3D&chksm=83953cc4b4e2b5d2bd4426e0d2103f2e95715b682f3b7ff333dbb123eaa79d3e5ad24f64beac&idx=1&mid=2650404060&scene=21&sn=cacf40d19528f6c2d9fd165151d6e8b4#wechat_redirect">阿里技术专家详解DDD系列 第二弹 - 应用架构</a>



### 1、高质量DDD应用内部架构要点

首先需要区别于宏观的架构，比如SOA、微服务架构，更多地强调的是应用内部的设计。

* 高可维护性
* 高可扩展性
* 高可测试性
* 代码结构清晰

独立性：

* 独立于框架：框架在经常变化，不能依赖于某个外部的框架。比如DAO操作时，完全依赖于MyBatis就不合适
* 独立于UI：页面以及对应数据结构也是不断在变化，要求架构设计的数据需要具有通用性，不能随之变化
* 独立于外部依赖：外部依赖 API无论怎么变化，核心架构应该尽量少的变动
* 独立于数据源：无论底层数据源使用的框架是Redis还是MongoDB等，都可能发生变化

可测试性：无论依赖什么数据源、外部依赖，都需要能够快速地进行测试和验证



### 2、如何考量接口的优劣

* 可维护性差
* 可扩展性差
* 可测试性差

**（1）可维护性**

可维护性 = 变化时代码的改动量

内部接口、中间件、外部服务等变动

- 数据结构的不稳定性：AccountDO类是一个纯数据结构，映射了数据库中的一个表。这里的问题是数据库的表结构和设计是应用的外部依赖，长远来看都有可能会改变，比如数据库要做Sharding，或者换一个表设计，或者改变字段名。
- 依赖库的升级：AccountMapper依赖MyBatis的实现，如果MyBatis未来升级版本，可能会造成用法的不同（可以参考iBatis升级到基于注解的MyBatis的迁移成本）。同样的，如果未来换一个ORM体系，迁移成本也是巨大的。
- 第三方服务依赖的不确定性：第三方服务，比如Yahoo的汇率服务未来很有可能会有变化：轻则API签名变化，重则服务不可用需要寻找其他可替代的服务。在这些情况下改造和迁移成本都是巨大的。同时，外部依赖的兜底、限流、熔断等方案都需要随之改变。
- 第三方服务API的接口变化：YahooForexService.getExchangeRate返回的结果是小数点还是百分比？入参是（source, target）还是（target, source）？谁能保证未来接口不会改变？如果改变了，核心的金额计算逻辑必须跟着改，否则会造成资损。
- 中间件更换：今天我们用Kafka发消息，明天如果要上阿里云用RocketMQ该怎么办？后天如果消息的序列化方式从String改为Binary该怎么办？如果需要消息分片该怎么改？



**（2）可扩展性**

可扩展性 = 需求变动时需要新增/修改的代码

主要体现在：

- 数据来源被固定、数据格式不兼容：原有的AccountDO是从本地获取的，而跨行转账的数据可能需要从一个第三方服务获取，而服务之间数据格式不太可能是兼容的，导致从数据校验、数据读写、到异常处理、金额计算等逻辑都要重写。
- 业务逻辑无法复用：数据格式不兼容的问题会导致核心业务逻辑无法复用。每个用例都是特殊逻辑的后果是最终会造成大量的if-else语句，而这种分支多的逻辑会让分析代码非常困难，容易错过边界情况，造成bug。
- 逻辑和数据存储的相互依赖：当业务逻辑增加变得越来越复杂时，新加入的逻辑很有可能需要对数据库schema或消息格式做变更。而变更了数据格式后会导致原有的其他逻辑需要一起跟着动。在最极端的场景下，一个新功能的增加会导致所有原有功能的重构，成本巨大。



**（3）可测试性**

可测试性 = 运行每个测试用例所花费的时间 * 每个需求所需要增加的测试用例数量

- 设施搭建困难：当代码中强依赖了数据库、第三方服务、中间件等外部依赖之后，想要完整跑通一个测试用例需要确保所有依赖都能跑起来，这个在项目早期是及其困难的。在项目后期也会由于各种系统的不稳定性而导致测试无法通过。
- 运行耗时长：大多数的外部依赖调用都是I/O密集型，如跨网络调用、磁盘调用等，而这种I/O调用在测试时需要耗时很久。另一个经常依赖的是笨重的框架如Spring，启动Spring容器通常需要很久。当一个测试用例需要花超过10秒钟才能跑通时，绝大部分开发都不会很频繁的测试。
- 耦合度高：假如一段脚本中有A、B、C三个子步骤，而每个步骤有N个可能的状态，当多个子步骤耦合度高时，为了完整覆盖所有用例，最多需要有N * N * N个测试用例。当耦合的子步骤越多时，需要的测试用例呈指数级增长。



### 3、需要遵循的常见软件设计原则

* 单一性原则（Single Responsibility Principle）
* 依赖反转：依赖反转原则要求在代码中依赖抽象，而不是具体的实现。在这个案例里外部依赖都是具体的实现，比如YahooForexService虽然是一个接口类，但是它对应的是依赖了Yahoo提供的具体服务，所以也算是依赖了实现。同样的KafkaTemplate、MyBatis的DAO实现都属于具体实现。
* 开闭原则



### 4、DDD的六边形架构

在我们传统的代码里，我们一般都很注重每个外部依赖的实现细节和规范，但是今天我们需要敢于抛弃掉原有的理念，重新审视代码结构。在上面重构的代码里，如果抛弃掉所有Repository、ACL、Producer等的具体实现细节，我们会发现每一个对外部的抽象类其实就是输入或输出，类似于计算机系统中的I/O节点。这个观点在CQRS架构中也同样适用，将所有接口分为Command（输入）和Query（输出）两种。除了I/O之外其他的内部逻辑，就是应用业务的核心逻辑。基于这个基础，Alistair Cockburn在2005年提出了Hexagonal Architecture（六边形架构），又被称之为Ports and Adapters（端口和适配器架构）。

<img src="https://wgs-markdown-pic.oss-cn-hangzhou.aliyuncs.com/img/DDD%E5%85%AD%E8%BE%B9%E5%BD%A2%E6%9E%B6%E6%9E%84.png" alt="image-20210513173353515"/>

在这张图中：

- I/O的具体实现在模型的最外层
- 每个I/O的适配器在灰色地带
- 每个Hex的边是一个端口
- Hex的中央是应用的核心领域模型



### 5、DDD模式下的代码架构

<img src="https://wgs-markdown-pic.oss-cn-hangzhou.aliyuncs.com/img/DDD%E4%BB%A3%E7%A0%81%E6%9E%B6%E6%9E%84.png" alt="image-20210513174246010"/>

我的理解：<img src="https://wgs-markdown-pic.oss-cn-hangzhou.aliyuncs.com/img/DDD%E9%A1%B9%E7%9B%AE%E6%9E%B6%E6%9E%84%E7%90%86%E8%A7%A3.png" alt="image-20210513204351850" />

* Types 模块：Types模块是保存可以对外暴露的Domain Primitives的地方。Domain Primitives因为是无状态的逻辑，可以对外暴露，所以经常被包含在对外的API接口中，需要单独成为模块。Types模块不依赖任何类库，**纯 POJO 。**
* Domain 模块：Domain 模块是核心业务逻辑的集中地，包含有状态的Entity、领域服务**Domain Service**、以及各种外部依赖的接口类（如Repository、ACL、中间件等。Domain模块仅依赖Types模块，**也是纯 POJO 。**
* Application模块主要包含Application Service和一些相关的类。Application模块依赖Domain模块。还是不依赖任何框架，**纯POJO。** Application主要是使用Domain、ACL等编排
* Infrastructure模块：Infrastructure模块包含了**Persistence、Messaging、External等模块。**比如：Persistence模块包含数据库DAO的实现，包含Data Object、ORM Mapper、Entity到DO的转化类等。Persistence模块要依赖具体的ORM类库，比如MyBatis。如果需要用Spring-Mybatis提供的注解方案，则需要依赖Spring。
* Web模块：Web模块包含Controller等相关代码。如果用SpringMVC则需要依赖Spring。
* Start模块：Start模块是SpringBoot的启动类。



DDD模式下的包组织结构：

<img src="https://wgs-markdown-pic.oss-cn-hangzhou.aliyuncs.com/img/DDD%E9%A1%B9%E7%9B%AE%E5%8C%85%E7%BB%84%E7%BB%87%E7%BB%93%E6%9E%84.png" alt="image-20210513174330333" />



对于测试：

* Types，Domain模块都属于无外部依赖的纯POJO，基本上都可以100%的被单元测试覆盖。
* Application模块的代码依赖外部抽象类，需要通过测试框架去Mock所有外部依赖，但仍然可以100%被单元测试。
* Infrastructure的每个模块的代码相对独立，接口数量比较少，相对比较容易写单测。但是由于依赖了外部I/O，速度上不可能很快，但好在模块的变动不会很频繁，属于一劳永逸。
* Web模块有两种测试方法：通过Spring的MockMVC测试，或者通过HttpClient调用接口测试。但是在测试时最好把Controller依赖的服务类都Mock掉。一般来说当你把Controller的逻辑都后置到Application Service中时，Controller的逻辑变得极为简单，很容易100%覆盖。
* Start模块：通常应用的集成测试写在start里。当其他模块的单元测试都能100%覆盖后，集成测试用来验证整体链路的真实性。





领域驱动模型：

<img src="https://wgs-markdown-pic.oss-cn-hangzhou.aliyuncs.com/img/%E9%A2%86%E5%9F%9F%E9%A9%B1%E5%8A%A8%E7%BC%96%E6%8E%92.png" alt="image-20210519170255106" style="zoom:53%;" />





