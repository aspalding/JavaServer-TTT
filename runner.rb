include Java
require './assets/SimpleServer.jar'

Java::Application.new.setUp(ARGV.to_java(:String))
Java::Application::runLoop