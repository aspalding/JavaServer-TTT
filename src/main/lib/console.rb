require 'state'
require 'ai'
require 'player_manager'
require 'io_helper'

class Console

  def initialize(io)
    @io = io
  end

  def show_board
    if @board.board.size == 9
      "\n%s | %s | %s\n%s | %s | %s\n%s | %s | %s\n\n" % @board.board
    else
      "\n%s | %s | %s | %s\n%s | %s | %s | %s\n%s | %s | %s | %s\n%s | %s | %s | %s\n\n" % @board.board
    end
  end

  def human_move(mark)
    @io.output "Enter a valid move (0..8)"
    location = cast_int(gets.chomp)
    if @board.valid?(location)
      @board.place(mark, location)
    else
      human_move(mark)
    end
  end
  
  def get_player
    @io.output "enter your mark"
    mark = @io.input[0]
    @io.output "is this player the computer? (y/n)"
    ai = true if @io.input[0] == "y"
    [mark, ai]
  end

  def get_size
    @io.output "Size of board? (3/4)"
    size = cast_int(@io.input)
    if size == 4
      @board = Board.new(size)
    else
      @board = Board.new(3)
    end
  end

  def cast_int(loc)
    begin
      location = Integer(loc)
    rescue ArgumentError
      loc = -1
    end
  end

  def game_over(mark, state)
    if state.winner?(@board.board, mark)
      @io.output show_board
      @io.output mark + " is the winner!"
    else
      @io.output show_board
      @io.output "Tie game!"
    end
  end

  def which_turn(mark, manager)
    if manager.get_player(mark) == "human"
      human_move(mark)
    else
      ai = manager.get_player(mark)
      ai.smart_move(@board.value_board)
      @board.place(ai.mark, ai.choice)
    end 
  end

  def again?
    @io.output "play again? (y/n)"
    true if @io.input[0] == "y"
  end

  def game_loop(mark, manager)
    state = State.new(manager)
    if state.terminal?(@board.board)
      game_over(manager.other_mark(mark), state)
    else
      @io.output show_board
      which_turn(mark, manager)
      game_loop(manager.other_mark(mark), manager)
    end
  end

  def run(manager)
    game_loop(manager.first, manager)
    if again?
      size = Math.sqrt(@board.board.size)
      @board = Board.new(size)
      run(manager)
    else
      exit
    end
  end
end
