class IoFake
  def initialize(some_input = "")
    @some_input = some_input
  end

  def output(text)
    text
  end

  def input
    @some_input
  end
end

class IoProd
  def output(text)
    puts text
  end

  def input
    gets.chomp
  end
end
