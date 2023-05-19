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
            return response.data;
        });
};

export { getClothesByCategoryAndSubcategory };