VERSION=V3
NAME_IMAGE=$(cat ./pom.xml | grep "<name>[a-zA-Z0-9]*" | sed "s/<name>//g" | sed "s/<\/name>//g" |  sed -e 's/^[ \t]*//')
NAME_REPO=williamreges

echo "Versão Atual: ${VERSION}. Informe uma nova versão: "
read NEW_VERSION

if [ $NEW_VERSION  ]; then

    NEW_VERSION=$(echo $NEW_VERSION | tr "[:lower:]" "[:upper:]");

    echo "=== Tag Image ===="
    docker tag ${NAME_REPO}/${NAME_IMAGE} ${NAME_REPO}/${NAME_IMAGE}:${NEW_VERSION}

    echo "=== PUSH version==="
    docker push ${NAME_REPO}/${NAME_IMAGE}:${NEW_VERSION}

    echo "=== PUSH Latest==="
    docker push ${NAME_REPO}/${NAME_IMAGE}

    echo "Versão atualizada: ${NEW_VERSION}"
    sed -i  "s/${VERSION}/${NEW_VERSION}/g"  dockerfile-push.sh
fi