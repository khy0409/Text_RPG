package TEXT_RPG;

public class Healer extends Player2ndLevel {
	
	Healer(String name) {
		super(name);
		this.skillMp = 10;
	}

	@Override
	void skill(Creature targetArr) {
		healType(targetArr);
		if(this.mp >= 10) {
			this.mp -= 10;
		}else {
			this.mp = 0;
		}
		
	}
	
	@Override
	public String toString() {
		return "Healer [skillMp=" + skillMp + ", name=" + name + ", mp=" + mp + ", str=" + str + ", hp=" + hp
				+ ", type=" + type + "]";
	}
	@Override
	void buffSkill(Creature target) {
	}
}