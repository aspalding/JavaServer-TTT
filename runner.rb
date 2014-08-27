include Java
require './assets/SimpleServer.jar'
require './assets/TicTacServer.jar'

Java::TicTacApp::initializeGames
Java::Application.new.setUp(ARGV.to_java(:String))
Java::Application::runLoop(Java::TicTacToeRouter.new)
