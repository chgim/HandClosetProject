package HandCloset.HandCloset.service;

import HandCloset.HandCloset.entity.Clothes;
import HandCloset.HandCloset.entity.Diary;
import HandCloset.HandCloset.repository.ClothesRepository;
import HandCloset.HandCloset.repository.DiaryRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DiaryService {

    @Value("C:/DiaryImageStorage")
    private String diaryUploadDirectory;

    private final DiaryRepository diaryRepository;


    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;


    }

    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }


    public String saveThumbnail(MultipartFile file) {
        try {
            // 이미지를 파일 시스템에 저장하고 저장된 경로를 반환합니다.
            String filePath = diaryUploadDirectory + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return filePath;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    public List<Diary> getAllDiaryEntries() {
        return diaryRepository.findAll();
    }

    public List<Diary> getDiaryEntriesByDate(Date date) {
        return diaryRepository.findAllByDate(date);
    }

    public Diary getDiaryEntryById(Long id) {
        return diaryRepository.findById(id).orElse(null);
    }
    public List<Long> getImageIdsByDiaryId(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElse(null);
        if (diary == null) {
            return Collections.emptyList();
        }
        return diary.getImageIds();
    }
    public List<Diary> findDiariesByImageId(Long imageId) {
        return diaryRepository.findAllByImageIdsContaining(imageId);
    }
    /*
    public void deleteDiary(Long id) {
        Diary diary = diaryRepository.findById(id).orElse(null);
        if (diary != null) {
            List<Long> imageIds = diary.getImageIds();

            // 이미지 ID가 비어있지 않으면 이미지 ID에 해당하는 Clothes 아이템들의 createdate를 최신으로 업데이트하고 wearcnt를 -1
            if (!imageIds.isEmpty()) {
                for (Long imageId : imageIds) {
                    Optional<Clothes> optionalClothes = clothesRepository.findById(imageId);
                    optionalClothes.ifPresent(clothes -> {
                        Date latestDate = findLatestDateByImageId(imageId);
                        if (latestDate != null) {
                            clothes.setCreatedate(latestDate);
                        }
                        clothes.setWearcnt(clothes.getWearcnt() - 1);
                        clothesRepository.save(clothes);
                    });
                }
            }

            diaryRepository.deleteById(id);
        }
    }

     */
    /*
    public Date findLatestDateByImageId(Long imageId) {
        List<Diary> diaries = diaryRepository.findAllByImageIdsContaining(imageId);
        Date latestDate = null;

        for (Diary diary : diaries) {
            List<Long> imageIds = diary.getImageIds();
            if (imageIds.contains(imageId)) {
                Date diaryDate = diary.getDate();
                if (latestDate == null || diaryDate.after(latestDate)) {
                    latestDate = diaryDate;
                }
            }
        }

        return latestDate;
    }
    */

}