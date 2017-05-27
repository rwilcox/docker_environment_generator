package com.wilcox

import sys.process._
import scopt._

object DockerEnvMain extends App {

    case class Config(
        mode: String = "",       
        packageName: String = ""
    )

    def buildParser : scopt.OptionParser[Config] = {
        val parser = new scopt.OptionParser[Config]("dockerenv") {
            head("docker env tester / builder")

            cmd("print").action ((_, c) => c.copy(mode="print")).text("downloads Docker container and prints how you would run it") 

            arg[String]("<docker pakage name>").action( (inVal, c) => c.copy(packageName=inVal)).text("Docker container path from Docker hub")
        }

        parser
    }

    def doPrint(config: Config) = {
      s"docker pull ${config.packageName}" !;
      println(s"NOW:\tdocker run -i -t ${config.packageName} /bin/bash")   // no TTY, punt and show user how
    }

    override def main( args: Array[String] ): Unit = {
      val parser = buildParser

      parser.parse(args, Config()) match {
          case Some(config) => {
              if (config.mode == "print") doPrint(config)
          }
          case None => println("Please use --help argument for usage instructions")
      }
    }
}



