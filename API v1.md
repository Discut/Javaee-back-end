A - 管理员
B - 行政
C - 教师
D - 家长/学生

第一次会议记录6.2
基础url：/api/v1/
公共返回
```json
{
    "status": 200,
    "message": "登录成功",
    "data":数据,
    "date": "2023-06-01 16:51:57",
    "path": ""
}
```

## 公共方法

### 上传图片

/file/image/add

method: post

+ requst



+ response

```json
{
    uuid: 图片uuid
}
```



### 获取图片流

/file/image/{uuid}

method: get

+ response

FileStream

### 生成二维码 AB

/file/qrcode

method: post

- request（body）

```json
{
    title: "二维码标题",
    content: "二维码内容",
    footer: "二维码脚页"
}
```

+ response

```json
{
    uuid: "图片uuid"
}
```



## 成长轨迹

### 德智体美劳 -- 自定义评价平移到其它-- 数据库6标识？？？

### 成绩模块 - 后端转换为ABCD

#### 获取学生成绩列表 /score/student/list/{class}  - AB C（校验班级）

method：get

| 路径变量名 | 备注                                      |
| ---------- | ----------------------------------------- |
| class      | -1 代表查询所有；class>0代表根据class查询 |

- request（query）
| 字段 | 备注 |
| --- | --- |
| index | 页面索引 |
| limit | 页面大小 |
|  |  |

- response
```json
{
  limit:页面大小,
  index:当前页,
  size: 总页数,
	content:[...Object]
}
```


#### 直接获取某个学生所有成绩 - D

/score/self/{account}

method：get

| 路径变量名 | 备注     |
| ---------- | -------- |
| account    | 学生账号 |

+ response

```json
[...Object]
```

```json
[
    {
        title: "第一次全体考试"
        math: 12,
        
    }
]
```



### 获奖模块

#### 红领巾争章

> 类型：红星章、红旗章

##### 获取红领巾争章的奖状类型 ALL

get

/prize/type/list

+ response

```json
[
    {
        value: "奖状类型id",
        label: "奖状名称"
    },
    ...
]
```

##### 创建自定义奖状类型 AC



/prize/type/add

method: post

+ request

```json
{
    label: "待创建奖状名称"
}
```

##### 奖状授予 ABC

/prize/award/{account}

method: post

+ request

```json
{
    awardId: "奖状类型id",
    awardName: "奖状名称   eg.二零二二年第一次红星章评比",
    awardScore: 90  "奖状分数，Number"
}
```

#### 查询某学生获取的所有奖状 	AB   CD

/prize/student/{account}

| 路径变量名 | 备注     |
| ---------- | -------- |
| account    | 学生账号 |

+ response

```json
{
  limit:页面大小,
  index:当前页,
  size: 总页数,
	content:[
        {
            type: "奖状类型",
       		 name: "奖状名称",
            score:"奖状分数"
    }...]
}
```



### 活动管理

#### 创建活动 A C

/activity/put

method:post

+ request

```json
{
    title: "活动标题",
    startTime: "开始时间，eg.2020-11-11",
    endTime: "结束时间里, eg.2021-11-11",
    content: "富文本内容",
    subject: "活动执行班级, id"
}
```

### 修改活动 A C

/activity/modify/{activityId}

method: post

+ request

```json
{
    startTime: "活动开始时间",
    endTime: "活动结束时间",
    content: "富文本内容",
    endContent: "结束活动富文本内容",
}
```





#### 获取活动列表 AB C

/activity/list/{className}

| 路径变量名 | 备注                                      |
| ---------- | ----------------------------------------- |
| className  | -1 代表查询所有；class>0代表根据class查询 |

method: get

+ request



+ response

```json
{
  	limit:页面大小,
  	index:当前页,
    size: 总页数,
	content:[
        {
            id: "活动id",
            title: "活动标题",
            startTime: "活动开始时间",
            endTime: "活动结束时间",
            content: "富文本内容",
            endContent: "结束活动富文本内容",
            subject: "活动班级"
        }
    ]
}
```

#### 根据活动id获取活动内容

/activity/info/{{activityId}}

method: get

+ request

空

+ response

```json
{
    id: "活动id",
    title: "活动标题",
    startTime: "活动开始时间 eg.2022-1-1",
    endTime: "活动结束时间",
    content: "富文本内容",
    endContent: "结束活动富文本内容",
    subject: "活动班级"
}
```



### 编辑管理

#### 设置班级二维码

/clazz/qrcode/{{classId}}

method: post

+ requst

```json
{
    qruuid: "二维码uuid"
}
```

