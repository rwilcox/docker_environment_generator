#!/usr/bin/env scala 

import sys.process._

object DockerEnvMain {

    def main( args: Array[String] ): Unit = {
        s"docker pull ${args(0)}" !;
        println(s"NOW:\tdocker run -i -t ${args(0)} /bin/bash")   // no TTY, punt and show user how
    }

}