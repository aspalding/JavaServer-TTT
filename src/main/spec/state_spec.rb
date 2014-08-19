require 'state'
require 'board'
require 'player_manager'

describe State do
  let(:b) {Board.new(3)}
  let(:m) {Player_manager.new("x", false, "o", true)}
  let(:s) {State.new(m)}

  it "returns indices of empty positions on board" do
    s.empty_indices(b.board).should eq([0, 1, 2, 3, 4, 5, 6, 7, 8])
  end

  it "won't return filled positions" do
    s.empty_indices(b.place("x", 0)).should eq([1, 2, 3, 4, 5, 6, 7, 8])
  end

  it "reports if the board is full" do
    board = ["x", "o","x","o","o","o","x","o","x"]
    s.full?(board).should eq(true)
  end

  it "won't if it isn't" do
    board = ["x", "o","x","o","x","o","-","-","-"]
    s.full?(board).should eq(false)
  end

  it "reports status in horizontal row" do
    board = ["x", "x", "x","-","-","-","-","-","-"]
    s.horizontal?(board, "x").should eq(true)
    board = ["-","-","-","-","-","-","x", "x", "x"]
    s.horizontal?(board, "x").should eq(true)
    board = ["-","-","-","-","-","-","o", "x", "x"]
    s.horizontal?(board, "x").should eq(false)
    board = ["-", "-", "-",
             "o", "x", "x",
             "-", "-", "-"]
    s.horizontal?(board, "x").should eq(false)
    board = ["o", "-", "o",
             "-", "x", "-", 
             "x", "-", "x"]
    s.horizontal?(board, "x").should eq(false)
  end

  it "reports status in vertical row" do
    board = ["x", "-", "-","x","-","-","x","-","-"]
    s.vertical?(board, "x").should eq(true)
    board = ["-", "-", "x","-","-","x","-","-","x"]
    s.vertical?(board, "x").should eq(true)
    board = ["x", "-", "-",
             "-", "x", "-",
             "x", "-", "-"]
    s.vertical?(board, "x").should eq(false) 
    board = ["o", "-", "o",
             "x", "o", "-", 
             "x", "-", "x"]
    s.vertical?(board, "x").should eq(false)
  end

  it "reports status in major diagonal row" do
    board = ["x", "-", "-",
             "-","x","-",
             "-","-","x"]
    s.diagonal_major?(board, "x").should eq(true)
    board = ["-", "-", "x",
             "-", "-", "x",
             "-" ,"-", "x"]
    s.diagonal_major?(board, "x").should eq(false)
    board = ["o", "-", "o",
             "-", "x", "-", 
             "x", "-", "x"]
    s.diagonal_major?(board, "x").should eq(false)

    board = ["x", "-", "-",
             "-","x","-",
             "-","-","-"]
    s.diagonal_major?(board, "x").should eq(false)
  end

  it "reports status in minor diagonal row" do
    board = ["-", "-", "x",
             "-", "x", "-",
             "x", "-", "-"]
    s.diagonal_minor?(board, "x").should eq(true)
    board = ["-", "-", "x",
             "-", "-", "x", 
             "-", "-", "x"]
    s.diagonal_minor?(board, "x").should eq(false)
    board = ["o", "-", "o",
             "-", "x", "-", 
             "x", "-", "x"]
    s.diagonal_minor?(board, "x").should eq(false)
    board = ["-", "-", "-",
             "-", "x", "-", 
             "x", "-", "o"]
    s.diagonal_minor?(board, "x").should eq(false)
  end

  it "reports winner" do
    board = ["x", "-", "-","-","x","-","-","-","x"]
    s.winner?(board, "x").should eq(true)
    board = ["x", "-", "-","x","-","-","x","-","-"]
    s.winner?(board, "x").should eq(true)
    board = ["x", "x", "x","-","-","-","-","-","-"]
    s.winner?(board, "x").should eq(true)
    board = ["o", "-", "o",
             "-", "x", "-", 
             "x", "-", "x"]
    s.winner?(board, "x").should eq(false)
  end

  it "reports end of game (win or draw)" do
    board = ["x", "-", "-","-","x","-","-","-","x"]
    s.terminal?(board).should eq(true)
    board = ["x", "o", "x","x","o","x","o","x","o"]
    s.terminal?(board).should eq(true)
  end

  it "reports if the board is a tie game" do
    board = ["x", "o","x","o","o","o","x","o","x"]
    s.tie?(board).should eq(false)
  end

  it "hmmm?" do
    board = ["x", "x", "o","x","o","o","x","-","-"]
    s.winner?(board, "x").should eq(true)
  end

  it "work the same 4x4" do
    board = ["x", "x", "x","x",
             "-", "-", "-", "-",
             "-", "-", "-", "-",
             "-", "-", "-", "-"]
    s.horizontal?(board, "x").should eq(true)
    board = ["x", "-", "-","-",
             "x", "-", "-", "-",
             "x", "-", "-", "-",
             "x", "-", "-", "-"]
    s.vertical?(board, "x").should eq(true)
    board = ["x", "-", "-","-",
             "-", "x", "-", "-",
             "-", "-", "x", "-",
             "-", "-", "-", "x"]
    s.diagonal_major?(board, "x").should eq(true)
    board = ["-", "-", "-","x",
             "-", "-", "x", "-",
             "-", "x", "-", "-",
             "x", "-", "-", "-"]
    s.diagonal_minor?(board, "x").should eq(true)
  end

end
