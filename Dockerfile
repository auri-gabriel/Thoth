FROM php:7.4-cli
COPY . /usr/src/thoth
WORKDIR /usr/src/thoth
CMD [ "php", "./index.php" ]
