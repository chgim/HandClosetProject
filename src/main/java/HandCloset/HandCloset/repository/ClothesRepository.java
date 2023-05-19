package HandCloset.HandCloset.repository;

import HandCloset.HandCloset.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    List<Clothes> findByCategoryAndSubcategory(String category, String subcategory);

    List<Clothes> findByCategory(String category);

    List<Clothes> findBySubcategory(String subcategory);
}