NAME_IMAGE=lab-a01-app-repository-payment
NAME_REPO=williamreges

echo "Docker RUN Local Network HOST"
docker run -p 8000:8000 \
--net=host \
--name ${NAME_REPO}/${NAME_IMAGE} \
--rm ${NAME_REPO}/${NAME_IMAGE}
