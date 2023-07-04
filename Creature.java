package TEXT_RPG;

public abstract class Creature implements Attack_Healable, TypeAttack_Healable {

	int str;
	//player 공격력이자 회복력(healer)
	int hp;
	//player 체력
	PlayerType type;
	//playerType 지정 변수 type
	
	Creature (){ //playerType 랜덤 지정
		PlayerType[] types = PlayerType.values(); //PlayerType배열 types 선언, playerType 넣어줌
		this.type = types[(int) (Math.random() * 4)]; //type에 random 사용 -> 랜덤한 type 넣음
	}
	
	void printInfo() { //monster, player 전원 프린트
		System.out.println(this.toString());
	}
	
	boolean TypeCheck(PlayerType attack, PlayerType defense) { //booleanType으로 attack, defense 받아옴
		boolean typeHit = false; //typeHit(속성 체크해줄 변수)
		String typeMatch = attack.toString() + defense.toString(); //typeMatch = attack+defense 이어붙인 값
		switch(typeMatch) { //typeMatch 값 받아옴
		case "FIREEARTH":
		case "EARTHAIR":
		case "AIRWATER":
		case "WATERFIRE":
			typeHit = true; //각 속성별 우세타입이면 typeHit=true;
		}
		return typeHit; //typeHit return;
	}
	
	//target으로 몬스터 받아와야 함
	void DecreaseHp(Creature target) { //일반 타입일때 공격할 메서드
		if (target.hp >= this.str) { //target.hp가 나의 공격력보다 높다면
			target.hp -= this.str; //target.hp - myStr;
		} else { 				//target.hp가 나의 공격력보다 낮다면
			target.hp = 0; 		//target.hp = 0;
		}
	}
	
	void DecreaseHpTypeHit(Creature target) { //우세 타입일 때 공격할 메서드
		if(target.hp >= (int)(this.str * 1.5)) { //target.hp가 나의 공격력*1.5보다 높다면
			target.hp -= (int)(this.str * 1.5);
		} else {
			target.hp = 0;
		}
	}
	
	//target으로 나의 파티원 받아와야 함
	void HealHp(Creature target) { //일반 타입일때 회복할 메서드
		if (target.hp <= this.str) { //target.hp가 나의 회복력보다 낮다면
			target.hp += this.str; //target.hp + myStr;
		}else {						//target.hp가 나의 회복력보다 높다면
			target.hp = 100;		//target.hp = 100;
		}
	}
	
	void HealHpTypeHit(Creature target) {//우세 타입일 때 회복할 메서드
		if (this.hp <= (this.str * 1.5)) { //target.hp가 나의 회복력*1.5보다 낮다면
			this.hp += this.str * 1.5;
		} else {
			this.hp = 100;
		}
	}
}
