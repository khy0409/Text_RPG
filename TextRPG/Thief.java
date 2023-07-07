package TEXT_RPG;

public class Thief extends Player2ndLevel {
	public Thief(String name) {
		super(name);
		this.skillMp = 20;
	}

	@Override
	void skill(Creature target) {
		boolean typeHit = TypeCheck(this.type, target.type);
		this.mp -= skillMp;
		
		if(typeHit)
			decreaseHpTypeHitBySkill(target, 2);
		else
			decreaseHpBySkill(target, 2);
		
		String hitInfo = typeHit ? "[Type Hit!] " : "";
		System.out.println(hitInfo + "도적 " + this.name + " 유저가 몬스터를 기습했습니다");

	}

	@Override
	public String toString() {
		return "Thief [skillMp=" + skillMp + ", name=" + name + ", mp=" + mp + ", str=" + str + ", hp=" + hp + ", type="
				+ type + "]";
	}

	@Override
	void buffSkill(Creature target) {
		boolean typeHit = TypeCheck(this.type, target.type);
		this.mp -= skillMp;
		
		if(typeHit)
			decreaseHpTypeHitBySkill(target, 2.5);
		else
			decreaseHpBySkill(target, 2.5);
		
		String hitInfo = typeHit ? "& [Type Hit!] " : "";
		System.out.println("[BUFF!] "+hitInfo + "도적 " + this.name + " 유저가 몬스터를 기습했습니다");
	}

}