VERSION=V1
NAME_IMAGE=lab-a01-app-repository-payment
NAME_REPO=williamreges


echo "=== Tag Image ===="
docker tag ${NAME_IMAGE} ${NAME_REPO}/${NAME_IMAGE}:${VERSION}
docker tag ${NAME_IMAGE} ${NAME_REPO}/${NAME_IMAGE}

echo "=== PUSH version==="
docker push ${NAME_REPO}/${NAME_IMAGE}:${VERSION}

echo "=== PUSH Latest==="
docker push ${NAME_REPO}/${NAME_IMAGE}