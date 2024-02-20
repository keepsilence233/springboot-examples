# MongoDB 使用教程



## 基本概念

### 数据库（Database）

是集合的容器，相当于关系型DB中的数据库

### 集合（Collection）

数据库中的一组文档，相当于SQL中的表

### 文档（Document）

集合中的一条记录，相当于SQL的表中的一行。

不同的文档之间不必有相同的结构，这一点是和SQL不同的。

### 字段（Field）

文档中的键值对，相当于SQL中的列。比较重要的区别是MongoDB中的一个Field可以是一个JSON对象或数组



## 数据类型

- **String：** 字符串，存储数据常用的数据类型。在 MongoDB 中，UTF-8 编码的字符串才是合法的。
- **Integer：** 整型数值，用于存储数值。根据你所采用的服务器，可分为 32 位或 64 位。
- **Boolean：** 布尔值，用于存储布尔值（true/false）。
- **Double：** 双精度浮点值，用于存储浮点值。
- **Min/Max keys：** 将一个值与 BSON（二进制的 JSON）元素的最低值和最高值相对比。
- **Array：** 用于将数组或列表或多个值存储为一个键。
- **Timestamp：** 时间戳。记录文档修改或添加的具体时间。
- **Object：** 用于内嵌文档。
- **Null：** 用于创建空值。
- **Symbol：** 符号。该数据类型基本上等同于字符串类型，但不同的是，它一般用于采用特殊符号类型的语言。
- **Date：** 日期时间，用 UNIX 时间格式来存储当前日期或时间。你可以指定自己的日期时间：创建 Date 对象，传入年月日信息。
- **Object ID：** 对象 ID，用于创建文档的 ID。
- **Binary Data：** 二进制数据，用于存储二进制数据。
- **Code：** 代码类型，用于在文档中存储 JavaScript 代码。
- **Regular expression：** 正则表达式类型，用于存储正则表达式。

## 安装配置

### 安装（Mac版本）

```shell
# 安装
brew tap mongodb/brew
brew install mongodb-community
```

安装完成日志

```shell
==> Summary
🍺  /opt/homebrew/Cellar/mongodb-community/7.0.2: 11 files, 265.6MB, built in 5 seconds
==> Running `brew cleanup mongodb-community`...
Disable this behaviour by setting HOMEBREW_NO_INSTALL_CLEANUP.
Hide these hints with HOMEBREW_NO_ENV_HINTS (see `man brew`).
==> Caveats
==> mongodb-community
To start mongodb/brew/mongodb-community now and restart at login:
  brew services start mongodb/brew/mongodb-community
```

安装位置

```shell
# 配置文件
/opt/homebrew/etc/mongod.conf

#日志文件
/opt/homebrew/var/log/mongodb

# 数据目录
/opt/homebrew/var/mongodb
```

验证是否安装成功

```shell
mongod --versioin

db version v7.0.2
Build Info: {
    "version": "7.0.2",
    "gitVersion": "02b3c655e1302209ef046da6ba3ef6749dd0b62a",
    "modules": [],
    "allocator": "system",
    "environment": {
        "distarch": "aarch64",
        "target_arch": "aarch64"
    }
}
```

启动服务

```shell
# 启动
brew services start mongodb-community
#停止服务
brew services stop mongodb-community
#重启服务
brew services restart mongodb-community

#查看服务运行列表
brew services list
```

- mongo启动报错Error: uninitialized constant Homebrew::Service::System (https://www.tiven.cn/p/ae4f47d7/)

连接数据库

```shell
mongosh 

---
Current Mongosh Log ID:	65c316e9c36c2871ddfa87fd
Connecting to:		mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.1.3
(node:39098) [DEP0040] DeprecationWarning: The `punycode` module is deprecated. Please use a userland alternative instead.
(Use `node --trace-deprecation ...` to show where the warning was created)
Using MongoDB:		7.0.2
Using Mongosh:		2.1.3

For mongosh info see: https://docs.mongodb.com/mongodb-shell/


To help improve our products, anonymous usage data is collected and sent to MongoDB periodically (https://www.mongodb.com/legal/privacy-policy).
You can opt-out by running the disableTelemetry() command.

------
   The server generated these startup warnings when booting
   2024-02-07T13:36:20.447+08:00: Access control is not enabled for the database. Read and write access to data and configuration is unrestricted
------
```





## 常用命令

### 基本命令

打开并连接到本地实例的MongoShell。所有的其他命令都需要在mongosh中执行

```
mongosh
```

显示当前MongoDB实例中的所有数据库

```shell
show databases 
show dbs
```

切换数据库

```shelll
use <dbname>
```

显示当前数据库中所有集合

```
show collections
```

删除当前数据库

```shell
db.dropDatabase()
```

退出mongosh会话

```shell
exit
```

### 创建/插入

#### insertOne

在集合中插入一个新的文档，返回true表示插入成功，并且返回插入成功这条数据的id。

```shell
db.users.insertOne({"name":"张三"})
{
  acknowledged: true,
  insertedId: ObjectId('65c32fafc36c2871ddfa87fe')
}
```



#### insertMany

在集合中插入多个文档，返回true表示插入成功，并且返回插入成功数据的id。

```shell
db.users.insertMany([{"name":"李四","age":18},{"name":"王五","age":20}])
{
  acknowledged: true,
  insertedIds: {
    '0': ObjectId('65c3305ac36c2871ddfa87ff'),
    '1': ObjectId('65c3305ac36c2871ddfa8800')
  }
}
```



### 查找

#### find

查询所有文档

```shell
db.users.find()
[
  { _id: ObjectId('65c32fafc36c2871ddfa87fe'), name: '张三' },
  { _id: ObjectId('65c3305ac36c2871ddfa87ff'), name: '李四', age: 18 },
  { _id: ObjectId('65c3305ac36c2871ddfa8800'), name: '王五', age: 20 }
]
```

#### find(\<filterObject\>)

查询所有满足参数对象(\<filterObject\>)中指定过滤条件的数据

```shell
db.users.find({"name":"张三"})
[ { _id: ObjectId('65c32fafc36c2871ddfa87fe'), name: '张三' } ]
```

#### db.find(\<filterObject\>, \<selectObject\>)

查询所有满足参数对象(\<filterObject\>)中指定过滤条件的数据，并且只返回\<selectObject\>中指定的字段

```shell
db.users.find({"name":"李四"},{name:1})
[ { _id: ObjectId('65c3305ac36c2871ddfa87ff'), name: '李四' } ]
```

#### findOne

与find用法相同，找到满足过滤条件的对象，但是只返回第一条

```shell
db.users.findOne({level:1})
```

#### countDocument

返回满足条件的记录的数量

```shell
db.users.countDocuments()
db.collection.countDocuments(query, options)
```



#### sort

使用给定的字段按照升序或者讲序来排序 ，1是升序，-1是降序。还可以通过limit限制返回数量

```shell
db.users.find().sort({age:-1}).limit(2)
[
  { _id: ObjectId('65c3305ac36c2871ddfa8800'), name: '王五', age: 20 },
  { _id: ObjectId('65c3305ac36c2871ddfa87ff'), name: '李四', age: 18 }
]

##查询大于等于18的用户
db.users.find({age:{$gte:18}})
[
  { _id: ObjectId('65c3305ac36c2871ddfa87ff'), name: '李四', age: 18 },
  { _id: ObjectId('65c3305ac36c2871ddfa8800'), name: '王五', age: 20 }
]
```

#### limit

限定返回给定文档的数量

```shell
db.users.find().limit(1)
[ { _id: ObjectId('65c32fafc36c2871ddfa87fe'), name: '张三' } ]
```

#### skip

跳过一些查询结果，skip(1) 跳过第一条数据从第二条开始返回

```shell
db.users.find().sort({age:-1}).limit(2).skip(1)
[
  { _id: ObjectId('65c3305ac36c2871ddfa87ff'), name: '李四', age: 18 },
  { _id: ObjectId('65c32fafc36c2871ddfa87fe'), name: '张三' }
]
```

#### find({field:value})

条件查询

```shell
db.users.find({"name":"张三"})
[ { _id: ObjectId('65c32fafc36c2871ddfa87fe'), name: '张三' } ]
db.users.find({"age":20})
[ { _id: ObjectId('65c3305ac36c2871ddfa8800'), name: '王五', age: 20 } ]
```
