sudo docker swarm init || true
sudo docker stack deploy -c docker-compose-postgres.yml post
