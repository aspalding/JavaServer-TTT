require 'player_manager'

describe Player_manager do
  let(:p) {Player_manager.new("x", false, "o", true)}
  
  it "reports opposing player" do
    p.other_mark("x").should eq("o")
  end

  it "reports opposing player" do
    p.other_mark("x").should eq("o")
  end

  it "knows if player is ai or not" do
    p.get_player("x").should eq("human") 
    player = p.get_player("o")
    player.class.should eq(Ai)
  end
  
end
