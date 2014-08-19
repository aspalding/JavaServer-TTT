require 'web'
require 'board'

describe Web do
  let(:b) {Board.new(3)}
  let(:w) {Web.new(b)}

  it "returns a pretty board" do
    pretty_board = "\n- | - | -\n- | - | -\n- | - | -\n\n" 
    w.show_board.should eq(pretty_board)
  end
  
  it "puts a move on the board" do
    w.human_move("x", 0)
    b.board[0].should eq("x") 
  end
  
  it "returns an invalid move if move cannot be cast" do 
    w.cast_int("a").should eq(-1)
  end
  
end