class Board
  attr_reader :board

  def initialize(n)
    @board = Array.new(n*n, "-")
  end

  def place(mark, loc)
    @board[loc] = mark if valid?(loc)
    @board
  end

  def valid?(loc)
    @board[loc] == "-" && loc > -1 && loc < @board.length
  end

  def value_board
    @board.clone
  end

end
