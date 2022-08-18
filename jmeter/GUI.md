# 使用 Jmeter 的 GUI 做压力测试

```
HEAP="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m"
jmeter -n -t [jmx file] -l [results file] -e -o [Path to web report folder]
```