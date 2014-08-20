require_relative '/Users/andrew/Documents/JavaServer-TTT/src/main/lib/board.rb'

class Web
  def initialize(board)
    @board = board
  end

  def show_board
      "</br>%s | %s | %s</br>%s | %s | %s</br>%s | %s | %s</br></br>" % @board.board
  end

  def human_move(mark, loc)
    puts "Enter a valid move (0..8)"
    @board.place(mark, cast_int(loc))
  end

  def cast_int(loc)
    begin
      location = Integer(loc)
    rescue ArgumentError
      loc = -1
    end
  end
end

Web.new(Board.new(3))