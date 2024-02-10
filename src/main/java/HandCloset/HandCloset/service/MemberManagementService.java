package HandCloset.HandCloset.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberManagementService {

    private final ClothesService clothesService;
    private final DiaryService diaryService;
    private final MemberService memberService;

    // 회원탈퇴 or 회원 삭제(관리자)시 사용
    @Transactional
    public void deleteMemberAndRelatedData(Long memberId) {
        clothesService.deleteAllClothes(memberId);
        diaryService.deleteAllDiaries(memberId);
        memberService.deleteMember(memberId);
    }
}
