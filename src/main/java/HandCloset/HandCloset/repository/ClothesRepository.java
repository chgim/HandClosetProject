package HandCloset.HandCloset.repository;

import HandCloset.HandCloset.entity.Clothes;
import HandCloset.HandCloset.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByCategoryAndSubcategoryAndMember(String category, String subcategory, Member member);

    List<Clothes> findByCategoryAndMember(String category, Member member);

    List<Clothes> findBySubcategoryAndMember(String subcategory, Member member);


    Optional<Clothes> findByIdAndMember(Long id, Member member);

    List<Clothes> findByMember(Member member);

    void deleteByIdAndMember(Long id, Member member);

    List<Clothes> findTop5ByMemberOrderByWearcntDesc(Member member);

    List<Clothes> findTop5ByMemberOrderByWeardateAsc(Member member);

    List<Clothes> findTop2BySubcategoryAndMemberOrderByWearcntDesc(String subcategory, Member member);

    List<Clothes> findTop2BySubcategoryAndMemberOrderByWeardateAsc(String subcategory, Member member);

    List<Clothes> findByIdInAndMember(List<Long> ids, Member member);

    // 일반 SQL에서는 ORDER BY RAND()를 사용하여 랜덤한 순서로 결과를 정렬할 수 있지만, JPQL에서는 RAND() 함수를 직접 지원하지 않음. 따라서 FUNCTION('RAND')을 사용하여 랜덤한 순서로 정렬
    @Query("SELECT c FROM Clothes c WHERE c.subcategory = :subcategory AND c.member = :memberId ORDER BY FUNCTION('RAND')")
    List<Clothes> getRandomRecommendedClothes(@Param("subcategory") String subcategory, @Param("memberId") Member member);

    void deleteByMember(Member member);

    int countByMember(Member member);

    List<Clothes> findByImgpathAndMember(String imgpath, Member member);
}