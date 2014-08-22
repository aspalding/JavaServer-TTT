require 'web'
require 'board'

describe Web do
  let(:b) {Board.new(3)}
  let(:w) {Web.new(b)}

  it "returns a pretty board" do
    pretty_board = "<h1></br>- | - | -</br>- | - | -</br>- | - | -</br></br></h1>" 
    w.show_board.should eq(pretty_board)
  end
  
  it "puts a move on the board" do
    w.human_move("x", 0)
    b.board[0].should eq("x") 
  end
  
  #it "puts an ai move on the board" do
  #  w.ai_move("o")
  #  (b.board.include? "o").should eq(true)
  #end
  
  it "returns an invalid move if move cannot be cast" do 
    w.cast_int("a").should eq(-1)
  end

  it "returns the status of a game over" do
    w.game_over?.should eq(false)
  end

  it "returns the message to be displayed for winner" do
    class Tss_board < Board
      def initialize()
        @board = ["x", "x", "x", "-", "-", "-", "-", "-", "-"]
      end
    end
    board = Tss_board.new
    web = Web.new(board)
    web.over_message.should eq("x is the winner!")
  end


  it "returns the message to be displayed for tie" do
    class Tss_board < Board
      def initialize()
        @board = ["x", "o", "x", "x", "o", "x", "o", "x", "o"]
      end
    end
    board = Tss_board.new
    web = Web.new(board)
    web.over_message.should eq("It's a tie!")
  end
  
end
