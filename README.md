1，字符串对象：
1）append msg " again"
在msg对应的字符串后面再添加“ again"，即追加修改


2，列表对象：
1）添加  rpush alphabet a b c
追加： rpush alphabet a
2）lrange alphabet 0 -1
查询所有的列表元素
 lrange alphabet 1 2
查询索引1 ~ 2位置的元素
3）lindex alphabet 2
返回列表中的第3个元素
3）lrem (list remove) 
从列表头部开始删除值等于value的元素count次
LREM key count value
count>0:从表头开始向表尾搜索，移除与value相等的元素，数量为count
count<0:从表尾开始向表头搜索，移除与value相等的元素，数量为count的决定值
count=0:移除表中所有value相等的值

3，集合对象：
1）创建: sadd fruits apple banana cherry
添加 sadd fruits "orange"
2) 遍历查询：smembers fruits
获取fruits hashtable中的所有键值

4,有序列表
1) zrank price cherry
查看cherry在有序列表price中的排名
2）zrange price 0 2
查看有序列表price中索引为0 ~ 2的元素有哪些
3) 创建有序列表fruits： zadd fruits 0.5 apple 0.6 banana
添加元素: zadd fruits 0.8 orange

5, hash对象：
1）添加值：hset book name "testsss"
为book对象添加name属性
修改也可以使用这个命令，执行后如果返回0表示修改，如果返回1表示添加

6，object idletime msg
msg键的空转时长（当前时间与最后一次被访问的时间差）
idletime命令比较特殊，因为它不会修改lru（最后一次被访问的时间) 属性值

7，命令: info stats
keyspace_hits:177
keyspace_misses:2
键空间的命中次数与不命中次数

8,过期时间
1) 设置过期时间（单元秒): expire key 5-》5秒后过期
单元毫秒: pexpire key 5000 -> 5000 毫秒后过期
pexpireat message 1391234400000 
pexpireat <key> <timestamp> 用于将键key的过期时间设置为timestamp所指定的毫秒数时间戳
2）TTL和PTTL：返回这个键的剩余生存时间（返回-1表示键没有设置过期时间），不能可用于判定键是否过期
TTL key-》 key剩余的生存时间（秒）
3)  persist <key> 解除键和值（过期时间）在过期字典中的关联，和pexpireat作用相反，设置为不过期
