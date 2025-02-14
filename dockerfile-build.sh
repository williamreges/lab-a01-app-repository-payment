NAME_IMAGE=$(cat ./pom.xml | grep "<name>[a-zA-Z0-9]*" | sed "s/<name>//g" | sed "s/<\/name>//g" |  sed -e 's/^[ \t]*//')
NAME_REPO=williamreges
./mvnw clean install -Dtest.Skip=true

echo "=== Build Image Version ===="
docker build -t ${NAME_REPO}/${NAME_IMAGE} .


