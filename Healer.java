package TEXT_RPG;

public class Healer extends Player2ndLevel {
	
	Healer(String name) {
		super(name);
		this.skillMp = 10;
	}

	@Override
	void skill(Creature[] targetArr) {
		for (int i = 0; i < targetArr.length; i++) {
			healType(targetArr[i]);
		}
		System.out.println("힐러 " + this.name + " 유저가 회복을 진행합니다.");
	
	}

	@Override
	public String toString() {
		return "Healer [skillMp=" + skillMp + ", name=" + name + ", mp=" + mp + ", str=" + str + ", hp=" + hp
				+ ", type=" + type + "]";
	}

	@Override
	void skill(Creature target) {
	}
	
}