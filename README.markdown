Docker Environment Generator
==============================

I start every project of mine off with Docker and Docker Compose. I create a Docker container for my application, based on some Docker Hub base image. (Because of this abstraction I can easily swap out the base image, without disrupting workflow).

This means every project has two steps:

  1. "Hey, is this Docker image any good? Does it have the programs I need installed in it?"
  2. "Ok, awesome, I found one that works, now I need a compose file and a Docker file"

This tool takes care of both those concerns, allowing quick testing of Docker images, AND a write command to generate the required files.

    $ $dockEnvJar print ruby

Will download the Ruby DockerHub image, and give you instructions to attach to the image.

or

    $ $dockerEnvJar write ruby
Writes Dockerfile and docker compose file for this image

About Scala
====================

This is my introduction to Scala project, the first 100 or so lines of code I've written in the language.
