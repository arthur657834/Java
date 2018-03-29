![ jprofiler](jprofiler.png)

### 远程监控tomcat

服务器安装配置:
```
JPROFILER_HOME=/opt/jprofiler10.1/bin/linux-x64
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$JPROFILER_HOME
```

本地安装配置:
```
1. 下载startup.sh脚本
2. 利用jprofiler生成startup_jprofiler.sh脚本
3. 上传startup_jprofiler.sh至bin下
4. 使用startup_jprofiler.sh启动tomcat
```


可以分析jmap dump出来的文件


