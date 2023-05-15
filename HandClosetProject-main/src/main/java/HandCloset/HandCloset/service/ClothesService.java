//package HandCloset.HandCloset.service;
//
//import HandCloset.HandCloset.entity.Clothes;
//import HandCloset.HandCloset.repository.ClothesRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ClothesService {
//    private final ClothesRepository clothesRepository;
//
//    public ClothesService(ClothesRepository clothesRepository) {
//        this.clothesRepository = clothesRepository;
//    }
//
//    public Clothes saveClothes(Clothes clothes) {
//        return clothesRepository.save(clothes);
//    }
//
//    public Clothes getClothes(Long id) {
//        return clothesRepository.findById(id).orElse(null);
//    }
//
//    public List<Clothes> getAllClothes() {
//        return (List<Clothes>) clothesRepository.findAll();
//    }
//
//    public void deleteClothes(Long id) {
//        clothesRepository.deleteById(id);
//    }
//}

package HandCloset.HandCloset.service;

import HandCloset.HandCloset.entity.Clothes;
import HandCloset.HandCloset.repository.ClothesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {
    private final ClothesRepository clothesRepository;

    public ClothesService(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    public Clothes saveClothes(Clothes clothes) {
        return clothesRepository.save(clothes);
    }

    public Clothes getClothes(Long id) {
        return clothesRepository.findById(id).orElse(null);
    }

    public List<Clothes> getAllClothes() {
        return (List<Clothes>) clothesRepository.findAll();
    }

    public void deleteClothes(Long id) {
        clothesRepository.deleteById(id);
    }

    public List<Clothes> getClothesByCategory(String category) {
        return clothesRepository.findByCategory(category);
    }

    public List<Clothes> getClothesBySubcategory(String subcategory) {
        return clothesRepository.findBySubcategory(subcategory);
    }

    public List<Clothes> getClothesByCategoryAndSubcategory(String category, String subcategory) {
        return clothesRepository.findByCategoryAndSubcategory(category, subcategory);
    }
}