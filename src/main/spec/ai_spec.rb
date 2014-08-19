require 'state'
require 'ai'
require 'player_manager'

describe Ai do
  let(:m) {Player_manager.new("x", false, "o", true)}
  let(:a) {Ai.new("o", m)}

  it "scores all games -10" do
    board = ["o", "o", "o","-","-","-","-","-","-"]
    a.score(board, "o").should eq(-10)
    
    board = ["x", "x", "x","-","-","-","-","-","-"]
    a.score(board, "o").should eq(-10)
  end


  it "doesn't score a draw a -10" do
    board = ["x", "o", "x",
             "o", "-", "-",
             "x", "-", "o"]
    a.score(board, "o").should eq(0)

    board = ["x", "o", "x",
             "o", "-", "-",
             "x", "-", "o"]
    a.score(board, "x").should eq(0)
  end

  it "scores a draw 0" do
    board = ["x", "o", "x","x","o","o","o","x","x"]
    a.score(board, "o").should eq(0)
  end

  it "if game is over, return the score for that branch" do
    board = ["o", "o", "o","-","-","-","-","-","-"]
    a.score(board, "o").should eq(-10)
    board = ["x", "x", "x","-","-","-","-","-","-"]
    a.score(board, "o").should eq(-10)
    board = ["x", "o", "x","x","o","o","o","x","x"]
    a.score(board, "o").should eq(0)
  end

  it "should take the correct move" do
    board = ["x", "x", "-","-","o","-","-","-","-"]
    a.smart_move(board)
    a.choice.should eq(2)

    board = ["o", "o", "x","-","-","-","x","-","-"]
    a.smart_move(board)
    a.choice.should eq(4)

    board = ["-", "-", "o","-","o","x","x","-","x"]
    a.smart_move(board)
    a.choice.should eq(7)

    board = ["x", "x", "o","-","o","-","x","-","-"]
    a.smart_move(board)
    a.choice.should eq(3)
  end
  
  it "should block a fork" do
    board = ["-", "-", "-",
             "-", "x", "-",
             "x", "-", "o"]
    a.smart_move(board)
    a.choice.should eq(2)
  end

  it "another fork" do
    board = ["-", "-", "x",
             "-", "x", "-",
             "o", "-", "-"]
    a.smart_move(board)
    [0, 8].should include(a.choice)
  end

  it "another fork" do
    board = ["x", "o", "x",
             "-", "-", "-",
             "-", "-", "-"]
    a.smart_move(board)
    [4, 6, 8].should include(a.choice)
  end 

end
