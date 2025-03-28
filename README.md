### Description

This is project about learning words on method of interval learning. Project use SM-2 algorithm.

Technologies used in project:

* Backend: java spring boot, docker, docker compose

* Frontend: React, Bootstrap

You can run application with following commands:

Clone project

```shell
git clone https://github.com/EgorUgchv/flashCardsJava.git
```

Go to project directory and execute command. To execute this command, you need to install [docker](https://docs.docker.com/engine/install/) and [docker-compose](https://docs.docker.com/compose/install/).
This command runs the `docker-compose.yml` file locate in the root directory of the project.  

```shell
docker compose up -d
```

Then you can go to `http:localhost:80/add-cards` and input data.

![add-cards Page](/img/add-cardsPage.png)

After click on button `Создать` you redirect on page where you can see your flashcards.

![cards Page](/img/cardsPage.png)

