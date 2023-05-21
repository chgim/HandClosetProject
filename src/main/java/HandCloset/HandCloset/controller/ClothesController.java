
package HandCloset.HandCloset.controller;

import HandCloset.HandCloset.entity.Clothes;
import HandCloset.HandCloset.service.ClothesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/clothing")
public class ClothesController {
    private final ClothesService clothesService;

    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clothes saveClothes(@RequestParam("file") MultipartFile file,
                               @RequestParam("category") String category,
                               @RequestParam("subcategory") String subcategory,
                               @RequestParam("season") String season,
                               @RequestParam(value = "description", required = false) String description) {
        Clothes clothes = new Clothes();
        // 파일을 저장하고 저장된 경로를 DB에 저장합니다.
        String imagePath = clothesService.saveImage(file);
        clothes.setImgPath(imagePath);
        clothes.setCategory(category);
        clothes.setSubcategory(subcategory);
        clothes.setSeason(season);
        clothes.setDescription(description);
        return clothesService.saveClothes(clothes);
    }

    @GetMapping("/{id}")
    public Clothes getClothes(@PathVariable Long id) {
        return clothesService.getClothes(id);
    }

    @GetMapping
    public List<Clothes> getAllClothes() {
        return clothesService.getAllClothes();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClothes(@PathVariable Long id) {
        clothesService.deleteClothes(id);
    }

    @GetMapping("/category")
    public List<Clothes> getClothesByCategoryAndSubcategory(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subcategory) {
        if (category == null && subcategory == null) {
            return clothesService.getAllClothes();
        } else if (category != null && subcategory == null) {
            return clothesService.getClothesByCategory(category);
        } else if (category == null && subcategory != null) {
            return clothesService.getClothesBySubcategory(subcategory);
        } else {
            return clothesService.getClothesByCategoryAndSubcategory(category, subcategory);
        }
    }
    //////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getClothesImage(@PathVariable Long id) throws IOException {
        Clothes clothes = clothesService.getClothes(id);
        String imgPath = clothes.getImgPath();
        Path imgpath = Paths.get(imgPath);
        return Files.readAllBytes(imgpath);
    }
}