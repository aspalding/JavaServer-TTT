include Java
require './jars/JavaServer.jar'

Java::Application::runLoop(ARGV.to_java(:string));
