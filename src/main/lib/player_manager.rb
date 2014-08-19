require 'ai'

class Player_manager
  attr_reader :first, :second

  def initialize(first, ai_one, second, ai_two)
    @first = first
    @second = second

    @players = {}
    if ai_one == true
      @players[@first] = Ai.new(@first, self)
    else
      @players[@first] = "human"
    end

    if ai_two == true
      @players[@second] = Ai.new(@second, self)
    else
      @players[@second] = "human"
    end
  end
  
  def other_mark(mark)
    if mark == @first
      @second
    else
      @first
    end
  end

  def get_player(mark)
    @players[mark]
  end
end
