Liferay - Spring MVC - Powermock
=======================================

## Project's goal
This project shows how to test a simple Liferay Portlet, with Spring MVC, using PowerMock.

## Why Docker
Why not ? :) If you don't like it, you still have all the sources in the git repository.

More seriously, the use of Docker is purely intended to let you test the code without having to download the whole maven world, and Liferay dependencies. It has, for this project, no really other use I can think of.

## Very quick start
In a commande line terminal (having docker installed), simply run :

```
$> docker run -ti --rm afillatre/liferay-springmvc-powermock
-- In docker shell --
docker@docker#> mvn test
```
