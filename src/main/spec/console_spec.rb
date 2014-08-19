require 'board'
require 'console'
require 'player_manager'
require 'io_helper'

describe Console do
  let(:b) {Board.new(3)}
  let(:c) {Console.new(IoFake.new)}

  it "shows a pretty board" do
    io = IoFake.new(3)
    con = Console.new(io)
    con.get_size
    pretty_board = "\n- | - | -\n- | - | -\n- | - | -\n\n"
    con.show_board.should eq(pretty_board)

    io = IoFake.new(4)
    con = Console.new(io)
    con.get_size
    pretty_board = "\n- | - | - | -\n- | - | - | -\n- | - | - | -\n- | - | - | -\n\n" 
    con.show_board.should eq(pretty_board)
  end
  
  it "returns true if user wants to play another game" do
    io = IoFake.new("y")
    con = Console.new(io)
    con.again?.should eq(true)
  end

  it "spits out an invalid integer if user inputs bogus value" do
    c.cast_int("jlsfd;l").should eq(-1)
    c.cast_int("").should eq(-1)
  end

end
