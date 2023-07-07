package TEXT_RPG;

public class Player extends Creature{

	final String name; //playerName은 바뀌지 않음
	int mp;	//playerSkill에 쓰는 마나
	int healerType;
	boolean typeHitHealer=false;
	
	Player(String name) { //player값 받아오는 메소드
		this.name = name;
		this.hp = 100;
		this.mp = 100;
		this.str = 10;
	}
	

	@Override
	public void heal(Creature target) {
		if(this.hp<=this.str) {
			this.hp += this.str;
		}else {
			this.hp = 100;
		}
		System.out.println(this.name + " 유저가 회복합니다");
	}

	@Override
	public void healType(Creature target) {
		boolean typeHitHealer = TypeCheck(this.type, this.type);
		if (typeHitHealer) {
			HealHpTypeHit(target);
		}
		else
			HealHp(target);
	}
	
	public void healSay() {
		String hitInfo = typeHitHealer ? "[TYPE MATCH!] " : "";
		System.out.println(hitInfo + "유저가 회복합니다");
	}
	
	@Override
	public void attack(Creature target) { //일반 공격 메서드
		if (target.hp >= this.str) {
			target.hp -= this.str;
		} else {
			target.hp = 0;
		}
		System.out.println(this.name + " 유저가 몬스터를 공격합니다");
	}


	@Override
	public void attackType(Creature target) {
		boolean typeHit = TypeCheck(this.type, target.type);
		if (typeHit)
			DecreaseHpTypeHit(target);
		else
			DecreaseHp(target);

		String hitInfo = typeHit ? "[TYPE HIT!] " : "";
		System.out.println(hitInfo + this.name + " 유저가 몬스터를 공격합니다");
	}
	
	public void BuffAttackType(Creature target) {
		boolean typeHit = TypeCheck(this.type, target.type);
		if (typeHit)
			BuffDecreaseHpTypeHit(target);
		else
			BuffDecreaseHp(target);

		String hitInfo = typeHit ? "& [TYPE HIT!] " : "";
		System.out.println("[BUFF] "+hitInfo + this.name + " 유저가 몬스터를 공격합니다");
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", mp=" + mp + ", str=" + str + ", hp=" + hp + ", type=" + type + "]";
	}


}
