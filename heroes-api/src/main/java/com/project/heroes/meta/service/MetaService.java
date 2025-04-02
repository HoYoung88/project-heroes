package com.project.heroes.meta.service;

import com.project.heroes.meta.dto.MetaDto;
import com.project.heroes.meta.dto.MetaDto.CharacterMetaDto;
import com.project.heroes.meta.dto.MetaDto.EquipmentSlotDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaService {

    public MetaDto getMeta() {
        return new MetaDto(characterMetaDto(), findAllEquipmentInfo());
    }

    private CharacterMetaDto characterMetaDto() {
        String[] physicalClass = new String[]{"리시타", "피오나", "카록", "카이", "벨라", "허크", "린", "델리아", "미리", "그림덴", "벨", "레서",
            "카엘", "레티", "아켈", "소우", "사냐"};
        String[] magicClass = new String[]{"이비", "아리샤", "헤기", "미울", "테사", "단아", "라티야", "체른", "네반"};
        return new CharacterMetaDto(physicalClass, magicClass, characterSkillDto());
    }

    private Map<String, String[]> characterSkillDto() {

        return new HashMap<>() {{
            put("리시타",
                new String[]{"스피어대시", "리버레이트", "3960 허리케인", "문 스플리터", "사일런트 호크", "서든 블래스트", "윈드밀 부스트", "블클레어", "액셀러레이트",
                    "라이트닝 퓨리: 듀얼스피어", "퀵 리버레이트"});
            put("피오나",
                new String[]{"그랜드 슬램 칼라", "버터플라이 스윙", "비틀 크러쉬", "스티그마 해머", "하니 비 스팅", "액티브: 블루밍 바이올렛", "액티브: 짓누르기"});
            put("이비", new String[]{"블링크", "라이프 스틸", "새크리파이스", "스피릿 바인드", "액티브: 컨퓨전 홀", "데몬 스플리터", "블링크 문라이트", "헬 퀘이크",
                "데스 레이블", "소울 스큐어", "피어 스크림", "액티브: 인세인 리퍼", "블러디 스레드", "액티브: 아케인 게이트", "인비지블 룸"});
            put("카록",
                new String[]{"위빙", "볼로 펀치", "빅뱅", "선데이 펀치", "스트레이트 펀치", "웨이스트 피벗", "버스트", "버스트: 돌격", "차징 버스트", "충격 전환",
                    "카운터 블로우", "버스트: 강타", "액티브: 폭풍", "액티브: 화산 폭발", "액티브: 힘의 해방", "버스트: 파괴", "더킹", "액티브: 격노",
                    "액티브: 인파이팅", "J.F 버스트", "클린 히트", "오버 플로우", "뎀프시롤"});
            put("카이",
                new String[]{"리로드", "볼트 리볼버", "센세이션", "스핀 스매시", "퀵 리볼버", "액티브: 할로우샷", "액티브: 피어싱", "액티브: 블렛6", "인사이트",
                    "액티브: 핸드밤", "액티브: 밴드 서포트", "액티브: 인핸스 볼트", "액티브: 거스팅 볼트", "액티브: 벙커 버스터", "액티브: 아토믹"});
            put("벨라",
                new String[]{"루미너스 러시", "모먼트 리프", "미스트 슬래쉬", "스파이럴 스핀", "액티브: 뱀파이어의 키스", "액티브: 도미네이트", "액티브: 차가운 심장",
                    "모먼트 블로우", "컷 어크로스", "소노러스 필", "액티브: 레드 아이즈", "액티브: 냉각", "액티브: 플로잉 블러드", "액티브: 글라시얼 브레이커",
                    "액티브: 폴라리스 소나타", "액티브: 블레이드 댄스", "체인 마스터리"});
            put("허크", new String[]{"가벼운 상처", "롤링 로드", "리로드 스탠스", "액티브: 스플릿 슬래쉬", "예리한 칼날", "사격 마스터리", "액티브: 퍼펙트 리로드",
                "액티브: 임펙트 슈어 샷", "벌어진 상처", "액티브: 스피닝 슬래쉬", "총열 강화", "액티브: 페이탈 슬래쉬", "탄환 업그레이드", "찢겨진 상처",
                "액티브: 슬랩 더 킬러", "액티브: 데스페라도"});
            put("린",
                new String[]{"기화심공", "내화", "비화", "비화격", "비화풍", "액티브: 비풍일격", "외화", "월연", "월연격", "월연풍", "진풍", "진풍격", "화진",
                    "화진격", "화진각", "액티브: 화신풍", "액티브: 투산철격", "액티브: 반화격", "액티브: 기천비무", "액티브: 유화강기", "액티브: 유원백화",
                    "액티브: 운심월성"});
            put("아리샤",
                new String[]{"마나 비스", "마르카토 러시", "비트 잇", "프레티시모 비트 잇", "스크리밍 콰이어", "스트린젠도 슬랩", "아 바투타", "린포르찬도 피네",
                    "마나 디소넌스", "마나 아그레망", "리시딩 인터류드", "액티브: 마나 메타모포제", "액티브: 엑시덴탈 악센트", "액티브: 모티베이션", "액티브: 인스피레이션",
                    "액티브: 배턴 포 왈츠", "액티브: 마나 카프리치오", "액티브: 케레센도 심포니"});
        }};
    }

    private EquipmentSlotDto findAllEquipmentInfo() {
        Map<String, String> slotName = new HashMap<>() {{
            put("RIGHT_HAND", "무기");
            put("LEFT_HAND", "수호부");
            put("HAND", "팔");
            put("BELT", "벨트");
            put("LEG", "신발");
            put("LOWER", "하의");
            put("UPPER", "상의");
            put("HEAD", "머리");
            put("EARRING", "귀걸이");
            put("RHOD", "로드");
            put("LEFT_FINGER", "반지");
            put("RIGHT_FINGER", "반지");
            put("NECKLACE", "목걸이");
            put("LEFT_WRIST", "팔찌");
            put("RIGHT_WRIST", "팔찌");
            put("CHARM", "브로치");
            put("ARTIFACT", "아티팩트");
            put("HAIR", "헤어");
            put("FACEPAINTING", "페이스 페인팅");
            put("BODYPAINTING", "바디 페인팅");
            put("MAKEUP", "메이크업");
            put("INNER_ARMOR", "이너아머");
            put("LEFT_EPAULET", "왼쪽 견장");
            put("RIGHT_EPAULET", "오른쪽 견장");
            put("AVATAR_WEAPON", "무기 아바타");
            put("AVATAR_GLOVES", "아바타 장갑");
            put("AVATAR_HELM", "아바타 모자");
            put("AVATAR_TUNIC", "아바타 상의");
            put("AVATAR_PANTS", "아바타 하의");
            put("AVATAR_BOOTS", "아바타 부츠");
            put("AVATAR_REAR", "날개");
            put("AVATAR_TAIL", "꼬리");
            put("BADGE", "배지");
            put("LENS", "렌즈");
            put("BODY_SHAPE", "체형");
            put("SUBWEAPON", "보조장비");
        }};

        String[] slotOrders = new String[]{"EARRING", "HEAD", "NECKLACE", "RIGHT_HAND", "UPPER", "LEFT_HAND", "LOWER",
            "HAND", "BELT", "LEG", "CHARM", "RIGHT_FINGER", "ARTIFACT", "LEFT_FINGER", "RIGHT_WRIST", "RHOD",
            "LEFT_WRIST"};

        return new EquipmentSlotDto(slotName, slotOrders);
    }

}
