package HandCloset.HandCloset.repository;

import HandCloset.HandCloset.entity.Clothes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothesRepository extends CrudRepository<Clothes, Long> {
    @Query("SELECT c FROM Clothes c WHERE c.category = :category AND c.subcategory = :subcategory")
    List<Clothes> findByCategoryAndSubcategory(@Param("category") String category, @Param("subcategory") String subcategory);

    List<Clothes> findByCategory(String category);

    List<Clothes> findBySubcategory(String subcategory);
}