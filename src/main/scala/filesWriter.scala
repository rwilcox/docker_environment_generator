package com.wilcox

import java.nio.file._
import java.nio.charset._
import java.io.PrintWriter

class FilesWriter(containerName : String) {

  def write(parentPath : Path) {
    val destinationFolder : Path = parentPath.relativize( Paths.get("./docker/application"))
    destinationFolder.toFile().mkdirs()
    
    val dockerfileContent = s"""FROM ${containerName}\nCMD ["tail", "-f", "/dev/null"]"""
    val dockerFilePath = destinationFolder.resolve( Paths.get("Dockerfile") )

    //println( dockerFilePath.toString())
    val writer = new PrintWriter( dockerFilePath.toFile(), "UTF-8" )
    writer.write( dockerfileContent )
    writer.close()

    val dockerComposeFileContent = s"""version: '2'
services:
  application:
    build:
      dockerfile: docker/application/Dockerfile
      context: .
"""

    val dockerComposeWriter = new PrintWriter( parentPath.resolve("docker-compose.yml").toFile(), "UTF-8" )
    dockerComposeWriter.write( dockerComposeFileContent )
    dockerComposeWriter.close()

    println("To start created containers:")
    println("$ docker-compose up -d")

    println("\nTo ssh into created container:")
    println("$ docker-compose attach application /bin/bash")
  }

}
