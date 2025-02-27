# buk-common

版本2.0将不与1.0系列兼容，在1.0中一些废弃的类将删除掉。


mvn release:perform -DuseReleaseProfile=src

发布时使用以上格式，不会生成javadoc
javadock出错的地方太多，无法改进。


## Maven 仓库推送
```
mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -DskipTests

## git
git config --global user.name "yfdai"
git config --global user.email "yfdai@buk.cn"
git config --global credential.helper store
git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=3600'