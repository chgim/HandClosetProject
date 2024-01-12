
package HandCloset.HandCloset.service;
import HandCloset.HandCloset.entity.Diary;
import HandCloset.HandCloset.entity.Member;
import HandCloset.HandCloset.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import HandCloset.HandCloset.entity.Clothes;
import HandCloset.HandCloset.repository.ClothesRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class ClothesService {
    @Value("${upload.directory}")
    private String uploadDirectory;
    private final ClothesRepository clothesRepository;

    private final MemberRepository memberRepository;

    public ClothesService(ClothesRepository clothesRepository, MemberRepository memberRepository) {
        this.clothesRepository = clothesRepository;
        this.memberRepository = memberRepository;
    }

    public Clothes saveClothes(Clothes clothes) {
        return clothesRepository.save(clothes);
    }
    @Transactional(readOnly = true)
    public Clothes getClothes(Long id, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findByIdAndMember(id, member).orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getAllClothes( Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findByMember(member);
    }

    public void deleteClothes(Long id,Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
            clothesRepository.deleteByIdAndMember(id,member);
        } catch (EmptyResultDataAccessException e) {
            // 요청한 id에 해당하는 Clothes 엔티티가 존재하지 않는 경우
            throw new EntityNotFoundException("Clothes entity with id " + id + " does not exist.");
        }
    }
    //다른 클래스에서 활용하는 메서드
    public void deleteClothesAndImage(Long id,Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
            try {

                Clothes clothes = clothesRepository.findByIdAndMember(id, member).orElse(null);
                String imagePath = clothes.getImgpath();
                // 파일 경로 구분자 수정
                String modifiedImagePath = imagePath.replace("\\", "/");
                Path imageFilePath = Paths.get(modifiedImagePath);

                // 파일 시스템에서 이미지 삭제
                Files.delete(imageFilePath);

            } catch (IOException e) {
                // 파일 삭제 실패 시 예외 처리
                e.printStackTrace();
                throw new RuntimeException("Failed to delete image and data.");
            }
            clothesRepository.deleteByIdAndMember(id,member);
        } catch (EmptyResultDataAccessException e) {
            // 요청한 id에 해당하는 Clothes 엔티티가 존재하지 않는 경우
            throw new EntityNotFoundException("Clothes entity with id " + id + " does not exist.");
        }
    }
    public void deleteAllClothes(Long memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
            List<Clothes> clothesList = clothesRepository.findByMember(member);

            for (Clothes clothes : clothesList) {
                String imagePath = clothes.getImgpath();
                // 파일 경로 구분자 수정
                String modifiedImagePath = imagePath.replace("\\", "/");
                Path imageFilePath = Paths.get(modifiedImagePath);
                Files.delete(imageFilePath);
            }

            clothesRepository.deleteByMember(member);
        } catch (IOException e) {
            // 파일 삭제 실패 시 예외 처리
            e.printStackTrace();
            throw new RuntimeException("Failed to delete");
        }
    }

    @Transactional(readOnly = true)
    public int getClothesCount(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.countByMember(member);
    }

    @Transactional(readOnly = true)
    public List<Clothes> getClothesByCategory(String category,Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findByCategoryAndMember(category,member);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getClothesBySubcategory(String subcategory,Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findBySubcategoryAndMember(subcategory,member);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getClothesByCategoryAndSubcategory(String category, String subcategory,Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findByCategoryAndSubcategoryAndMember(category, subcategory,member);
    }
    public String saveImage(MultipartFile file, Long memberId) {
        try {
            // 사용자별 디렉토리 생성
            String userDirectory = uploadDirectory + File.separator + "member_" + memberId;
            File directory = new File(userDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 이미지 파일 경로 생성
            String filePath = userDirectory + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return filePath;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getCategoryItemCountForClothes(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        List<Clothes> allClothes = clothesRepository.findByMember(member);
        Map<String, Integer> itemCountMap = new HashMap<>();

        for (Clothes clothes : allClothes) {
            String category = clothes.getCategory();
            String subcategory = clothes.getSubcategory();
            String categoryKey = category + "-" + subcategory;
            itemCountMap.put(categoryKey, itemCountMap.getOrDefault(categoryKey, 0) + 1);
        }

        return itemCountMap;
    }

    ///
    @Transactional(readOnly = true)
    public Map<String, Integer> getSeasonStatistics(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        List<Clothes> clothesList = clothesRepository.findByMember(member);
        Map<String, Integer> statistics = new HashMap<>();

        for (Clothes clothes : clothesList) {
            String[] seasons = clothes.getSeason().split(",");
            for (String season : seasons) {
                statistics.put(season, statistics.getOrDefault(season, 0) + 1);
            }
        }

        return statistics;
    }
    @Transactional(readOnly = true)
    public List<Clothes> getTopItems(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findTop5ByMemberOrderByWearcntDesc(member);
    }

    @Transactional(readOnly = true)
    public List<Clothes> getBottomItems(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findTop5ByMemberOrderByCreatedateAsc(member);
    }
    ///
    @Transactional(readOnly = true)
    public List<Clothes> getFilteredClothes(String subcategory, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findBySubcategoryAndMember(subcategory, member);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getRecommendedClothes(String subcategory, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findTop2BySubcategoryAndMemberOrderByWearcntDesc(subcategory, member);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getRecommendedClothesAsc(String subcategory, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findTop2BySubcategoryAndMemberOrderByCreatedateAsc(subcategory, member);
    }
    @Transactional(readOnly = true)
    public List<Clothes> getRandomRecommendedClothes(String subcategory, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.getRandomRecommendedClothes(subcategory, member);
    }
    public void updateWearCountAndCreateDateOnCreate(Long imageId, Date date, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Optional<Clothes> optionalClothes = clothesRepository.findByIdAndMember(imageId, member);
        optionalClothes.ifPresent(clothes -> {
            clothes.setWearcnt(clothes.getWearcnt() + 1);
            Date existingCreatedate = clothes.getCreatedate();
            if (existingCreatedate == null || date.after(existingCreatedate)) {
                clothes.setCreatedate(date); // 최근의 날짜인 경우에만 createdate를 업데이트 시킴
            }
            clothesRepository.save(clothes);
        });
    }

    public void updateWearCountAndCreateDateOnDelete(Long imageId, Date date, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Optional<Clothes> optionalClothes = clothesRepository.findByIdAndMember(imageId, member);
        optionalClothes.ifPresent(clothes -> {
            clothes.setWearcnt(clothes.getWearcnt() - 1);
            Date existingCreatedate = clothes.getCreatedate();
            clothes.setCreatedate(date); // 최근의 날짜인 경우에만 createdate를 업데이트 시킴

            clothesRepository.save(clothes);
        });
    }
    @Transactional(readOnly = true)
    public List<Clothes> getClothesByImageIds(List<Long> imageIds, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return clothesRepository.findByIdInAndMember(imageIds, member);
    }
}