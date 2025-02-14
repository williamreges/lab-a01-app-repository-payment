NAME_IMAGE=$(cat ./pom.xml | grep "<name>[a-zA-Z0-9]*" | sed "s/<name>//g" | sed "s/<\/name>//g" |  sed -e 's/^[ \t]*//')
NAME_REPO=williamreges

echo "Docker RUN Local Network HOST"
docker run -p 8000:8000 \
--net=host \
--name ${NAME_REPO}/${NAME_IMAGE} \
--rm ${NAME_REPO}/${NAME_IMAGE}
