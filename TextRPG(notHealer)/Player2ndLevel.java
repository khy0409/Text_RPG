package TEXT_RPG;

public abstract class Player2ndLevel extends Player{

	int skillMp;
	
	Player2ndLevel(String name) {
		super(name);
	}
	
	abstract void skill(Creature target);
	abstract void buffSkill(Creature target);
	
	void healAll(Creature[] party) {
		for (int i = 0; i < party.length; i++) {
			if(true) {
			healType(party[i]);
			}
		}
	}

	void decreaseHpTypeHitBySkill(Creature target, double bonus) {
		if(target.hp >= (int)((int)(this.str * 1.5) * bonus)) {
			target.hp -= (int)((int)(this.str * 1.5) * bonus);
		} else {
			target.hp = 0;
		}
	}

	void decreaseHpBySkill(Creature target, double bonus) {
		if (target.hp >= (int)(this.str * bonus)) {
			target.hp -= (int)(this.str * bonus);
		} else {
			target.hp = 0;
		}
	}

	@Override
	public String toString() {
		return "Player2ndLevel [skillMp=" + skillMp + ", name=" + name + ", mp=" + mp + ", str=" + str + ", hp=" + hp
				+ ", type=" + type + "]";
	}	
}