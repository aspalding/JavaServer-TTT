require_relative '/Users/andrew/Documents/JavaServer-TTT/src/main/lib/board.rb'
require_relative '/Users/andrew/Documents/JavaServer-TTT/src/main/lib/ai.rb'
require_relative '/Users/andrew/Documents/JavaServer-TTT/src/main/lib/state.rb'


class Web
  def initialize(board)
    @manager = Player_manager.new("x", false, "o", true)
    @ai = Ai.new("o", @manager)
    @board = board
  end

  def show_board
      "</br>%s | %s | %s</br>%s | %s | %s</br>%s | %s | %s</br></br>" % @board.board
  end

  def human_move(mark, loc)
    @board.place(mark, cast_int(loc))
  end
  
  def ai_move(mark)
    loc = @ai.smart_move(@board.board)
    @board.place(mark, loc)
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