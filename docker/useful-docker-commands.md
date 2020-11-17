#### Start Docker container with docker-compose in Daemon mode:
$ docker-compose up -d

#### Stop Docker container:
$ docker-compose down

#### This will take care of taking down the unused volumes and images.
$ docker-compose down --remove-orphans

#### Delete all docker data. Images, volumes, containers and networks.
$ docker system prune -a
$ docker volume prune

#### Run specific image inside a docker-compose.yml, for example for mysql image.
$ docker-compose up --no-start && docker-compose start mysql && docker-compose logs -f mysql


