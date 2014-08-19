require 'state'
require 'player_manager'

class Ai
  attr_reader :mark, :choice

  def initialize(mark, manager)
    @mark = mark
    @state = State.new(manager)
    @manager = manager
  end
  
  def score(board, mark)
    if @state.winner?(board, mark) || @state.winner?(board, @manager.other_mark(mark))
      -10
    else
      0
    end  
  end
  
  def score_moves(board, mark, depth, scores)
    return score(board, mark) if @state.terminal?(board) || (board.size == 16 && depth == 2)
    @state.empty_indices(board).each do |move|
      board[move] = mark
      scores[move] = -1 * score_moves(board, @manager.other_mark(mark), depth + 1, Hash.new)
      board[move] = "-"
    end
    best_score = scores.values.max
    depth == 0 ? scores : best_score
  end
  
  def smart_move(board)
    @choice = score_moves(board, @mark, 0, Hash.new).max_by{ |k, v| v }[0]
  end

end
