package TEXT_RPG;

import java.util.Scanner;

public class GameMain {
	
	static String job; //직업 구분할 변수
	
	//파티원 출력
	public static void printPartyInfo(Creature[] party) {
		for (int i = 0; i < party.length; i++) {
			party[i].printInfo();
		}
	}

	// 파티원 전원의 hp 총합
	public static int getPartyTotalHp(Creature[] party) {
		int sum = 0;
		for (int i = 0; i < party.length; i++) {
			sum += party[i].hp;
		}
		return sum;
	}
	
	// 전사, 마법사, 도적, 힐러중에 하나 선택해서 객체 생성
	public static void setPlayerParty(Player[] party) {
		Scanner s = new Scanner(System.in);
		for (int i = 0; i < party.length; i++) {
			System.out.print("[" + (i + 1) + "/" + party.length + "] 전사(w), 마법사(m), 도적(t), 힐러(h) 중 선택하세요 : ");
			String playerSelect = s.nextLine();
			if (playerSelect.equals("w")) {
				party[i] = new Warrior("player" + i);
				job = "w";
			} else if (playerSelect.equals("m")) {
				party[i] = new Magician("player" + i);
				job = "m";
			} else if (playerSelect.equals("t")) {
				party[i] = new Thief("player" + i);	
				job = "t";
			} else if (playerSelect.equals("h")) {
				party[i] = new Healer("player" + i);
				job = "h";
			} else {
				System.out.println("w, m, t, h 중 하나를 입력해주세요!");
				i--;
			}
		}
		s.close();
	}
			
	
	// 몬스터 배열을 전달받아 몬스터 객체를 생성해주는 메서드
	public static void setMonsterParty(Monster[] party) {
		for (int i = 0; i < party.length; i++) {
			party[i] = new Monster();
		}
	}

	// 플레이어 1번, 몬스터 1번 때리는 메서드
	public static int runOneRound(Player2ndLevel[] attackParty, Monster[] defenseParty) {
		// 플레이어 전투 시작
		int randomPIdx = (int) (Math.random() * attackParty.length);

		while (attackParty[randomPIdx].hp == 0) {
			randomPIdx = (int) (Math.random() * attackParty.length);
		}
		
		// 때릴 몬스터 선택(0번째 몬스터부터 시작)
		int target = 0;
		while (defenseParty[target].hp == 0) {
			target++;
			if (target == defenseParty.length)//만약 target과 defenseParty 길이가 같다면(마지막까지 돌았다면) 플레이어 승리(return 1;)
				return 1;
		}
		
		// 스킬 사용 가능 상태 체크
		 if(attackParty[randomPIdx].mp >= (attackParty[randomPIdx]).skillMp && job == "h") {
			attackParty[randomPIdx].skill(attackParty[randomPIdx]);
		}else if (attackParty[randomPIdx].mp >= (attackParty[randomPIdx]).skillMp) {
			attackParty[randomPIdx].skill(defenseParty[target]);
		}else {
			attackParty[randomPIdx].attackType(defenseParty[target]);
		}

		// 몬스터 전체 체력이 0이면 전투 중지(함수 탈출. 1이면 플레이어 승리))
		if (getPartyTotalHp(defenseParty) == 0)
			return 1;

		// 몬스터 전투 시작
		int randomMIdx = (int) (Math.random() * defenseParty.length);
		while (defenseParty[randomMIdx].hp == 0) {
			randomMIdx = (int) (Math.random() * defenseParty.length);
		}

		target = 0;
		while (attackParty[target].hp == 0) {
			target++;
			// 만약 끝까지 봤다면 전투 중지(함수 탈출, -1이면 몬스터 승리)
			if (target == attackParty.length)
				return -1;
		}

		defenseParty[randomMIdx].attackType(attackParty[target]);

		// 0이면 다음 round 다시 플레이
		return 0;
	}

	public static void main(String[] args) {
		int playerNum = 3;
		Player2ndLevel[] players = new Player2ndLevel[playerNum];

		setPlayerParty(players); // 유저로부터 w,m,t를 입력받아 객체를 생성하는 함수
		
		System.out.println("---- [Player Party Info] ----");
		printPartyInfo(players);

		int randomMonsterNum = (int) (Math.random() * 5) + 1;
		Monster[] monsters = new Monster[randomMonsterNum];

		setMonsterParty(monsters); // 몬스터 객체를 생성자를 통해 생성
		
		System.out.println("---- [Monster Party Info] ----");
		printPartyInfo(monsters);

		int result = 0;
		while (getPartyTotalHp(players) > 0 && getPartyTotalHp(monsters) > 0) {
			System.out.println("---------Round Start---------");
			
			result = runOneRound(players, monsters); // 플레이어 1번, 몬스터 1번 공격하는 라운드

			System.out.println("---- [Player Party Info] ----");
			printPartyInfo(players);
			System.out.println("---- [Monster Party Info] ----");
			printPartyInfo(monsters);
			
			System.out.println("---------Round End---------");
			try {
				Thread.sleep(2000); // 2초 대기
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (result == 1 || getPartyTotalHp(players) > 0) {
			System.out.println("플레이어 승리");
		} else if (result == -1 || getPartyTotalHp(monsters) > 0) {
			System.out.println("몬스터 승리");
		} else {
			System.out.println("게임 종료");
		}
	}
 
}