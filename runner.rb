include Java
require './assets/TicTacServer.jar'

#Java::Application.new.setUp(ARGV.to_java(:String))
#Java::Application::runLoop

Java::TicTacApp::run(ARGV.to_java(:String))
