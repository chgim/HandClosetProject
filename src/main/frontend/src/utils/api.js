import axios from "axios";

const getClothesByCategoryAndSubcategory = (category, subcategory) => {
    return axios
        .get(`/api/clothing/category`, {
            params: {
                category: category,
                subcategory: subcategory,
            },
        })
        .then((response) => {
            // 이미지 파일 경로를 가져오기 위해 응답 데이터를 가공합니다.
            const clothesWithImageUrls = response.data.map((clothes) => ({
                ...clothes,
                image: `/api/clothing/images/${clothes.id}`,
            }));
            return clothesWithImageUrls;
        });
};

export { getClothesByCategoryAndSubcategory };