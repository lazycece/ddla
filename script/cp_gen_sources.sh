#
#    Copyright 2026 lazycece<lazycece@gmail.com>
#
#    Licensed under the Apache License, Version 2.0 (the "License");
#    you may not use this file except in compliance with the License.
#    You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.
#

cd ../

# 假设当前在 ddla 仓库根
GEN=ddla-samples/tradecore/target/generated-sources/archetype

# 1. 清掉生成目录里的垃圾
rm -rf $GEN/src/main/resources/archetype-resources/.idea
rm -f  $GEN/src/main/resources/archetype-resources/*.iml
rm -rf $GEN/src/main/resources/archetype-resources/target

# 2. 用 cp 复制（排除 pom.xml，保留 ddla-archetype 原有的）
cp -r $GEN/src/ ddla-archetype/

mvn clean